package com.api.tests;

import org.junit.jupiter.api.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;
import static io.restassured.RestAssured.*;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.basePath;


public class LoggingAndSpecTest {

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
    void validateJsonBody(){
        given()
                .spec(requestSpec)
                .pathParam("id", 3)
                .when()
                .get("/{id}")
                .then()
                .spec(responseSpec)
                .body("id", equalTo(3));
    }
    @Test
    void validateJsonArraySize(){
        given()
                .spec(requestSpec)
                .when()
                .get("")
                .then()
                .spec(responseSpec)
                .body("size()", equalTo(100));
    }
}
