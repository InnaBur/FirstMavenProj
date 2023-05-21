package com.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

public class FileProcessing {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private static final String FILE_NAME = "config.properties";

    public static String readProperty() {
        Properties properties = new Properties();
        String name = "";
        try {
            name = readPropertyOutOfJar(properties);
            if (Objects.equals(name, "")) {
                readPropertyIntoJar(properties);
            }
        } catch (NullPointerException | IOException e) {
            logger.warn("Properties were not loaded {}", e.getMessage(), e);
        }
        return name;
    }

    private static void readPropertyIntoJar(Properties properties) throws IOException {
        logger.debug("Finding properties file into jar");
        InputStream inputStream = App.class.getClassLoader().getResourceAsStream(FILE_NAME);
        if (inputStream != null) {
            logger.debug("Properties file into jar was found");
            loadingProperties(inputStream, properties);
        } else {
            logger.error("Properties into jar were not loaded ");
        }
    }

    private static String readPropertyOutOfJar(Properties properties) throws IOException {
        logger.debug("Finding properties file out of jar");
        InputStream inputStream = new FileInputStream(FILE_NAME);
        loadingProperties(inputStream, properties);
        return properties.getProperty("username");
    }

    private static void loadingProperties(InputStream inputStream, Properties properties) throws IOException {
        Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        properties.load(reader);
        logger.debug("Properties were loaded");
        inputStream.close();
    }
}
