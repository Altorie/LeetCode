package version.two;

import sun.awt.image.ImageWatched;

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
 * @TIME 2021/11/25 8:29
 * @DESCRIPTION 
 **/
public class Day19 {

    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        System.out.println(set.ceiling(4));
        Day19 d = new Day19();
        d.containsNearbyAlmostDuplicate(new int[]{1,5,9,1,5,9}, 2, 3);
    }
    /**
     * 剑指 Offer II 056. 二叉搜索树中两个节点之和
     * 给定一个二叉搜索树的 根节点 root 和一个整数 k , 请判断该二叉搜索树中是否存在两个节点它们的值之和等于 k 。
     * 假设二叉搜索树中节点的值均唯一。
     *
     * 在一个严格递增数组中判断是否存在两个节点它们的值之和等于 k
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget(TreeNode root, int k) {
        /**
         * 1 使用额外空间
         */
//        List<Integer> list = new ArrayList<>();
//        Deque<TreeNode> stack = new LinkedList<>();
//        while (root!=null || !stack.isEmpty()){
//            if (root!=null){
//                while (root!=null){
//                    stack.push(root);
//                    root = root.left;
//                }
//            } else {
//                root = stack.pop();
//                list.add(root.val);
//                root = root.right;
//            }
//        }
//        int l = 0;
//        int r = list.size()-1;
//        while (l < r){
//            int sum = list.get(l) + list.get(r);
//            if (sum > k) {
//                r--;
//            } else if (sum < k){
//                l ++;
//            } else {
//                return true;
//            }
//        }
//        return false;

        /**
         * 2 使用两个栈，同时从BST的最左和最右开始遍历
         */
        TreeNode l = root;
        TreeNode r = root;
        Deque<TreeNode> lStack = new LinkedList<>();
        Deque<TreeNode> rStack = new LinkedList<>();
        while (l.left!=null){
            lStack.push(l);
            l = l.left;
        }
        while (r.right!=null){
            rStack.push(r);
            r =r.right;
        }
        while (l!=r){
                TreeNode p1 = r.left;
                while (p1!=null){
                    rStack.push(p1);
                    p1 = p1.right;
                }
                TreeNode p2 = l.right;
                while (p2!=null){
                    lStack.push(p2);
                    p2 = p2.left;
                }
            if (l.val + r.val > k){
                r = rStack.pop();
            } else if (l.val + r.val < k){
                l = lStack.pop();
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 剑指 Offer II 057. 值和下标之差都在给定的范围内
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        /**
         * 滑动窗口 + 有序集合
         */
//        TreeSet<Long> set = new TreeSet<>();
//        for (int i = 0; i < nums.length; i++) {
//            Long ceiling = set.ceiling((long)nums[i]-t);
//            if (ceiling!=null && ceiling <= (long)nums[i]+t){
//                return true;
//            }
//            set.add((long)nums[i]);
//            if (i >=k){
//                set.remove((long)(nums[i-k]));
//            }
//        }
//        return false;
        /**
         * 滑动窗口 + 桶排序
         * 每个桶的大小是 t+1，把窗口内所有的元素放进桶里
         */
        HashMap<Long, Integer> map = new HashMap<>(); // 模拟桶
        long w = (long)t+1; // 这里设置宽度是 t+1 是为了防止出现除数为 0 的情况
        for (int i = 0; i < nums.length; i++) {
            long id = getID((long)nums[i], w);
            if (map.containsKey(id)){
                return true;
            }
            if (map.containsKey(id-1) && Math.abs((long)nums[i]-map.get(id-1) ) <= t){
                return true;
            }
            if (map.containsKey(id+1) && Math.abs((long)nums[i]-map.get(id+1) ) <= t){
                return true;
            }
            map.put(id, nums[i]);
            if (i >= k){
                map.remove(getID((long)nums[i-k], w));
            }
        }
        return false;
    }
    // 判断元素 x 在哪一个桶里
    private long getID(long x, long w){
        if (x >= 0){
            return x/w;
        }
        return (x+1)/w - 1;
    }


}
