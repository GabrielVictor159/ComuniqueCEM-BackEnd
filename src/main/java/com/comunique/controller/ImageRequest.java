package com.comunique.controller;

import org.springframework.web.multipart.MultipartFile;

public class ImageRequest {
    private MultipartFile image;

    public ImageRequest(MultipartFile image) {
        this.image = image;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
