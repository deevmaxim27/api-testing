package com.api.tests;

import com.api.utils.EnvironmentManager;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

@Epic("API-тестирование")
@Feature("Посты")
public class AllureReportTest {

    @BeforeAll
    static void setup() {
        io.restassured.RestAssured.baseURI = EnvironmentManager.get("base.url");
        io.restassured.RestAssured.basePath = EnvironmentManager.get("base.path");
    }

    @Test
    @Story("Проверка существующего поста")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка получения поста с id=1 и статусом 200, добавление ответа как вложения")
    void test_allure_report() throws IOException {
        int postId = 1;

        Response response = openPostById(postId);
        response.then().statusCode(200);

        // Добавляем вложение в Allure
        attachResponseAsFile(response, "post_" + postId + "_response.json");

        checkPostId(response, postId);
    }

    @Step("Открываем пост по id = {postId}")
    public Response openPostById(int postId) {
        return given()
                .pathParam("id", postId)
                .when()
                .get("/{id}");
    }

    @Step("Проверяем, что id поста равен {expectedId}")
    public void checkPostId(Response response, int expectedId) {
        response.then().body("id", equalTo(expectedId));
    }

    @Attachment(value = "{filename}", type = "application/json")
    public byte[] attachResponseAsFile(Response response, String filename) {
        return response.getBody().asByteArray();
    }
}


