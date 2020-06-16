package com.yjx.demo.quizzes.test;

public interface InterfacrA1 {

    void eat();

    default void eat2(){
        System.out.println(" A1 eat2");
    }

}
