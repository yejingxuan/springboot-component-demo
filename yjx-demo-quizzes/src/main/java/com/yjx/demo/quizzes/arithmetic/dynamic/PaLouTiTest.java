package com.yjx.demo.quizzes.arithmetic.dynamic;

import java.util.concurrent.atomic.AtomicInteger;

public class PaLouTiTest {

    public static void main(String[] args) {

        new AtomicInteger(10);

        Palouti palouti = new Palouti();
        System.out.println(palouti.getRes(4));

    }

    static class Palouti{
        public int getRes(int n){
            if (n == 1) {
                return 1;
            }
            int[] dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }
    }

}
