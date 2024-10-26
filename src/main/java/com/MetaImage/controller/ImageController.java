package com.MetaImage.controller;

import com.MetaImage.dto.ExifMetadataResponse;
import com.MetaImage.exception.CustomException;
import com.MetaImage.service.ExifMetadataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.imaging.ImageReadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/exif")
public class ImageController {

    @Autowired
    private ExifMetadataService exifMetadataService;


    @PostMapping("/view-metadata")
    public ResponseEntity<ExifMetadataResponse> viewMetaData(@RequestParam("file")
    MultipartFile file) throws IOException, CustomException, ImageReadException {
        ExifMetadataResponse metadata = exifMetadataService.getExifMetadata(file);
        log.info("Metadata extracted: %s".formatted(metadata.toString()));
        return ResponseEntity.ok(metadata);
    }

}
