package com.yjx.demo.quizzes.threadetudy;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(2);
        OpenThread openThread = new OpenThread(latch);
        PutThread putThread = new PutThread(latch);
        openThread.start();
        putThread.start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("close ice-box");
    }

    static class OpenThread extends Thread {
        private CountDownLatch latch;
        public OpenThread(CountDownLatch latch){
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println("open ice-box");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }
    }

    static class PutThread extends Thread {
        private CountDownLatch latch;
        public PutThread(CountDownLatch latch){
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println("put elephant");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }
    }

}
