package com.study.beaws.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {
    @Value("${aws.access-key-id}")
    private String accessKeyId;

    @Value("${aws.secret-access-key}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    @Bean
    public AWSCredentialsProvider awsCredentialsProvider() {
        BasicAWSCredentials creds = new BasicAWSCredentials(accessKeyId, secretKey);
        return new AWSStaticCredentialsProvider(creds);
    }

    @Bean
    public AmazonS3 amazonS3(AWSCredentialsProvider creds) {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(creds)
                .withRegion(region)
                .build();
    }

    @Bean
    public AmazonRekognition amazonRekognition(AWSCredentialsProvider creds) {
        return AmazonRekognitionClientBuilder.standard()
                .withCredentials(creds)
                .withRegion(region)
                .build();
    }
}
