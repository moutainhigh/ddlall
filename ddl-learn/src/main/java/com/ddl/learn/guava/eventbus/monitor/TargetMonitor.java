package com.ddl.learn.guava.eventbus.monitor;


public interface TargetMonitor {

    void startMonitor() throws Exception;

    void stopMonitor() throws Exception;
}
