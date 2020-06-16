package com.yjx.demo.quizzes.test;

import com.yjx.demo.quizzes.test.TestInnerClass.Dog;

public class TestInnerClass2 {


    public static void main(String[] args) {

        TestInnerClass innerClass = new TestInnerClass();
        Dog dog = innerClass.new Dog();
        dog.doEat();
    }



}
