package com.yjx.demo.quizzes.test;

import java.security.PublicKey;

public class TestInnerClass {

    public TestInnerClass() {
        System.out.println("init TestInnerClass");
    }

    public static void main(String[] args) {
        TestInnerClass innerClass = new TestInnerClass();


        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).run();

        innerClass.dogEat();
    }


    public void dogEat(){
        new Eat(){
            @Override
            public void doEat() {
                System.out.println("dog eat a shit");
            }
        }.doEat();

        Dog dog = new Dog();
        dog.doEat();
    }


    public class Dog{
        public void doEat(){
            System.out.println("dog eat a shit class");
        }
    }


    public interface Eat {
        void doEat();
    }

}
