package com.sjy.base;

public class Day10 {
    /**
     * 2. 两数相加
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     *
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     *
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode();
        ans.val = (l1.val + l2.val) % 10;
        int add = (l1.val + l2.val) / 10;
        ListNode p1 = l1.next;
        ListNode p2 = l2.next;
        ListNode temp = ans;
        while (p1!=null && p2!=null){
            int sum = p1.val + p2.val + add;
            int val = sum % 10;
            temp.next = new ListNode(val);
            temp = temp.next;
            add = sum / 10;
            p1 = p1.next;
            p2 = p2.next;
        }
        ListNode p = p1 == null? p2:p1;
        while (p!=null){
            int sum = p.val + add;
            int val = sum % 10;
            add = sum / 10;
            temp.next = new ListNode(val);
            temp = temp.next;
            p = p.next;
        }
        if (add != 0){
            temp.next = new ListNode(add);
        }
        return ans;
    }

    /**
     * 142. 环形链表 II
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        /**
         * 利用HashMap
         */
//        Map<ListNode, Integer> map = new HashMap<>();
//        ListNode p = head;
//        while (p!=null){
//            if (map.containsKey(p)){
//                return p;
//            } else {
//                map.put(p, 1);
//                p = p.next;
//            }
//        }
//        return null;
        /**
         * 快慢指针
         */
        if (head == null){
            return null;
        }
        // 快慢指针。快指针每次走两步，慢指针每次走一步
        ListNode slow = head, fast = head;
        while (fast!=null){
            slow = slow.next;
            if (fast.next!=null){
                fast = fast.next.next;
            } else {
                return null;
            }
            if (slow == fast){ // 快慢指针相遇
                ListNode p = head;
                while (p!=slow){
                    p = p.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}

