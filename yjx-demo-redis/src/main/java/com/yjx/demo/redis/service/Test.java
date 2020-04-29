package com.yjx.demo.redis.service;

import java.util.Collections;
import java.util.Optional;

public class Test {

    private Test a;

    public static void main(String[] args) {
        Test item = new Test();
        //item.a = new Test();
        Optional.ofNullable(item.a.a).ifPresent(test -> System.out.println("test is not null"));
        System.out.println("test is null");
    }


    public static void add() {
        String item = "abc";
        //字符串末位补零
        String join = String.join("", Collections.nCopies(8 - item.length(), "0"));

        System.out.println(item.concat(join));
    }
}
