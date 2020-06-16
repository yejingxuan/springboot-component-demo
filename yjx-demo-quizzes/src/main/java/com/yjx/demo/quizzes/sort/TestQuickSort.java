package com.yjx.demo.quizzes.sort;

import java.util.Arrays;

public class TestQuickSort {

    public static void main(String[] args) {
        int[] arrs = new int[]{3, 5, 7, 1, 8, 6};
        quickSort(arrs, 0, arrs.length - 1);
    }


    public static void quickSort(int[] arrs, int start, int end) {
        if (start >= end) {
            return;
        }
        partition(arrs, start, end);

    }


    public static void partition(int[] arrs, int start, int end) {
        int pivot = arrs[start];

        while (start <= end){
            if(pivot<=arrs[end]){
                end--;
                continue;
            }

            if(pivot>arrs[start]){
                start++;
                continue;
            }

            int temp = arrs[start];
            arrs[start] = arrs[end];
            arrs[end] = arrs[start];

            System.out.println(Arrays.toString(arrs));
        }

    }

}
