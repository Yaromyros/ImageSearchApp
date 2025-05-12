package com.study.beaws.service;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.services.rekognition.model.S3Object;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class RekognitionService {
    private static final String BUCKET_NAME = "upload-image-bucket01";
    private static final int MAX_LABELS = 10;
    private static final float MIN_CONFIDENCE = 75.0f;
    private final AmazonRekognition rekognitionClient;
    private final AmazonS3 amazonS3;

    @Autowired
    public RekognitionService(AmazonRekognition rekognitionClient, AmazonS3 amazonS3) {
        this.rekognitionClient = rekognitionClient;
        this.amazonS3 = amazonS3;
    }

    public List<String> findImagesByObject(String searchTerm) {
        List<String> matchingImageUrls = new ArrayList<>();

        ListObjectsV2Request listObjects = new ListObjectsV2Request().withBucketName(BUCKET_NAME);
        ListObjectsV2Result objectListing = amazonS3.listObjectsV2(listObjects);

        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            String fileName = objectSummary.getKey();

            DetectLabelsRequest request = new DetectLabelsRequest()
                    .withImage(new Image().withS3Object(new S3Object().withBucket(BUCKET_NAME).withName(fileName)))
                    .withMaxLabels(MAX_LABELS)
                    .withMinConfidence(MIN_CONFIDENCE);

            List<Label> labels = rekognitionClient.detectLabels(request).getLabels();

            // Check if the object is on the image
            for (Label label : labels) {
                if (label.getName().equalsIgnoreCase(searchTerm)) {
                    // Form URL for the image
                    String imageUrl = amazonS3.getUrl(BUCKET_NAME, fileName).toString();
                    matchingImageUrls.add(imageUrl);  // Add URL to the result
                    break;
                }
            }
        }

        return matchingImageUrls;  // Returning list of URLs
    }
}
