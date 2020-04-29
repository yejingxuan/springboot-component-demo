package com.yjx.demo.quizzes.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

/**
 * [2,0,4,1,2]
 * [1,3,0,0,2]
 */
public class Question870 {

    public static void main(String[] args) {
        Question870 question = new Question870();
        int[] A = new int[]{2,0,4,1,2};
        int[] B = new int[]{1,3,0,0,2};
        //int[] res = question.advantageCount(A, B);
        int[] res = question.advantageCount2(A, B);


        System.out.println(Arrays.toString(res));
    }

    private int[] advantageCount2(int[] A, int[] B) {
        int[] res = new int[A.length];

        Arrays.sort(A);
        LinkedList<Node> listB = new LinkedList<>();
        for(int i = 0; i < B.length; i++){
            listB.add(new Node(B[i],i));
        }
        Collections.sort(listB,new Comparator<Node>(){
            public int compare(Node n1, Node n2){
                return n1.value - n2.value;
            }
        });

        // 遍历A即可，将B数组作为输出容器，因为B的信息已经都存在LinkedList里了，这里B数组已经没用了
        for(int i = 0; i < A.length; i++){
            if(A[i] > listB.getFirst().value){
                Node node = listB.removeFirst();
                res[node.index] = A[i];
            }else{
                Node node = listB.removeLast();
                res[node.index] = A[i];
            }
        }
        return res;
    }

    class Node{
        int value;
        int index;
        public Node(int value, int index){
            this.value = value;
            this.index = index;
        }
    }


    public int[] advantageCount(int[] A, int[] B) {
        Arrays.sort(A);
        int[] res = new int[A.length];

        Map<Integer, Integer> mapA = new LinkedHashMap<>();
        for(int i=0 ; i<B.length; i++){
            mapA.put(i, A[i]);
        }

        for (int i=0 ; i<B.length; i++){
            Integer target = null;
            Integer index = null;

            for (Entry<Integer, Integer> entry: mapA.entrySet()){
                if (entry.getValue() > B[i]) {
                    target = entry.getValue();
                    break;
                }
            }

            if(target == null){
                index = mapA.keySet().iterator().next();
                target = mapA.get(index);

            }
            mapA.remove(index);

            res[i] = target;
        }

        return res;

    }

}
