package com.task1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageProcessing {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void printMessage(String username) {
        Message message = createMessage(username);
        logger.debug("Processing message format");
        String outputFormat = System.getProperty("format");
        try {
            if (isJSON(outputFormat)) {
                printJson(message);
            } else if (isXML(outputFormat)) {
                printXml(message);
            } else {
                logger.warn("Allowed only JSON or XML format");
            }
        } catch (NullPointerException | JsonProcessingException e) {
            logger.warn("You need to enter a message input format -Dformat=json or -Dformat=xml " + e.getMessage());
        }
    }

    private static void printXml(Message message) throws JsonProcessingException {
        ObjectMapper objectMapper = new XmlMapper();
        logger.debug(objectMapper.writeValueAsString(message));
        logger.debug("XML message was created");
    }

    private static void printJson(Message message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.debug(objectMapper.writeValueAsString(message));
        logger.debug("JSON message was created");
    }

    public static Message createMessage(String username) {
        Message message = new Message();
        String name = FileProcessing.readProperty();
        if (name != null) {
            message.setName("Привіт, " + name + "!");
            logger.info("Setting message {} ", message.getName());
            return message;
        } else {
            logger.error("No key {}", username);
            return new Message("File properties has not key = " + username);
        }
    }

    private static boolean isXML(String outputFormat) {
        return outputFormat.equals("xml");
    }

    private static boolean isJSON(String outputFormat) {
        return outputFormat.equals("json");
    }
}
