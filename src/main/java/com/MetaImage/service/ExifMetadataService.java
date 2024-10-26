package com.MetaImage.service;

import com.MetaImage.dto.ExifMetadataResponse;
import com.MetaImage.exception.CustomException;
import org.apache.commons.imaging.ImageReadException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ExifMetadataService {

    ExifMetadataResponse getExifMetadata(MultipartFile file) throws IOException, CustomException, ImageReadException;
}
