package com.yjx.demo.quizzes.thoughtwork.main;

import com.yjx.demo.quizzes.thoughtwork.model.Mon;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test.method4());
    }

    public String method4(){
        System.out.println(Thread.currentThread().getName());
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("start " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("expection");
            }
            System.out.println("end " + Thread.currentThread().getName());
        });
        try {
            future.get(4000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    public static void method3() {
        String idCard = "11101000000000111X";
        String REGEX_ID_CARD = "\\d{15}(\\d{2}[0-9X])?";
        String REGEX_ID_CARD2 = "\\d{17}([0-9X])?";

        System.out.println(Pattern.matches(REGEX_ID_CARD, idCard));
        System.out.println(Pattern.matches(REGEX_ID_CARD2, idCard));
    }

    public static void method1() {
        String fileName = "张三_男性_在逃人员_1994.jpg";
        System.out.println(fileName.substring(0, fileName.lastIndexOf(".")));
    }

    public static void method2() {
        List<Mon> mons = Arrays.asList(new Mon("001", 1), new Mon("002", 1), new Mon("003", 0));

        System.out.println(mons);

        Map<Integer, List<Mon>> group = mons.stream()
                .collect(Collectors.groupingBy(Mon::getResource));
        System.out.println(group);
    }

}
