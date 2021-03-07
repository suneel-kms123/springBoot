package com.demoApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootControllerTest {

    @Test
    public void testRestApi() {
        RestAssured.given().get("/hello").then().assertThat().statusCode(200);
    }
    
}
