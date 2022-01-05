package version.one;

import java.util.*;

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
 * @TIME 2021/12/30 22:11
 * @DESCRIPTION 
 **/
public class Day15 {
    Node pre = null;
    Node head = null;
    int ans = 0;
    int count = 1;

    class Node{
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    public static void main(String[] args) {
        Day15 d = new Day15();
        d.isNStraightHand(new int[]{1,2,3,6,2,3,4,7,8}, 3);
    }


    /**
     * 剑指 Offer 34. 二叉树中和为某一值的路径
     * @param root
     * @param target
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root==null)return ans;
        List<Integer> path = new ArrayList<>();
        path.add(root.val);
        pathSum_backtrack(root, target-root.val, path, ans);
        return ans;
    }
    private void pathSum_backtrack(TreeNode node, int target, List<Integer> path, List<List<Integer>> ans){
        if (node.left==null && node.right==null){
            if (target==0)ans.add(new ArrayList<>(path));
            return;
        }
        if (node.left!=null){
            path.add(node.left.val);
            pathSum_backtrack(node.left, target-node.left.val, path, ans);
            path.remove(path.size()-1);
        }
        if (node.right!=null){
            path.add(node.right.val);
            pathSum_backtrack(node.right, target-node.right.val, path, ans);
            path.remove(path.size()-1);
        }

    }

    /**
     * 剑指 Offer 36. 二叉搜索树与双向链表
     * @param root
     * @return
     */
    public Node treeToDoublyList(Node root) {
        if (root == null)return null;
        traverasl(root);
        head.left = pre;
        pre.right = head;
        return head;
    }
    private void traverasl(Node node){
        if (node == null)return;
        traverasl(node.left);
        if (pre!=null){
            pre.right = node;
            node.left = pre;
        }else {
            head = node;
        }
        pre = node;
        traverasl(node.right);
    }

    /**
     * 剑指 Offer 54. 二叉搜索树的第k大节点
     * @param root
     * @param k
     * @return
     */
    public int kthLargest(TreeNode root, int k) {
        kthLargest_traversal(root, k);
        return ans;
    }
    private void kthLargest_traversal(TreeNode node, int k){
        if (node == null)return;
        kthLargest_traversal(node.right, k);
        if (count == k)ans = node.val;
        count++;
        kthLargest_traversal(node.left, k);
    }

    /**
     * 846. 一手顺子
     * @param hand
     * @param groupSize
     * @return
     */
    public boolean isNStraightHand(int[] hand, int groupSize) {
        /**
         *
         */
//        int n = hand.length;
//        if (n % groupSize != 0)return false;
//        int m = n / groupSize;
//        Arrays.sort(hand);
//        boolean[] used = new boolean[n];
//        int preIndex = -1;
//        for (int i = 0; i < m; i++) {
//            // 寻找起始点
//            for (int j = 0; j < n; j++) {
//                if (!used[j]){
//                    preIndex = j;
//                    used[j] = true;
//                    break;
//                }
//            }
//            int count = 1;
//            int pre = hand[preIndex];
//            for (int j = preIndex+1; j < n && count < groupSize ; j++) {
//                if (!used[j] && hand[j] == pre+1){
//                    count++;
//                    used[j] = true;
//                    pre = hand[j];
//                }
//            }
//            if (count < groupSize)return false;
//        }
//        return true;

        /**
         * 利用 堆 和 哈希表优化
         */
        int n = hand.length;
        if (n % groupSize != 0)return false;
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(); // 小顶堆
        Arrays.sort(hand);
        for (int i : hand){
            queue.add(i);
            map.put(i, map.getOrDefault(i, 0)+1);
        }
        while (!queue.isEmpty()){
            // 取最小作为组合第一个
            Integer top = queue.poll();
            // 不能被选光
            if (map.get(top)==0)continue;
            for (int i = top; i < top+groupSize; i++) {
                if (map.getOrDefault(i, 0) == 0){
                    return false;
                } else {
                    map.put(i, map.get(i)-1);
                }
            }
        }
        return true;
    }

}


