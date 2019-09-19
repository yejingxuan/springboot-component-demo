package com.yjx.demo.quizzes.thoughtwork.main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class StationQuizze{

    public static void main(String[] args) {
        System.out.println("请输入");

        String outPutStr = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";

        String[] outPutArray = outPutStr.split(",");

        Set<String> items = new HashSet<>();
        Arrays.stream(outPutArray).forEach(s -> {
            String item1 = s.substring(0, 1);
            String item2 = s.substring(1, 2);
            String item3 = s.substring(2, 3);
            items.add(item1);
            items.add(item2);
            System.out.println(item1+","+item2+","+item3);
        });

        Object[] objects =items.toArray();
        System.out.println(Arrays.toString(objects));

        int [][] road = new int[5][5];

        Arrays.stream(outPutArray).forEach(s -> {
            String item1 = s.substring(0, 1);
            String item2 = s.substring(1, 2);
            String item3 = s.substring(2, 3);
            int begin = 0, end = 0;
            for(int i=0; i<objects.length; i++){
                if(objects[i].toString().equals(item1)){
                    begin = i;
                }
                if(objects[i].toString().equals(item2)){
                    end = i;
                }
            }
            road[begin][end] = Integer.parseInt(item3);
        });


    }



}
