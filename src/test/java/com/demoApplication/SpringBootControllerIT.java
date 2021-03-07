package com.demoApplication;

import org.junit.Before;

//import com.demoApplication.Application;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@DirtiesContext
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@EmbeddedKafka(topics = "kafka-learn-sample", partitions = 1, controlledShutdown = false)
public class SpringBootControllerIT {

    @LocalServerPort
    private int port;


    @Before
    public void setUp() throws Exception {
        RestAssured.baseURI = "http://127.0.0.1:" + port;
        RestAssured.port = port;
    }

    @Test
    public void testSendMessageSwaggerApi() {
        RestAssured.given()
        .log()
        .all()
        .proxy("192.168.0.34", Integer.valueOf("8080"))
        //.queryParams("message", "firstParameterValue")
        .contentType(ContentType.JSON)
        .when()
        .get("/hello")
        .then().assertThat().statusCode(200);
    }

}
