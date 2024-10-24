package com.MetaImage.utils;


import com.MetaImage.dto.ImageMetadataDTO;
import com.MetaImage.pojo.ImageMetadata;

public class ImageMetadataConverter {

    // Convert Model to DTO
    public static ImageMetadataDTO toDTO(ImageMetadata metadata){
        ImageMetadataDTO dto = new ImageMetadataDTO();
        dto.setFileName(metadata.getFileName());
        dto.setFileSize(metadata.getFileSize());
        dto.setFileType(metadata.getFileType());
        return dto;
    }

    // Convert DTO to Model
    public  static ImageMetadata toModel(ImageMetadataDTO metadataDTO){
        ImageMetadata metadata = new ImageMetadata();
        metadata.setFileName(metadataDTO.getFileName());
        metadata.setFileSize(metadataDTO.getFileSize());
        metadata.setFileType(metadataDTO.getFileType());
        return metadata;
    }
}
