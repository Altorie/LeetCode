package version.two;

import java.util.List;

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
 * @TIME 2021/11/13 9:39
 * @DESCRIPTION 
 **/
public class Day7 {

    /**
     * 剑指 Offer II 021. 删除链表的倒数第 n 个结点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 虚拟头指针。为了不用单独考虑 head 节点
        ListNode dumpy = new ListNode();
        dumpy.next = head;
        ListNode fast = dumpy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        ListNode slow = dumpy;
        while (fast!=null && fast.next!=null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dumpy.next;
    }

    /**
     * 剑指 Offer II 022. 链表中环的入口节点
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next==null)return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast){
                ListNode ans = head;
                while (ans!=slow){
                    ans = ans.next;
                    slow = slow.next;
                }
                return ans;

            }
        }
        return null;
    }

    /**
     * 剑指 Offer II 023. 两个链表的第一个重合节点
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int m = 0;
        ListNode a = headA;
        while (a!=null){
            a = a.next;
            m++;
        }
        int n = 0;
        ListNode b = headB;
        while (b!=null){
            b = b.next;
            n++;
        }
        a = headA;
        b = headB;
        if (m > n){
            for (int i = 0; i < m-n; i++) {
                a = a.next;
            }
        } else {
            for (int i = 0; i < n-m; i++) {
                b = b.next;
            }
        }
        while (a!=b){
            a = a.next;
            b = b.next;
        }
        return a;
    }
}
