package com.sjy;

public class Day12 {
    public static void main(String[] args) {

    }

    /**
     * 24. 两两交换链表中的节点
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     *
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode father = null;
        ListNode p = head;
        while (p!=null && p.next!=null){
            ListNode q = p.next;
            p.next = q.next;
            q.next = p;
            if (father==null){// 是头两个
                head = q;
            }else {
                father.next = q;

            }
            // 移动指针
            father = p;
            p = p.next;
        }
        return head;
    }



}
