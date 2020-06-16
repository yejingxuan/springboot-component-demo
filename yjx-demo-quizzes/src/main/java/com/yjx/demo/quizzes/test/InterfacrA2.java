package com.yjx.demo.quizzes.test;

public interface InterfacrA2 {

    void eat();

    default void eat2(){
        System.out.println("A2 eat2");
    }

}
