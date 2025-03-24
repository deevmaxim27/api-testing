package com.api.tests;

import org.junit.jupiter.api.Test;
import com.api.models.Post;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

public class AdvancedApiTest {

    @Test
    void getPostsByUserId(){
        given()
                .queryParam("userId", 3)
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200);
    }

    @Test
    void getPostById(){
        given()
                .pathParams("id", 5)
                .when()
                        .get("https://jsonplaceholder.typicode.com/posts/{id}")
                        .then()
                        .statusCode(200);
    }

    @Test
    void createPostWithSerialization(){
        Post newPost = new Post("Заголовок", "Содержимое поста", 5);

        given()
                .contentType(ContentType.JSON)
                .body(newPost)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(201);
    }
}
