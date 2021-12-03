package version.two;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

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
 * @TIME 2021/12/2 10:35
 * @DESCRIPTION 
 **/
public class Day26 {

    /**
     * 剑指 Offer II 076. 数组中的第 k 大的数字
     * 基于快速排序
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        return findByQuickSort(nums, 0, nums.length-1, k);
    }
    private int findByQuickSort(int[] nums, int start, int end, int k){
        if (start >= end){
            return nums[start];
        }
        int l = start;
        int r = end;
        int base = nums[start];
        while (l < r){
            while (l < r &&nums[r] <= base){
                r--;
            }
            while (l < r &&nums[l] >= base){
                l++;
            }
            if (l != r){
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
            }
        }
        nums[start] = nums[l];
        nums[l] = base;
        if (k-1 == l){
            return nums[l];
        } else if (k-1 < l){
            return findByQuickSort(nums, start, l-1, k);
        }else {
            return findByQuickSort(nums, l+1, end, k);
        }

    }

    /**
     * 剑指 Offer II 077. 链表排序
     * 给定链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        // 归并排序
        ListNode slow = head, fast = head, pre = null;
        while (fast!=null && fast.next!=null){
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        ListNode head1 = sortList(head);
        ListNode head2 = sortList(slow);
        // 组合两个链表
        ListNode dumpy = new ListNode();
        ListNode p = dumpy;
        while (true){
            if (head1==null && head2==null){
                break;
            }
            if (head1 == null){
                p.next = head2;
                break;
            } else if (head2 == null){
                p.next = head1;
                break;
            } else {
                if (head1.val <= head2.val){
                    p.next = head1;
                    head1 = head1.next;
                } else {
                    p.next = head2;
                    head2 = head2.next;
                }
                p = p.next;
            }
        }
        return dumpy.next;
    }

    /**
     * 剑指 Offer II 078. 合并排序链表
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        Map<ListNode, Integer> map = new HashMap<>(len);// 记录出现在优先队列中的节点所属的链表
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (int i = 0; i < len; i++) {
            if (lists[i]!=null){
                queue.offer(lists[i]);
                map.put(lists[i], i);
                lists[i] = lists[i].next;
            }
        }
        ListNode dumpy = new ListNode();//虚拟头结点
        ListNode p = dumpy;
        while (queue.size()>0){
            ListNode node = queue.poll();
            p.next = node;
            p = p.next;
            // node 所属的链表索引
            int index = map.get(node);
            map.remove(node);
            // 往队列中加入新节点
            if (lists[index]!=null){
                queue.offer(lists[index]);
                map.put(lists[index], index);
                lists[index] = lists[index].next;
            }
        }
        return dumpy.next;
    }
}
