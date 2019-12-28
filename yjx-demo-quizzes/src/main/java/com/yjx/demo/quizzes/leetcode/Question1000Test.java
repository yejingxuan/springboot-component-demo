package com.yjx.demo.quizzes.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.Test;

public class Question1000Test {

    @Test
    public void testInitData(){
        Question1000 question1000 = new Question1000();
        List<String> roads = new ArrayList<>();
        roads.add("AB5");
        roads.add("BC4");
        roads.add("AC7");
        Set<String> keys = question1000.initData(roads);
        System.out.println(keys);
    }

}
