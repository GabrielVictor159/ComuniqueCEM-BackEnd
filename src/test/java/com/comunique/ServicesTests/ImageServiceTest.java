package com.comunique.ServicesTests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import com.comunique.service.ImageService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ImageServiceTest {

    @Autowired
    ImageService imageService;

    private Path imagePath = Paths.get("src/test/resources/images/test.jpg");
    private String folder = "src/test/resources/test";

    @After
    public void setDown() throws IOException {
        imageService.excluirPasta(folder);
    }

    @Test
    public void testPersistir() throws IOException {
        Path imagePath = Paths.get("src/test/resources/images/test.jpg");
        assertTrue(Files.exists(imagePath));
        byte[] content = Files.readAllBytes(imagePath);
        MockMultipartFile image = new MockMultipartFile("test.jpg", "test.jpg", "image/jpeg", content);
        assertTrue(image != null);
        String folder = "src/test/resources/test";
        imageService.persistir(image, folder);
        Path path = Paths.get(folder + image.getOriginalFilename());
        assertTrue(Files.exists(path));
        Files.delete(path);
    }

    @Test
    public void testExcluir() throws IOException {
        Path imagePath = Paths.get("src/test/resources/images/test.jpg");
        assertTrue(Files.exists(imagePath));
        byte[] content = Files.readAllBytes(imagePath);
        MockMultipartFile image = new MockMultipartFile("test.jpg", "test.jpg", "image/jpeg", content);
        assertTrue(image != null);
        String folder = "src/test/resources/test";
        imageService.persistir(image, folder);
        Path path = Paths.get(folder + image.getOriginalFilename());
        assertTrue(Files.exists(path));

        imageService.excluir(folder + image.getOriginalFilename());
        assertFalse(Files.exists(path));
    }

}
