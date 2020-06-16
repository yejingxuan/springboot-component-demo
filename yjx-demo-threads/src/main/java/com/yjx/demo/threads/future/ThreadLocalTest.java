package com.yjx.demo.threads.future;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTest {

    static class Test{

    }

    public static void main(String[] args) {
        ThreadLocal<String> locals = new ThreadLocal<>();
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        List<String> logs = new ArrayList<>();
        /*for(int i = 0; i<1000; i++){
            logs.add(String.valueOf(i));
        }

        logs.forEach(s -> {
            final String a =s;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("next--"+Thread.currentThread()+"："+a);
            });
        });*/

        for (int i=0; i<30; i++){
            final int a =i;
            threadPool.execute(() -> {
                locals.set(String.valueOf(a));
                System.out.println("next--"+Thread.currentThread()+"："+locals.get());
            });
        }


    }

}
