package com.sjy.study;

import sun.reflect.generics.tree.Tree;

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
 * @TIME 2021/11/6 16:50
 * @DESCRIPTION 
 **/
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str1 = in.nextLine();
        String str2 = in.nextLine();
        String[] s = str1.split(" ");
        List<Integer> odds = new ArrayList<>(); // 存储奇数的可取数目
        List<Integer> evens = new ArrayList<>(); // 存储偶数的可取数目
    }

    /**
     * 107. 二叉树的层序遍历 II
     * 给定一个二叉树，返回其节点值自底向上的层序遍历。
     * （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)return ans;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode top = queue.removeFirst();
                list.add(top.val);
                if (top.left!=null)queue.addLast(top.left);
                if (top.right!=null)queue.addLast(top.right);
            }
            ans.add(list);
        }
        Collections.reverse(ans);
        return ans;
    }

    /**
     * 226. 翻转二叉树
     * 翻转一棵二叉树。
     *
     * 可以采用前序、后序和层序遍历实现，唯独中序遍历不行
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        /**
         * 采用非递归前序遍历实现
         */
//        Deque<TreeNode> stack = new LinkedList<>();
//        if (root!=null)stack.push(root);
//        while (!stack.isEmpty()){
//            TreeNode top = stack.pop();
//            if (top!=null){ // 说明这个节点没有遍历过
//                if (top.right!=null)stack.push(top.right);
//                if (top.left!=null)stack.push(top.left);
//                stack.push(top);
//                stack.push(null); // 标记节点，代表上一个入栈节点已经遍历过，需要处理
//            } else { // 当前节点是标记节点，需要处理下一个栈顶元素
//                top = stack.pop();
//                TreeNode temp = top.left;
//                top.left = top.right;
//                top.right = temp;
//            }
//        }
//        return root;
        /**
         * 采用非递归后序遍历实现
         */
//        Deque<TreeNode> stack = new LinkedList<>();
//        if (root!=null)stack.push(root);
//        while (!stack.isEmpty()){
//            TreeNode top = stack.pop();
//            if (top!=null){ // 说明这个节点没有遍历过
//                stack.push(top);
//                stack.push(null); // 标记节点，代表上一个入栈节点已经遍历过，需要处理
//                if (top.right!=null)stack.push(top.right);
//                if (top.left!=null)stack.push(top.left);
//            } else { // 当前节点是标记节点，需要处理下一个栈顶元素
//                top = stack.pop();
//                TreeNode temp = top.left;
//                top.left = top.right;
//                top.right = temp;
//            }
//        }
//        return root;
        /**
         * 采用层序遍历实现
         */
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root!=null)queue.addLast(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode top = queue.removeFirst();
                // 交换
                TreeNode temp = top.left;
                top.left = top.right;
                top.right = temp;
                // 子节点入队列
                if (top.left!=null)queue.addLast(top.left);
                if (top.right!=null)queue.addLast(top.right);
            }
        }
        return root;
    }

    /**
     * 101. 对称二叉树
     * 给定一个二叉树，检查它是否是镜像对称的。
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        /**
         * 1 利用递归解决
         */
//        if (root==null)return true;
//        return traverse(root.left, root.right);
        /**
         * 2 利用迭代解决
         *
         */
        if (root == null)return true;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root.left);
        queue.addLast(root.right);
        while (!queue.isEmpty()){
            TreeNode leftNode = queue.removeFirst();
            TreeNode rightNode = queue.removeFirst();
            if (leftNode==null && rightNode == null){
                continue;
            }
            if (leftNode!=null && rightNode!=null && leftNode.val == rightNode.val){
                queue.addLast(leftNode.left);
                queue.addLast(rightNode.right);

                queue.addLast(leftNode.right);
                queue.addLast(rightNode.left);
            } else {
                return false;
            }
        }
        return true;
    }
    private boolean traverse(TreeNode left, TreeNode right){
        if (left == null && right == null){
            return true;
        } else if (left!=null && right!=null){
            return left.val == right.val && traverse(left.right, right.left) && traverse(left.left, right.right);
        }
        return false;
    }

    /**
     * 111. 二叉树的最小深度
     * 给定一个二叉树，找出其最小深度。
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     *
     * 这道题和求二叉树的最大深度有一点区别：如果某一个子节点为 null，那在求最小值时这个子节点的 0 不能考虑
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null)return 0;
        if (root.left == null && root.right == null){
            return 1;
        }
        int min = Integer.MAX_VALUE;
        if (root.left!=null)min = Math.min(min, minDepth(root.left));
        if (root.right!=null)min = Math.min(min, minDepth(root.right));
        return min+1;
    }

    /**
     * 222. 完全二叉树的节点个数
     * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root==null){
            return 0;
        }
        int leftDepth = 0, rightDepth = 0;
        TreeNode l = root.left;
        while (l!=null){
            leftDepth++;
            l = l.left;
        }
        TreeNode r = root.right;
        while (r!=null){
            rightDepth++;
            r =r.right;
        }
        if (leftDepth == rightDepth){ // 这棵树是满二叉树
            return (2 << leftDepth)-1;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    /**
     * 257. 二叉树的所有路径
     * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
     * 叶子节点 是指没有子节点的节点。
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<Integer> path = new ArrayList<>();
        List<String> result = new ArrayList<>();
        if (root!=null) backtrack(root, path, result);
        return result;
    }
    private void backtrack(TreeNode node, List<Integer> path, List<String> result){
        path.add(node.val);
        if (node.left == null && node.right == null){ // 遍历到叶子节点
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < path.size()-1; i++) {
                sb.append(path.get(i)).append("->");
            }
            sb.append(path.get(path.size()-1));
            result.add(sb.toString());
        }else {
            if (node.left!=null){
                backtrack(node.left, path, result);
                path.remove(path.size()-1);
            }
            if (node.right!=null){
                backtrack(node.right, path, result);
                path.remove(path.size()-1);
            }
        }
    }
}
