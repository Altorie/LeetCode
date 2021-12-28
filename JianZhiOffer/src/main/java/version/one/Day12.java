package version.one;

import java.util.Arrays;
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
 * @TIME 2021/12/27 15:59
 * @DESCRIPTION 
 **/
public class Day12 {

    public static void main(String[] args) {
        Day12 d = new Day12();
        d.numFriendRequests(new int[]{73,106,39,6,26,15,30,100,71,35,46,112,6,60,110});
    }
    /**
     * 剑指 Offer 25. 合并两个排序的链表
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode dumpy = new ListNode(0);
        ListNode p = dumpy;
        while (p1!=null && p2 != null){
            if (p1.val < p2.val){
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }
        if (p1 == null){
            p.next = p2;
        } else {
            p.next = p1;
        }
        return dumpy.next;
    }

    /**
     * 剑指 Offer 52. 两个链表的第一个公共节点
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        int n1 = 0, n2 = 0;
        while (p1!=null){
            n1++;
            p1 = p1.next;
        }
        while (p2!=null){
            n2++;
            p2 = p2.next;
        }
        int diff = Math.abs(n1 - n2);
        if (n1 > n2){
            p1 = headA;
            p2 = headB;
        } else {
            p1 = headB;
            p2 = headA;
        }
        for (int i = 0; i < diff; i++) {
            p1 = p1.next;
        }
        while (p1!=null && p1 != p2){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    /**
     * 825. 适龄的朋友
     * @param ages
     * @return
     */
    public int numFriendRequests(int[] ages) {
        int n  = ages.length;
        Arrays.sort(ages);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x = ages[i];
            //     0.5*x + 7 < y <= x
            int low = (int) (0.5 * x + 8);
            int heigh = x;
            if (low >= heigh) continue;
            // 找第一个 大于等于 low 的
            int l = 0;
            int r = i-1;
            int index1 = i;
            while (l <= r){
                int mid = l + (r-l)/2;
                if (ages[mid] < low){
                    l = mid + 1;
                } else {
                    if (mid == 0 || ages[mid-1] < low){
                        index1 = mid;
                        break;
                    } else {
                        r = mid - 1;
                    }
                }
            }
            ans += i - index1;
            // 找第一个小于等于 heigh 的
            l = i+1;
            r = ages.length-1;
            int index2 = i;
            while (l <= r){
                int mid = l + (r-l)/2;
                if (ages[mid] > heigh){
                    r = mid - 1;
                } else {
                    if (mid == ages.length-1 || ages[mid+1] > heigh){
                        index2 = mid;
                        break;
                    } else {
                        l = mid+1;
                    }
                }
            }
            ans += index2 - i;
        }
        return ans;
    }
}
