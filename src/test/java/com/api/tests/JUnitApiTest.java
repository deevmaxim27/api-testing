package com.api.tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;

public class JUnitApiTest {

    @BeforeEach
    void setup(){
        System.out.println("Начало нового теста!");
    }

    @AfterEach
    void tearDown(){
        System.out.println("Тест завершен.");
    }

    @Test
    void getPostById(){
        given()
                .pathParam("id", 10)
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/{id}")
                .then()
                .statusCode(200);
    }

    @Test
    void getNonExistingResource(){
        given()
                .pathParam("id", 150)
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/{id}")
                .then()
                .statusCode(404);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 20, 50})
    void checkExistingPostsParameterized(int postId){
        given()
                .pathParam("id", postId)
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/{id}")
                .then()
                .statusCode(200);
    }
}


