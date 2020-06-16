package com.yjx.demo.quizzes.threadetudy;

import java.util.concurrent.atomic.AtomicInteger;

public class TestInterrupted {

    public static void main(String[] args) {
        AtomicInteger flag = new AtomicInteger(1000);
        System.out.println("start : " + flag.get());
        Thread thread1 = new Thread(() -> {
            while (!Thread.interrupted()){
                flag.getAndDecrement();
                int value = flag.get();
                System.out.println(value);
            }
        });

        thread1.start();

        while (true){
            if(flag.get() == 0){
                thread1.interrupt();
                System.out.println("中断");
                return;
            }
        }

    }

}
