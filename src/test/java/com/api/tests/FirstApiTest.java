package com.api.tests;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class FirstApiTest {

    @Test
    void firstGetTest(){
        given().
                when().
                get("https://jsonplaceholder.typicode.com/posts/1").
                then().
                statusCode(200);
    }
    @Test
    void getNonExistingResource(){
        given().
                when().
                get("https://jsonplaceholder.typicode.com/posts/0").
                then().
                statusCode(404);
    }
    @Test
    void getListOfPosts(){
        given().
                when().
                get("https://jsonplaceholder.typicode.com/posts").
                then().
                statusCode(200);
    }


}

