package com.ddl.learn.concurrency.designpattern.threadlocalstorage;

import java.util.stream.IntStream;

public class ContextTest {
    public static void main(String[] args) {
        IntStream.range(1, 5)
                .forEach(i ->
                        new Thread(new ExecutionTask()).start()
                );
    }
}