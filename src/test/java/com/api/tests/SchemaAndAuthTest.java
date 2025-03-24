package com.api.tests;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaAndAuthTest {

    static RequestSpecification authSpec;

    @BeforeAll
    static void setAuthSpec(){
        authSpec = given()
                .baseUri("https://gorest.co.in/public/v2")
                .header("Authorization", "Bearer 2d7cd3a96bb433d097467abaa4191a0a4a58e5dd9c7c3622e8ae33d51d072601")
                .log().all();
    }

    static RequestSpecification requestSpec;

    @BeforeAll
    static void setupURI(){
        baseURI = "https://jsonplaceholder.typicode.com";
        basePath = "/posts";
    }

    @BeforeAll
    static void createRequestSpec() {
        requestSpec = given()
                .baseUri(baseURI)
                .basePath(basePath)
                .header("Content-Type", "application/json")
                .log().all();
    }
    static ResponseSpecification responseSpec;

    @BeforeAll
    static void createResponseSpec() {
        responseSpec = expect()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }


    @BeforeEach
    void setup(){
        System.out.println("Начало нового теста!");
    }

    @AfterEach
    void tearDown(){
        System.out.println("Тест завершен.");
    }
    @Test
    void validateJsonSchema(){
        given()
                .spec(requestSpec)
                .pathParam("id", 7)
                .when()
                .get("/{id}")
                .then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("post-schema.json"));
    }

    @Test
    void authorizedRequest(){
        given()
                .spec(authSpec)
                .when()
                .get("/users")
                .then()
                .statusCode(200);
    }
}
