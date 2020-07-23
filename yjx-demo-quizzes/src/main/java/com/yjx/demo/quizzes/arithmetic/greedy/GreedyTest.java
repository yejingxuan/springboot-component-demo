package com.yjx.demo.quizzes.arithmetic.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class GreedyTest {

    public static void main(String[] args) {
        /*int[] A = new int[]{2, 0, 4, 1, 2};
        int[] B = new int[]{1, 3, 0, 0, 2};
        Solution tjSaiMa = new Solution();
        System.out.println(Arrays.toString(tjSaiMa.advantageCount(A, B)));*/
        String str = "123";
        StringBuffer sBuffer = new StringBuffer(str);
        sBuffer.reverse();

    }


    static class Solution {
        public int[] advantageCount(int[] A, int[] B) {
            Arrays.sort(A);
            LinkedList<Node> listB = new LinkedList<>();
            for(int i = 0; i < B.length; i++){
                listB.add(new Node(B[i],i));
            }
            Collections.sort(listB, Comparator.comparingInt(n -> n.value));

            // 遍历A即可，将B数组作为输出容器，因为B的信息已经都存在LinkedList里了，这里B数组已经没用了
            for(int i = 0; i < A.length; i++){
                if(A[i] > listB.getFirst().value){
                    B[listB.removeFirst().index] = A[i]; // 对应思想中的（1）
                }else{
                    B[listB.removeLast().index] = A[i]; // // 对应思想中的（2）
                }
            }
            return B;
        }
    }

    static class Node{
        int value;
        int index;
        public Node(int value, int index){
            this.value = value;
            this.index = index;
        }
    }

}
