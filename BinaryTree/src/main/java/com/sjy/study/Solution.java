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
}
