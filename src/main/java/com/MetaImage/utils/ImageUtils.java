package com.MetaImage.utils;


import com.MetaImage.dto.ExifMetadataResponse;
import com.MetaImage.exception.CustomException;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ImageUtils {


    //Convert MultipartFile to File
    public static File convertToFile(MultipartFile file) throws IOException {
        File tempFile = File.createTempFile("temp", file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(file.getBytes());
        }
        return tempFile;
    }

    public static ExifMetadataResponse extractExifMetadata(File imageFile) throws CustomException, IOException, ImageReadException {
        final ImageMetadata metadata = Imaging.getMetadata(imageFile);
        ExifMetadataResponse response = new ExifMetadataResponse();

        if (metadata instanceof JpegImageMetadata) {
            final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;

            // Extract and add all available EXIF tags dynamically
            final List<ImageMetadata.ImageMetadataItem> items = jpegMetadata.getItems();
            for (final ImageMetadata.ImageMetadataItem item : items) {
                String itemString = item.toString();

                // Parse the item string assuming it’s in the "Key: Value" format
                String[] parts = itemString.split(": ", 2);  // Split at the first ": "
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    response.addMetadata(key, value);  // Store parsed key and value
                }

            }
        }
        return response;
    }

    //POC Code for full implementation of  EXIF Metadata by Apache Commons Imaging  ::
    // https://github.com/apache/commons-imaging/blob/master/src/test/java/org/apache/commons/imaging/examples/MetadataExample.java

}



