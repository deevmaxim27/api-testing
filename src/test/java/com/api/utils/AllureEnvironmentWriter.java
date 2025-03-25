package com.api.utils;

import io.qameta.allure.Allure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class AllureEnvironmentWriter {

    public static void write(Map<String, String> environment) {
        File file = new File("target/allure-results/environment.properties");
        try (FileWriter writer = new FileWriter(file)) {
            for (Map.Entry<String, String> entry : environment.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

