package com.ddl.concurrency.designpattern.future;


import java.util.concurrent.TimeUnit;

/**
 * Future        ->代表的是未来的一个凭据
 * FutureTask    ->将你的调用逻辑进行了隔离
 * FutureService ->桥接 Future和 FutureTask
 */
public class SyncInvoker {

    public static void main(String[] args) throws InterruptedException {
        /*String result = get();
        System.out.println(result);*/

        FutureService futureService = new FutureService();
        Future<String> future = futureService.submit(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISH";
        },System.out::println);
        System.out.println(future);

        //System.out.println(future.get());

        System.out.println("===========");
        System.out.println(" do other thing.");
        //Thread.sleep(1000);
        System.out.println("===========");


    }

    private static String get()
            throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(10000L);
        return "FINISH";
    }
}