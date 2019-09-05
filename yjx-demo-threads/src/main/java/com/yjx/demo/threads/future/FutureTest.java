package com.yjx.demo.threads.future;

import com.yjx.demo.threads.model.Fruit;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FutureTest {

    private ExecutorService executorService;

    public FutureTest(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public static void main(String[] args) {
        FutureTest futureTest = new FutureTest(Executors.newFixedThreadPool(2));
        futureTest.method1();
        futureTest.method2();
    }


    /**
     * 异步执行，执行完后函数再返回，并设置超时时间
     */
    private void method1() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": hello");
        }, executorService);
        try {
            future.get(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": hello");
    }



    /**
     * 异步执行，执行完返回执行结果，并设置超时时间
     */
    private void method2() {
        CompletableFuture<List<Fruit>> listCompletableFuture = CompletableFuture
                .supplyAsync(() -> this.createFruits(), executorService);
        List<Fruit> fruits = null;
        try {
            fruits = listCompletableFuture.get(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(fruits.toString());
    }

    private List<Fruit> createFruits() {
        List<Fruit> fruits = Arrays.asList(new Fruit("apple"), new Fruit("orange"));
        System.out.println(Thread.currentThread().getName() + ": hello");
        return fruits;
    }





}
