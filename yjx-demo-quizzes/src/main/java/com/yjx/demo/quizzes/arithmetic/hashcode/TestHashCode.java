package com.yjx.demo.quizzes.arithmetic.hashcode;

public class TestHashCode {

    public static void main(String[] args) {

        TestHashCode testHashCode = new TestHashCode();
        TestHashCode testHashCode2 = new TestHashCode();
        TestHashCode testHashCode3 = testHashCode2;
        System.out.println(testHashCode.hashCode());
        System.out.println(testHashCode2.hashCode());
        System.out.println(testHashCode3.hashCode());
    }

}
