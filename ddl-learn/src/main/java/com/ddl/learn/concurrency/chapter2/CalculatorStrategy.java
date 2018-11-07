package com.ddl.learn.concurrency.chapter2;


@FunctionalInterface
public interface CalculatorStrategy {

    double calculate(double salary, double bonus);
}
