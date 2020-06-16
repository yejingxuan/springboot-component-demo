package com.yjx.demo.quizzes.threadetudy;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadCreateTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadOne threadOne = new ThreadOne();
        Thread threadTwo = new Thread(new RunnableTwo());

        FutureTask<String> futureTask = new FutureTask<>(new CallableThree());
        Thread threadThree = new Thread(futureTask);

        threadOne.start();
        threadTwo.start();
        threadThree.start();

        System.out.println(futureTask.get());

        AtomicInteger atomicInteger = new AtomicInteger(100);
        atomicInteger.getAndAdd(13);
        System.out.println(atomicInteger.get());

    }


    static class ThreadOne extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+": ThreadOne do it");
        }
    }


    static class RunnableTwo implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+": RunnableTwo do it");
        }
    }


    static class CallableThree implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName()+": CallableThree do it");
            return "I am CallableThree";
        }
    }

}
