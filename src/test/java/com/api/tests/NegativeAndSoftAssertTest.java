package com.api.tests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;
import static io.restassured.RestAssured.*;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.basePath;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class NegativeAndSoftAssertTest {
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
    void NegativeTest(){
        given()
                .spec(requestSpec)
                .pathParam("id", -10)
                .when()
                .get("/{id}")
                .then()
                .statusCode(404);
    }
    @Test
    void softAssertionsTest(){
        String title =
        given()
                .spec(requestSpec)
                .pathParam("id", 2)
                .when()
                .get("/{id}")
                .then()
                .spec(responseSpec)
                .extract().path("title");

        SoftAssertions Soft = new SoftAssertions();
        Soft.assertThat(title).contains("qui est esse");
        Soft.assertThat(title).startsWith("qui");
        Soft.assertThat(title).endsWith("esse");
        Soft.assertThat(title).isNotEmpty();
        Soft.assertAll();
    }

    @Test
    void exceptionHandlingExample(){
        try {
            given()
                    .baseUri("https://invalid.jsonplaceholder.typicode.com") // некорректный домен
                    .when()
                    .get("/invalid_endpoint")
                    .then()
                    .statusCode(404);
        } catch (Exception e) {
            System.out.println("Произошла ошибка запроса: " + e.getMessage());
            Assertions.fail("Тест завершился с исключением!");
        }
    }
}
