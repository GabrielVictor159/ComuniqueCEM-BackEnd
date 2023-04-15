package com.comunique.ControllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.comunique.controller.AdminMasterController;
import com.comunique.functions.Reflections;
import com.comunique.model.AdminsMaster;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AdminMasterControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void testLogin() throws NoSuchMethodException {
        String loginUrl = Reflections.getURI(AdminMasterController.class, "Login");
        ResponseEntity<AdminsMaster> response = testRestTemplate.getForEntity(loginUrl,
                AdminsMaster.class,
                AdminMasterExample.nome,
                AdminMasterExample.senha);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ResponseEntity<AdminsMaster> response2 = testRestTemplate.getForEntity(loginUrl,
                AdminsMaster.class,
                AdminMasterExample.nome,
                AdminMasterExample.senha + "5");
        assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
    }
}
