package com.sjy.base;

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
 * @TIME 2021/10/30 12:45
 * @DESCRIPTION 
 **/
public class Day16 {
    /**
     * 199. 二叉树的右视图
     * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        /**
         * 法 1：递归。
         * 时间复杂度不太行
         */
//        if (root == null){
//            return new ArrayList<>();
//        }
//        List<Integer> left = rightSideView(root.left);
//        List<Integer> right = rightSideView(root.right);
//        if (left.size() > right.size()){
//            right.addAll(left.subList(right.size(), left.size()));
//        }
//        right.add(0, root.val);
//        return right;
        /**
         * 法 2：深度优先遍历
         * 在搜索过程中，我们总是先访问右子树。那么对于每一层来说，我们在这层见到的第一个结点一定是最右边的结点
         */
        if (root == null){
            return new ArrayList<>();
        }
        Map<Integer, Integer> map = new HashMap<>(); // 存储每一层最右边的节点
        int maxDepth = 1; // 维护最大深度
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> depthStack  = new Stack<>();
        nodeStack.push(root);
        depthStack.push(1);
        while (!nodeStack.isEmpty()){
            // 获取节点和节点所在的层数
            TreeNode node = nodeStack.pop();
            int depth = depthStack.pop();
            // 更新最大深度
            maxDepth = Math.max(maxDepth, depth);
            // 如果 node 是这一层第一个遍历到的
            if (!map.containsKey(depth)){
                map.put(depth, node.val);
            }
            // 先入栈左子节点，后入栈右子节点。使右子节点先遍历
            if (node.left!=null){
                nodeStack.push(node.left);
                depthStack.push(depth+1);
            }
            if (node.right !=null){
                nodeStack.push(node.right);
                depthStack.push(depth+1);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= maxDepth ; i++) {
            ans.add(map.get(i));
        }
        return ans;
        /**
         * 法 3：广度优先遍历
         */
    }

    /**
     * 450. 删除二叉搜索树中的节点
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。
     * 返回二叉搜索树（有可能被更新）的根节点的引用。
     *

     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null){
            return root;
        }
        TreeNode dumpy = new TreeNode(); //虚拟头节点
        dumpy.left = root;
        TreeNode target = root;
        TreeNode father = dumpy;
        // 寻找目标节点
        while (target!=null){
            if (key == target.val){ // 找到目标节点
                if (target.left!=null && target.right!=null){ // 目标节点有两个子节点
                    // 找到右子树的最小节点，替换target
                    TreeNode min = target.right;
                    TreeNode minFather = target;
                    while (min.left!=null){
                        minFather = min;
                        min = min.left;
                    }
                    target.val = min.val;
                    // 现在问题变成了删除 min
                    target = min;
                    father = minFather;
                }
                // 目标节点只有一个或没有子节点的情况
                TreeNode child = null;
                if (target.left!=null) child = target.left;
                if (target.right!=null) child = target.right;
                if (target == father.left) father.left = child;
                if (target == father.right) father.right = child;
                break;
            }
            father = target;
            if (key > target.val){
                target = target.right;
            } else {
                target = target.left;
            }
        }
        return dumpy.left;
    }

    /**
     * 113. 路径总和 II
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     *
     * 叶子节点 是指没有子节点的节点。
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null){
            return ans;
        }
        backtrack(root, 0, targetSum, new ArrayList<>(), ans);
        return ans;
    }
    private void backtrack(TreeNode node, int curSum, int targetSum, List<Integer> list, List<List<Integer>> ans){
        curSum += node.val;
        list.add(node.val);
        if (node.left == null && node.right == null){ // 是叶子节点
            if (curSum == targetSum){
                ans.add(new ArrayList<>(list));
            }
        } else {
            if (curSum < targetSum){
                if (node.left!=null) backtrack(node.left, curSum, targetSum, list, ans);
                if (node.right!=null) backtrack(node.right, curSum, targetSum, list, ans);
            }

        }
        curSum -= node.val;
        list.remove(list.size()-1);
    }

}
