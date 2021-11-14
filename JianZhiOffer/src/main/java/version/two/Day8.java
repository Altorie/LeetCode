package version.two;

import javax.swing.tree.TreeNode;

/**
 *                             _ooOoo_
 *                            o8888888o
 *                            88" . "88
 *                            (| -_- |)
 *                            O\  =  /O
 *                         ____/`---'\____
 *                       .'  \\|     |//  `.
 *                      /  \\|||  :  |||//  \
 *                     /  _||||| -:- |||||-  \
 *                     |   | \\\  -  /// |   |
 *                     | \_|  ''\---/''  |   |
 *                     \  .-\__  `-`  ___/-. /
 *                   ___`. .'  /--.--\  `. . __
 *                ."" '<  `.___\_<|>_/___.'  >'"".
 *               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *               \  \ `-.   \_ __\ /__ _/   .-` /  /
 *          ======`-.____`-.___\_____/___.-`____.-'======
 *                             `=---='
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *                     佛祖保佑        永无BUG
 * @AUTHOR zuo-zhenjun
 * @TIME 2021/11/14 10:33
 * @DESCRIPTION 
 **/
public class Day8 {
    public static void main(String[] args) {

    }

    /**
     * 剑指 Offer II 024. 反转链表
     * 给定单链表的头节点 head ，请反转链表，并返回反转后的链表的头节点。
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 剑指 Offer II 025. 链表中的两数相加
     * 给定两个 非空链表 l1和 l2 来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
     *
     * 可以假设除了数字 0 之外，这两个数字都不会以零开头。

     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        boolean flag = false;
        ListNode head = null;
        while (l1!=null && l2 != null){
            int val = l1.val + l2.val;
            if (flag) val+=1;
            flag = val >= 10;
            if (flag) {
                val = val % 10;
            }
            ListNode node = new ListNode(val, head);
            head = node;
            l1 = l1.next;
            l2 = l2.next;
        }
        ListNode p = l1 == null?l2:l1;
        while (p!=null){
            int val = p.val;
            if (flag) val+=1;
            flag = val >= 10;
            if (flag) {
                val = val % 10;
            }
            ListNode node = new ListNode(val, head);
            head = node;
            p = p.next;
        }
        if (flag){
            head = new ListNode(1, head);
        }
        return head;
    }

    /**
     * 剑指 Offer II 026. 重排链表
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head==null || head.next==null) return;
        // 将链表拆成两个子链表
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next;
        }
        ListNode head2 = slow.next;
        slow.next = null;
        // 逆转后半个链表
        head2 = reverseList(head2);
        slow = head;
        ListNode slowNext = null;
        ListNode headNext = null;
        while (head2!=null){
            slowNext = slow.next;
            headNext = head2.next;
            slow.next = head2;
            head2.next = slowNext;
            slow = slowNext;
            head2 = headNext;
        }
        return;
    }
}
