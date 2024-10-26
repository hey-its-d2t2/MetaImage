package com.MetaImage.utils;


import com.MetaImage.dto.ExifMetadataResponse;
import com.MetaImage.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
/*import org.apache.commons.imaging.formats.tiff.TiffField;
import org.apache.commons.imaging.formats.tiff.TiffImageMetadata;
import org.apache.commons.imaging.formats.tiff.taginfos.TagInfo;*/
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
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
                response.addMetadata(item.toString(), item.toString());  // Use item.getKeyword() as the key
            }
        }
        return response;
    }

    /*
    public static ExifMetadataResponse extractExifMetadata_test(File imageFile) throws CustomException, IOException, ImageReadException {
        // Get all metadata stored in EXIF format (from JPEG or TIFF).
        final ImageMetadata metadata = Imaging.getMetadata(imageFile);
        ExifMetadataResponse response = new ExifMetadataResponse(); // Create response object
        log.info(response.toString()+"22");

        if (metadata instanceof JpegImageMetadata) {
            final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;

            // Add common EXIF tags to the response
          *//*
            addTagToResponse(response, jpegMetadata, TiffTagConstants.TIFF_TAG_XRESOLUTION);
            addTagToResponse(response, jpegMetadata, TiffTagConstants.TIFF_TAG_DATE_TIME);
            addTagToResponse(response, jpegMetadata, ExifTagConstants.EXIF_TAG_DATE_TIME_ORIGINAL);
            addTagToResponse(response, jpegMetadata, ExifTagConstants.EXIF_TAG_DATE_TIME_DIGITIZED);
            addTagToResponse(response, jpegMetadata, ExifTagConstants.EXIF_TAG_ISO);
            addTagToResponse(response, jpegMetadata, ExifTagConstants.EXIF_TAG_SHUTTER_SPEED_VALUE);
            addTagToResponse(response, jpegMetadata, ExifTagConstants.EXIF_TAG_APERTURE_VALUE);
            addTagToResponse(response, jpegMetadata, ExifTagConstants.EXIF_TAG_BRIGHTNESS_VALUE);

           *//*

            // Extract GPS information if available
            final TiffImageMetadata exifMetadata = jpegMetadata.getExif();

            if (exifMetadata != null) {
                final TiffImageMetadata.GPSInfo gpsInfo = exifMetadata.getGPS();
                if (gpsInfo != null) {
                    response.addMetadata("GPS Description", gpsInfo.toString());
                    response.addMetadata("GPS Longitude", String.valueOf(gpsInfo.getLongitudeAsDegreesEast()));
                    response.addMetadata("GPS Latitude", String.valueOf(gpsInfo.getLatitudeAsDegreesNorth()));
                }
            }

            // Dynamically extract all available EXIF tags
            final List<ImageMetadata.ImageMetadataItem> items = jpegMetadata.getItems();
            log.info(items.toString());
            for (final ImageMetadata.ImageMetadataItem item : items) {
                response.addMetadata(item.getClass().toString(), item.toString());
            }
        }
        return response; // Return the populated response object
    }

    private static void addTagToResponse(ExifMetadataResponse response, JpegImageMetadata jpegMetadata, TagInfo tagInfo) {
        final TiffField field = jpegMetadata.findEXIFValueWithExactMatch(tagInfo);
        if (field != null) {
            response.addMetadata(tagInfo.name, field.getValueDescription());
            //log.info(new StringBuilder().append("100::").append(response.addMetadata(tagInfo.name, field.getValueDescription())).toString());
        } else {
            response.addMetadata(tagInfo.name, "Not Found");
        }
    }


    */
    //POC Code from :: https://github.com/apache/commons-imaging/blob/master/src/test/java/org/apache/commons/imaging/examples/MetadataExample.java
    /**

    //Extract EXIF metadata from the image file
    public static ExifMetadataResponse extractExifMetadata(File imageFile) throws CustomException, IOException, ImageReadException {
            // get all metadata stored in EXIF format (ie. from JPEG or TIFF).
            final ImageMetadata metadata = Imaging.getMetadata(imageFile);

            // System.out.println(metadata);

            if (metadata instanceof JpegImageMetadata) {
                final JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;

                // Jpeg EXIF metadata is stored in a TIFF-based directory structure
                // and is identified with TIFF tags.
                // Here we look for the "x resolution" tag, but
                // we could just as easily search for any other tag.
                //
                // see the TiffConstants file for a list of TIFF tags.

                System.out.println("file: " + imageFile.getPath());

                // print out various interesting EXIF tags.
                printTagValue(jpegMetadata, TiffTagConstants.TIFF_TAG_XRESOLUTION);
                printTagValue(jpegMetadata, TiffTagConstants.TIFF_TAG_DATE_TIME);
                printTagValue(jpegMetadata, ExifTagConstants.EXIF_TAG_DATE_TIME_ORIGINAL);
                printTagValue(jpegMetadata, ExifTagConstants.EXIF_TAG_DATE_TIME_DIGITIZED);
                printTagValue(jpegMetadata, ExifTagConstants.EXIF_TAG_ISO);
                printTagValue(jpegMetadata, ExifTagConstants.EXIF_TAG_SHUTTER_SPEED_VALUE);
                printTagValue(jpegMetadata, ExifTagConstants.EXIF_TAG_APERTURE_VALUE);
                printTagValue(jpegMetadata, ExifTagConstants.EXIF_TAG_BRIGHTNESS_VALUE);
                printTagValue(jpegMetadata, GpsTagConstants.GPS_TAG_GPS_LATITUDE_REF);
                printTagValue(jpegMetadata, GpsTagConstants.GPS_TAG_GPS_LATITUDE);
                printTagValue(jpegMetadata, GpsTagConstants.GPS_TAG_GPS_LONGITUDE_REF);
                printTagValue(jpegMetadata, GpsTagConstants.GPS_TAG_GPS_LONGITUDE);

                System.out.println();

                // simple interface to GPS data
                final TiffImageMetadata exifMetadata = jpegMetadata.getExif();
                if (null != exifMetadata) {
                    final TiffImageMetadata.GPSInfo gpsInfo = exifMetadata.getGPS();
                    if (null != gpsInfo) {
                        final String gpsDescription = gpsInfo.toString();
                        final double longitude = gpsInfo.getLongitudeAsDegreesEast();
                        final double latitude = gpsInfo.getLatitudeAsDegreesNorth();

                        System.out.println("    " + "GPS Description: " + gpsDescription);
                        System.out.println("    " + "GPS Longitude (Degrees East): " + longitude);
                        System.out.println("    " + "GPS Latitude (Degrees North): " + latitude);
                    }
                }

                // more specific example of how to manually access GPS values
                final TiffField gpsLatitudeRefField = jpegMetadata.findEXIFValueWithExactMatch(GpsTagConstants.GPS_TAG_GPS_LATITUDE_REF);
                final TiffField gpsLatitudeField = jpegMetadata.findEXIFValueWithExactMatch(GpsTagConstants.GPS_TAG_GPS_LATITUDE);
                final TiffField gpsLongitudeRefField = jpegMetadata.findEXIFValueWithExactMatch(GpsTagConstants.GPS_TAG_GPS_LONGITUDE_REF);
                final TiffField gpsLongitudeField = jpegMetadata.findEXIFValueWithExactMatch(GpsTagConstants.GPS_TAG_GPS_LONGITUDE);
                if (gpsLatitudeRefField != null && gpsLatitudeField != null && gpsLongitudeRefField != null && gpsLongitudeField != null) {
                    // all of these values are strings.
                    final String gpsLatitudeRef = (String) gpsLatitudeRefField.getValue();
                    final RationalNumber[] gpsLatitude = (RationalNumber[]) gpsLatitudeField.getValue();
                    final String gpsLongitudeRef = (String) gpsLongitudeRefField.getValue();
                    final RationalNumber[] gpsLongitude = (RationalNumber[]) gpsLongitudeField.getValue();

                    final RationalNumber gpsLatitudeDegrees = gpsLatitude[0];
                    final RationalNumber gpsLatitudeMinutes = gpsLatitude[1];
                    final RationalNumber gpsLatitudeSeconds = gpsLatitude[2];

                    final RationalNumber gpsLongitudeDegrees = gpsLongitude[0];
                    final RationalNumber gpsLongitudeMinutes = gpsLongitude[1];
                    final RationalNumber gpsLongitudeSeconds = gpsLongitude[2];

                    // This will format the gps info like so:
                    //
                    // gpsLatitude: 8 degrees, 40 minutes, 42.2 seconds S
                    // gpsLongitude: 115 degrees, 26 minutes, 21.8 seconds E

                    System.out.println("    " + "GPS Latitude: " + gpsLatitudeDegrees.toDisplayString() + " degrees, " + gpsLatitudeMinutes.toDisplayString()
                            + " minutes, " + gpsLatitudeSeconds.toDisplayString() + " seconds " + gpsLatitudeRef);
                    System.out.println("    " + "GPS Longitude: " + gpsLongitudeDegrees.toDisplayString() + " degrees, " + gpsLongitudeMinutes.toDisplayString()
                            + " minutes, " + gpsLongitudeSeconds.toDisplayString() + " seconds " + gpsLongitudeRef);

                }

                System.out.println();

                final List<ImageMetadata.ImageMetadataItem> items = jpegMetadata.getItems();
                for (final ImageMetadata.ImageMetadataItem item : items) {
                    System.out.println("    " + "item: " + item);
                }

                System.out.println();
            }
            return new ExifMetadataResponse();
        }

        private static void printTagValue(final JpegImageMetadata jpegMetadata, final TagInfo tagInfo) {
            final TiffField field = jpegMetadata.findEXIFValueWithExactMatch(tagInfo);
            if (field == null) {
                System.out.println(tagInfo.name + ": " + "Not Found.");
            } else {
                System.out.println(tagInfo.name + ": " + field.getValueDescription());
            }
        }
        */

}
