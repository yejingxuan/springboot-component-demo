package com.yjx.demo.quizzes.threadetudy;

public class TestWait {

    public static void main(String[] args) {
        Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (lock){
                System.out.println("thread1 start");
                try {
                    lock.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 stop");
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 start");
                lock.notify();

                System.out.println("thread2 stop");
            }
        });

        thread1.start();
        //thread2.start();

    }

}
