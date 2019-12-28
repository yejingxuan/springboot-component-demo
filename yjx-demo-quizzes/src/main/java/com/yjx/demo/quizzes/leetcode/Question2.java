package com.yjx.demo.quizzes.leetcode;

import java.util.LinkedList;

public class Question2 {


/*    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

    }*/

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 示例：
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */
    public static void main(String[] args) {
        int n = 807;
        System.out.println(String.valueOf(n));

        LinkedList<Integer> res = new LinkedList<>();
        res.add(8);
        res.add(0);
        res.add(7);
        System.out.println(res.toString());

        System.out.println();
        System.out.println();
    }

}
