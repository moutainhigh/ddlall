package com.ddl.guava.eventbus.monitor;


public interface TargetMonitor {

    void startMonitor() throws Exception;

    void stopMonitor() throws Exception;
}
