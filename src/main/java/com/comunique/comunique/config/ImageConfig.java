package com.comunique.comunique.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageConfig {
    @Value("${file.upload.path}")
    private String uploadPath;

    public String getUploadPath() {
        return uploadPath;
    }
}
