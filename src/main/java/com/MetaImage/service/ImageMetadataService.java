package com.MetaImage.service;

import com.MetaImage.dto.ImageMetadataDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageMetadataService {

    ImageMetadataDTO extractMetadata(MultipartFile file) throws IOException;
}
