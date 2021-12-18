package version.one;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @TIME 2021/12/17 10:02
 * @DESCRIPTION 
 **/
public class Day2 {
    /**
     * 剑指 Offer 06. 从尾到头打印链表
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head!=null){
            list.add(head.val);
            head = head.next;
        }
        int[] ans = new int[list.size()];
        for (int i = list.size()-1; i >= 0 ; i--) {
            ans[list.size()-i-1] = list.get(i);
        }
        return ans;
    }

    /**
     * 剑指 Offer 24. 反转链表
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head.next == null)return head;
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 剑指 Offer 35. 复杂链表的复制
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head==null)return null;
        // 记录原节点和新节点的映射关系
        Map<Node, Node> map = new HashMap<>();
        Node newHead = new Node(head.val);
        map.put(head, newHead);
        Node p1 = head.next;
        Node p2 = newHead;
        while (p1!=null){
            p2.next = new Node(p1.val);
            p2 = p2.next;
            map.put(p1, p2);
            p1 = p1.next;
        }
        p1 = head;
        p2=newHead;
        while (p1!=null){
            p2.random = map.get(p1.random);
            p2 = p2.next;
            p1 = p1.next;
        }
        return newHead;
    }
}
class ListNode{
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
