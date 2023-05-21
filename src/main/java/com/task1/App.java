package com.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private final static String USERNAME = "username";

    public static void main(String[] args) {

        logger.info("Program was started");

        MessageProcessing.printMessage(USERNAME);

        logger.info("Program was finished");
    }
}
