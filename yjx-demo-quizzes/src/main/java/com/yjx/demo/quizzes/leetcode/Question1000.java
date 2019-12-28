package com.yjx.demo.quizzes.leetcode;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question1000 {

    public Set<String> initData(List<String> roads) {
        Set<String> keys = new HashSet<>();

        for (String road : roads) {
            String from = road.substring(0, 1);
            String to = road.substring(1, 2);
            String length = road.substring(2, 3);

            keys.add(from);
            keys.add(to);
        }

        return keys;
    }





    public static void main(String[] args) {

    }
}
