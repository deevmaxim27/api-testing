package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvironmentManager {

    private static final Properties properties = new Properties();

    static {
        String env = System.getProperty("env", "dev"); // если не задано — по умолчанию "dev"
        String fileName = String.format("environment-%s.properties", env);
        try (InputStream input = EnvironmentManager.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input != null) {
                properties.load(input);
            } else {
                throw new RuntimeException("Не найден файл: " + fileName);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке: " + fileName, e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
