package com.yjx.demo.quizzes.test;

public class TestInterface implements InterfacrA1, InterfacrA2 {

    public static void main(String[] args) {
        TestInterface testInterface = new TestInterface();
        testInterface.eat();
    }

    @Override
    public void eat() {
        System.out.println("eat");
    }

    @Override
    public void eat2() {

    }

}
