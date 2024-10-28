package com.MetaImage.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Data
public class ExifMetadataResponse {
    private Map<String, String> metadata;
    public ExifMetadataResponse() {
        this.metadata = new HashMap<>();
    }

    public void addMetadata(String key, String value) {
        if (!metadata.containsKey(key)) {
            this.metadata.put(key, value);
        }
    }

    @Override
    public String toString() {
        return "ExifMetadataResponse{" +
                "metadata=" + metadata +
                '}';
    }
}
