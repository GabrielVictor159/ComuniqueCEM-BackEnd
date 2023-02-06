package com.comunique.comunique.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.comunique.comunique.config.ImageConfig;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

@Service
public class ImageService {
    private final String uploadPath;

    public ImageService(ImageConfig imageConfig) {
        this.uploadPath = imageConfig.getUploadPath();
    }

    public void saveImage(MultipartFile file, String fileName) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(uploadPath + fileName);
        Files.write(path, bytes);
    }

    public Resource getImage(String fileName) throws FileNotFoundException, MalformedURLException {
        Path path = Paths.get(uploadPath + fileName).toAbsolutePath();
        Resource resource = new UrlResource(path.toUri());
        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new FileNotFoundException("Could not read file: " + fileName);
        }
    }
}
