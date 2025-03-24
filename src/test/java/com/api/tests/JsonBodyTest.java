package com.api.tests;

import org.junit.jupiter.api.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;
import static io.restassured.RestAssured.*;

public class JsonBodyTest {
    @BeforeEach
    void setup(){
        System.out.println("Начало нового теста!");
    }

    @AfterEach
    void tearDown(){
        System.out.println("Тест завершен.");
    }
    @Test
    void validateJsonBody(){
        given()
                .pathParam("id", 3)
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/{id}")
                .then()
                .statusCode(200)
                .body("userId", equalTo(1))
                .body("id", equalTo(3))
                .body("title", containsString("ea molestias quasi exercitationem"));
    }
    @Test
    void validateJsonArraySize(){
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200)
                .body("size()", equalTo(100));
    }
    @Test
    void extractValueFromJson(){
        String title =
                given()
                        .pathParam("id", 5)
                        .when()
                        .get("https://jsonplaceholder.typicode.com/posts/{id}")
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("title");

        System.out.println("Title поста с id=5: " + title);
    }
}
