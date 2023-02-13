package com.comunique.functions;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

public class ImageResizer {
    public static byte[] resizeImage(MultipartFile image, int size) throws IOException {
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(image.getBytes()));
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();

        int width = size;
        int height = size;
        if (originalWidth > originalHeight) {
            height = (int) (originalHeight * (1.0 * size / originalWidth));
        } else {
            width = (int) (originalWidth * (1.0 * size / originalHeight));
        }

        BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String formatName = image.getContentType().split("/")[1];
        ImageIO.write(resizedImage, formatName, baos);
        return baos.toByteArray();
    }
}
