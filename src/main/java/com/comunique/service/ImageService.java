package com.comunique.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashSet;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.comunique.functions.ImageResizer;

@Service
public class ImageService {

    private static final String ROOT = "src/main/resources/static/images/";

    public void persistir(MultipartFile image, String folder) throws IOException {
        if (image.getContentType().equals("image/jpeg") || image.getContentType().equals("image/png")) {
            byte[] bytes = ImageResizer.resizeImage(image, 1000);
            String fileName = image.getOriginalFilename();
            Path path = Paths.get(folder);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            Files.write(path.resolve(fileName), bytes);
        } else {
            String fileName = image.getOriginalFilename();
            Path path = Paths.get(folder + fileName);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            Files.write(path.resolve(fileName), image.getBytes());
        }
    }

    public void excluir(String folder) throws IOException {
        Path path = Paths.get(folder);
        Files.deleteIfExists(path);
        Path parentPath = path.getParent();

        while (Files.isDirectory(parentPath) && Files.list(parentPath).count() == 0 && parentPath.getNameCount() > 1) {
            Files.delete(parentPath);
            parentPath = parentPath.getParent();
        }
    }

    public void excluirPasta(String folder) throws IOException {
        Path path = Paths.get(folder);
        Files.deleteIfExists(path);
    }

    public void criarPastaDeImagens(String folder) throws IOException {
        Path path = Paths.get(folder);
        Files.createDirectory(path);
    }

    public String removePath(String path) {
        int lastSlashIndex = path.lastIndexOf("/");
        return path.substring(lastSlashIndex + 1, path.length());
    }

    public String removeNameForPath(String imagePath) {
        int index = imagePath.lastIndexOf("/");
        return imagePath.substring(0, index + 1);
    }
}
