package com.MetaImage.dto;

import lombok.Data;

@Data
public class ImageMetadataDTO {
    private String fileName;
    private long fileSize;
    private String fileType;
    private String fileTypeExtension;
    private String mimeType;
    private int imageWidth;
    private int imageHeight;
    private String majorBrand;
    private String minorVersion;
}
