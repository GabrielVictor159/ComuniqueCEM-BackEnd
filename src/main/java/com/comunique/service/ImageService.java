package com.comunique.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.comunique.functions.ImageResizer;

@Service
public class ImageService {

    private static final String ROOT = "src/main/resources/static/images/";

    public void persistir(MultipartFile image, String folder) throws IOException {
        if (image.getContentType() == "image/jpeg" || image.getContentType() == "image/png") {
            byte[] bytes = ImageResizer.resizeImage(image, 1000);
            Path path = Paths.get(folder + image.getOriginalFilename());
            if (!Files.exists(Paths.get(folder))) {
                Files.createDirectories(Paths.get(folder));
            }

            Files.write(path, bytes);
        } else {
            Path path = Paths.get(folder + image.getOriginalFilename());
            if (!Files.exists(Paths.get(folder))) {
                Files.createDirectories(Paths.get(folder));
            }

            Files.write(path, image.getBytes());
        }

    }

    public void excluir(String nomeImagem, String folder) throws IOException {
        Path path = Paths.get(folder + nomeImagem);
        Files.deleteIfExists(path);
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
