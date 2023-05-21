package com.task1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {

    private String message;
    public Message() {
    }

    public Message(String message) {
        this.message = message;
    }

    public String getName() {
        return message;
    }

    @JsonProperty("message")
    public void setName(String message) {
        this.message = message;
    }


}
