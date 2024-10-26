package com.MetaImage.serviceImpl;

import com.MetaImage.exception.CustomException;
import com.MetaImage.dto.ExifMetadataResponse;
import com.MetaImage.service.ExifMetadataService;
import com.MetaImage.utils.ImageUtils;
import org.apache.commons.imaging.ImageReadException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Service
public class ExifMetadataServiceImpl implements ExifMetadataService {

    @Override
    public ExifMetadataResponse getExifMetadata(MultipartFile file) throws IOException, CustomException, ImageReadException {
        File tempFile = ImageUtils.convertToFile(file);
        return ImageUtils.extractExifMetadata(tempFile);
    }
}
