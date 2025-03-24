package com.api.tests;

import org.junit.jupiter.api.*;

class JunitExampleTest {

    @BeforeAll
    static void setupAll(){
        System.out.println("Выполнится 1 раз ПЕРЕД всеми тестами");
    }

    @BeforeEach
    void setup(){
        System.out.println("Выполнится ПЕРЕД каждым тестом");
    }

    @Test
    void firstTest(){
        System.out.println("Первый тест выполнен");
        Assertions.assertEquals(2, 1 + 1);
    }

    @Test
    void secondTest(){
        System.out.println("Второй тест выполнен");
        Assertions.assertTrue("Максим".contains("ак"));
    }

    @AfterEach
    void tearDown(){
        System.out.println("Выполнится ПОСЛЕ каждого теста");
    }

    @AfterAll
    static void tearDownAll(){
        System.out.println("Выполнится 1 раз ПОСЛЕ всех тестов");
    }
}

