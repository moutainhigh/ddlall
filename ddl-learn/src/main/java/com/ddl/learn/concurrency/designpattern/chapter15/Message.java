package com.ddl.learn.concurrency.designpattern.chapter15;

public class Message {
    private final String value;

    public Message(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}