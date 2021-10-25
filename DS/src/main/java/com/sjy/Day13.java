package com.sjy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Day13 {

    public static void main(String[] args) {
        Day13 s = new Day13();
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        a.next = b;
        b.next = c;
        c.next = d;
        s.reorderList(a);
    }
    /**
     * 25. K 个一组翻转链表
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     *
     * k 是一个正整数，它的值小于或等于链表的长度。
     *
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head==null || head.next==null || k==1){
            return head;
        }
        ListNode pre = null;
        ListNode start = head;
        ListNode p, q, temp;
        while (start!=null){
            temp = start;
            // 检测这一组是否有 k 个元素
            int count = 1;
            while (temp.next!=null){
               temp = temp.next;
               count++;
               if (count == k){
                   break;
               }
            }
            if (count == k){
                p = start;
                q = start.next;
                for (int i = 0; i <= k - 2; i++) {
                    ListNode next =q.next;
                    q.next = p;
                    p = q;
                    q = next;
                }
                // 现在 p 到了这 K 个元素的最后位置，q到了下 K 个元素的第一个位置
                //
                if (pre == null){
                    head = p;
                } else {
                    pre.next = p;
                }
                pre = start;
                start = q;
            } else {
                if (pre!=null)  pre.next = start;
                break;
            }
        }
        return head;
    }

    /**
     * 143. 重排链表
     * @param head
     */
    public void reorderList(ListNode head) {
        /**
         * 法 1 ：
         * O(N)
         * O(N)
         */
//        if (head == null || head.next == null){
//            return;
//        }
//        Queue<ListNode> queue = new LinkedList<>();
//        Stack<ListNode> stack = new Stack<>();
//        ListNode p = head;
//        int count = 0;
//        while (p!=null){
//            count ++;
//            queue.offer(p);
//            stack.push(p);
//            p = p.next;
//        }
//        ListNode n1 = null;
//        ListNode n2 = null;
//        for (int i = 0; i < count / 2; i++) {
//            n1 = queue.poll();
//            n2 = stack.pop();
//            n1.next = n2;
//            n2.next = queue.peek();
//        }
//        if (queue.peek() != stack.peek()){
//            n2.next = null;
//        }  else {
//            n2.next.next = null;
//        }
        /**
         * 法 2：寻找链表中点 + 后半链表逆序 + 合并链表
         */
        // 1.寻找中点
        if (head == null || head.next == null || head.next.next == null){
            return;
        }
        ListNode p1 = head, p2 = head;
        while (p2!=null && p2.next!=null){
            p1 = p1.next;
            p2 = p2.next.next;
        }
        ListNode mid = p1;
        // 2.反转后半链表
        p2 = p1.next;
        while (p2!=null){
            ListNode next = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = next;
        }
        mid.next = null;
        // 3.合并两个链表，起点分别为 head， p1
        ListNode p = head;
        while (p1.next!=null){
            ListNode p_next = p.next;
            ListNode p1_next = p1.next;
            p.next = p1;
            p1.next = p_next;
            p = p_next;
            p1 = p1_next;
        }
        p1.next = null;
    }
}
