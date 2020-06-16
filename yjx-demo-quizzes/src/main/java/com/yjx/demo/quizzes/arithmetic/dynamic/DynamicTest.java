package com.yjx.demo.quizzes.arithmetic.dynamic;

public class DynamicTest {

    public static void main(String[] args) {
        int[] a1 = {-2,1,-3,4,-1,2,1,-5,4};
        int[] a2 = {1, -2, 3, 10, -4, 7, 2, -5};
        System.out.println(getMaxSum(a1));
        //System.out.println(getMaxSum1(a1));
        //System.out.println(getMaxSum(a2));
    }

    //
    public static int getMaxSum(int[] arr) {
        int res = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            System.out.println("i:"+i+", max:"+max+",res:"+res);
            max = Math.max(max + arr[i], arr[i]);
            res = Math.max(res, max);
        }
        return res;
    }

    public static int getMaxSum1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int res = arr[0];
        int max = arr[0];
        int lt = 0;
        int rt = 0;
        int l = 0;
        int r = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max + arr[i]) {
                max = arr[i];
                lt = i;
                rt = i;
            } else {
                max = max + arr[i];
                rt++;
            }
            if (max > res) {
                res = max;
                r = rt;
                l = lt;
            }
        }
        System.out.println(arr[l] + " " + arr[r]);
        return res;
    }

}
