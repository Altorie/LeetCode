package com.sjy;

public class Day11 {
    /**
     * 160. 相交链表
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        // 计算链表 A的长度
        int m = 0;
        ListNode temp = headA;
        while (temp!=null){
            m++;
            temp = temp.next;
        }
        // 计算链表 B的长度
        int n = 0;
        temp = headB;
        while (temp!=null){
            n++;
            temp = temp.next;
        }
        ListNode a = headA, b = headB;
        if (m > n){
            for (int i = 0; i < m-n; i++) {
                a = a.next;
            }
        } else if (m <n ){
            for (int i = 0; i < n-m; i++) {
                b = b.next;
            }
        }
        while (a!=null && b!=null){
            if (a==b){
                return a;
            } else {
                a = a.next;
                b= b.next;
            }
        }
        return null;
    }

    /**
     * 82. 删除排序链表中的重复元素 II
     * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
     *
     * 返回同样按升序排列的结果链表。
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head==null || head.next == null){
            return head;
        }
        ListNode p = head;
        ListNode p_father = null;
        while (p!=null && p.next!=null){
            if (p.val!=p.next.val){//本节点不是重复节点
                p_father = p;
                p = p.next;
            } else { // 本节点开始出现重复
                while (p.next!=null && p.val == p.next.val){
                    p.next = p.next.next;
                }
                // 只剩下 p 要删除
                if (p_father == null){ // 重复节点包括头节点
                    head = p.next;
                    p = p.next;
                }else { // p_father p b 这种形式
                    p = p.next;
                    p_father.next = p;
                }
            }
        }
        return head;
    }
}
