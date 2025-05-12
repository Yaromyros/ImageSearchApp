package com.study.beaws.controller;

import com.study.beaws.service.RekognitionService;
import com.study.beaws.service.S3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/images")
public class ImageController {
    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
    private final S3Service s3Service;
    private final RekognitionService rekognitionService;

    @Autowired
    public ImageController(S3Service s3Service, RekognitionService rekognitionService) {
        this.s3Service = s3Service;
        this.rekognitionService = rekognitionService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = s3Service.uploadImage(file);
        logger.info("Uploaded image file: {}", fileName);
        return ResponseEntity.ok(fileName);
    }

    @GetMapping("/search")
    public ResponseEntity<List<String>> searchObjects(@RequestParam String searchTerm) {
        logger.info("Search requested for term: '{}'", searchTerm);
        List<String> matchingImages = rekognitionService.findImagesByObject(searchTerm);
        logger.info("Found {} matching image(s): {}", matchingImages.size(), matchingImages);
        return ResponseEntity.ok(matchingImages);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllErrors(Exception ex) {
        logger.error("Unexpected error in ImageController:", ex);
        return ResponseEntity.status(500).body("Something went wrong: " + ex.getMessage());
    }
}
