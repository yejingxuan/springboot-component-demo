package com.yjx.demo.threads.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    public int total = 103;

    public static void main(String[] args) {
        ThreadPoolTest poolTest = new ThreadPoolTest();
        //poolTest.tsetFixThreadPool();
        poolTest.testThreadSafe();

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 2, 1, TimeUnit.MINUTES,
                new LinkedBlockingDeque<>());

    }

    public void testThreadSafe() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        System.out.println(total);
        while (total > 0) {
            fixedThreadPool.execute(() -> {

                /*try {
                    //Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }*/

                System.out.println(Thread.currentThread().getName() + ":" + total + "：" + System
                        .currentTimeMillis());
                total--;
            });
        }

    }

    public void tsetFixThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < total; i++) {
            final int a = i;
            fixedThreadPool.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":" + a);
            });
        }
    }

}
