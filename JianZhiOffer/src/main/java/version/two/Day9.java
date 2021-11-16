package version.two;

import javax.swing.tree.TreeNode;
import java.util.Deque;
import java.util.LinkedList;

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
 * @TIME 2021/11/15 12:56
 * @DESCRIPTION 
 **/
public class Day9 {
    public static void main(String[] args) {

    }

    /**
     * 剑指 Offer II 027. 回文链表
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head==null || head.next == null)return true;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode q = slow.next;
        q = reverse(q);
        while (q!=null){
            if (q.val!=head.val)return false;
            q = q.next;
            head = head.next;
        }
        return true;
    }
    private ListNode reverse(ListNode head){
        if (head==null || head.next==null)return head;
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 剑指 Offer II 028. 展平多级双向链表
     * @param head
     * @return
     */
    public Node flatten(Node head) {
        /**
         * 迭代
         */
        Node p = head;
        Node next = null;
        Node child = null;
        Node pre = null;
        Deque<Node> stack = new LinkedList<>();
        while (p!=null || !stack.isEmpty()){
            if (p!=null){
                if (p.child!=null) {
                    child = p.child;
                    if (p.next != null) stack.push(p.next);
                    p.next = p.child;
                    p.child = null;
                    child.prev = p;
                }
                pre = p;
                p = p.next;
            } else {
                Node top = stack.pop();
                pre.next = top;
                top.prev = pre;
                p = top;
            }
        }
        return head;
        /**
         * 递归 (有问题)
         */
//        if (head == null)return head;
//        flatten_recursion(head);
//        return head;
    }
    private Node flatten_recursion(Node node){
        if (node.child == null && node.next == null)return node;
        Node child = node.child;
        Node next = node.next;
        if (child!=null){
            node.next = child;
            child.prev = next;
            node.child = null;
            Node last = flatten_recursion(child);
            last.next = next;
            if (next!=null){
                next.prev = last;
                return flatten_recursion(next);
            } else {
                return last;
            }
        }else{
            return flatten_recursion(next);
        }
    }

    /**
     * 剑指 Offer II 029. 排序的循环链表
     * @param head
     * @param insertVal
     * @return
     */
    public Node insert(Node head, int insertVal) {
        if (head == null){
            Node node = new Node(insertVal);
            node.next = node;
            return node;
        }
        Node node = new Node(insertVal);
        Node p = head;
        Node q = head.next;
        Node max = head; // 第一个遇到的最大节点
        do {
            if (insertVal >= p.val && insertVal <= q.val){ // p insert q
                p.next = node;
                node.next = q;
                return head;
            }
            if (p.val > max.val)max = p;
            p = p.next;
            q = q.next;
        }while (p!=head); // 如果 p 回到 head 的位置，说明 insertVal 是最大或最小值。都插在最后一个最大值后面
        p = max;
        if (p.val == p.next.val){ // 如果 max 不是最后一个最大的节点
            do {
                p = p.next;
            }
            while (p.val == p.next.val && p!=max); // 如果 所有节点的值都是 max
        }
        node.next = p.next;
        p.next = node;
        return head;
    }
}
