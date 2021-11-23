package version.two;

import java.util.HashMap;
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
 * @TIME 2021/11/23 9:51
 * @DESCRIPTION 
 **/
public class Day17 {
    private int ans;
    private TreeNode pre;

    /**
     * 剑指 Offer II 050. 向下的路径节点之和
     * 给定一个二叉树的根节点 root，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
     * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        /**
         * 1.使用递归。
         * 路径之和 = 左右子树的路径之和 + 以根节点为起点的满足条件的路径(结束不需要是叶子节点)数
         */
//        if (root==null){
//            return 0;
//        }
//        return pathSum_dfs1(root, targetSum) + pathSum(root.left, targetSum) + pathSum(root.right, targetSum);
        /**
         * 2.利用前缀和
         */
        if (root == null)return 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        pathSum_dfs2(root, map, 0, targetSum);
        return ans;
    }
    private int pathSum_dfs1(TreeNode node, int targetSum){
        if (node == null){
            return 0;
        }
        int num = node.val == targetSum ? 1:0;
        return num+pathSum_dfs1(node.left, targetSum-node.val) + pathSum_dfs1(node.right, targetSum-node.val);
    }
    private void pathSum_dfs2(TreeNode node, Map<Integer, Integer> map, int curSum, int targetSum){
        curSum += node.val;
        ans += map.getOrDefault(curSum - targetSum, 0);
        map.put(curSum, map.getOrDefault(curSum, 0)+1);
        if (node.left!=null){
            pathSum_dfs2(node.left, map, curSum, targetSum);
        }
        if (node.right!=null){
            pathSum_dfs2(node.right, map, curSum, targetSum);

        }
        // 回溯
        map.put(curSum, map.getOrDefault(curSum, 0)-1);
    }

    /**
     * 剑指 Offer II 051. 节点之和最大的路径
     * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
     * 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
     *
     * 本质上是求出以每个节点为根不断向下的最大值.
     * 每一条路径的最高的节点我们称之为根。我们只需计算以树中每一个节点为根的路径取最大值。
     * 以一个节点为根的路径的最大值，等于  左孩子不断往下延伸过程中的最大值（不能为负） + 本节点的值 + 右孩子不断往下延伸过程中的最大值（不能为负）
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        ans = Integer.MIN_VALUE;
        maxPathSum_traversal(root);
        return ans;
    }
    private int maxPathSum_traversal(TreeNode node){
        if (node == null){
            return 0;
        }
        int l = Math.max(0, maxPathSum_traversal(node.left));
        int r = Math.max(0, maxPathSum_traversal(node.right));
        ans = Math.max(ans, l + r + node.val);
        return node.val + Math.max(l, r);
    }


    /**
     * 剑指 Offer II 052. 展平二叉搜索树
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        // 虚拟节点，因为最后要返回最左的叶子节点
        TreeNode dumpy = new TreeNode();
        pre = dumpy;
        increasingBST_traversal(root);
        return dumpy.right;
    }
    private void increasingBST_traversal(TreeNode node){
        if (node == null)return;
        increasingBST_traversal(node.left);
        node.left = null;
        pre.right = node;
        pre = node;
        increasingBST_traversal(node.right);
    }

}
