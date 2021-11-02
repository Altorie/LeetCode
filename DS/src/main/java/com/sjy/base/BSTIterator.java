package com.sjy.base;

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
 * @TIME 2021/11/1 10:44
 * @DESCRIPTION
 *      本质上考察的是非递归形式的二叉树中序遍历
 **/
public class BSTIterator {
    TreeNode node = new TreeNode(-1);
    Deque<TreeNode> stack = new LinkedList<>();
    public BSTIterator(TreeNode root) {
        while (root!=null){
            stack.push(root);
            root = root.left;
        }
    }

    public int next() {
        node = stack.pop();
        int ans = node.val;
        node = node.right;
        while (node!=null){
            stack.push(node);
            node = node.left;
        }
        return ans;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
