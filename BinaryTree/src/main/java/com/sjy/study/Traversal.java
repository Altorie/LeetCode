package com.sjy.study;

import sun.awt.image.ImageWatched;

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
 * @TIME 2021/11/7 16:05
 * @DESCRIPTION 非递归地遍历前、中、后遍历二叉树
 **/
public class Traversal {

    /**
     * 非递归地前序遍历
     * @param root
     */
    public void preorder(TreeNode root){
        if (root==null){
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        StringBuffer sb = new StringBuffer();
        sb.append(root.val);
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode top = stack.pop();
            sb.append(top.val).append("->");
            if (top.right!=null) stack.push(top.right);
            if (top.left!=null) stack.push(top.left);
        }
        System.out.println(sb);
    }

    /**
     * 非递归地中序遍历
     * @param root
     */
    public void inorder(TreeNode root){
        Deque<TreeNode> stack = new LinkedList<>();
        StringBuffer sb = new StringBuffer();
        while (root!=null || !stack.isEmpty()){
            if (root!=null){
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                sb.append(root.val);
                root = root.right;
            }
        }
        System.out.println(sb);
    }

    /**
     * 非递归的后序遍历
     */
    public void postOrder(TreeNode root){
        if (root == null){
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        StringBuffer sb = new StringBuffer();
        sb.append(root.val);
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            if (node!=null){
                stack.push(node);
                // 压入标记节点。如果标记节点在某个节点之前出站，说明已经遍历过这个节点的左右子节点，但是还没处理自己
                stack.push(null);
                if (node.right!=null) stack.push(node.right);
                if (node.left!=null) stack.push(node.left);
            } else { // 如果是标记节点，处理下一个栈顶元素
                node = stack.pop();
                sb.append(node.val).append("->");
            }
        }
        System.out.println(sb);
    }
}
