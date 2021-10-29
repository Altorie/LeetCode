package com.sjy.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
 * @TIME 2021/10/29 9:45
 * @DESCRIPTION 
 **/
public class Day15 {
    public static void main(String[] args) {
        Day15 d = new Day15();
        d.buildTree(new int[]{1,2}, new int[]{2, 1});
    }

    /**
     * 108. 将有序数组转换为二叉搜索树
     * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
     *
     * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return a(nums, 0, nums.length-1);
    }
    private TreeNode a(int[] nums, int start, int end){
        if (start == end){
            return new TreeNode(nums[start]);
        } else if (start > end){
            return null;
        }
        int mid = start + (end - start) /2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = a(nums, start, mid-1);
        node.right = a(nums, mid+1, end);
        return node;
    }

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
     *
     * 思路：
     *      前序遍历的第一个元素一定是根节点
     *      中序遍历根节点左边的元素一定是左子树，右边一定是右子树
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return b(preorder, 0, inorder, 0, inorder.length-1);
    }
    private TreeNode b(int[] preorder, int rootIndex, int[] inorder, int left, int right){
        if (left == right){
            return new TreeNode(inorder[left]);
        }
        int rootIndexInOrder = 0;
        for (int i = left; i <= right; i++) {
            if (inorder[i] == preorder[rootIndex]){
                rootIndexInOrder = i;
            }
        }
        TreeNode root = new TreeNode(preorder[rootIndex]);
        int leftLength = rootIndexInOrder - left; // 左子树的大小
        int rightLength = right - rootIndexInOrder; // 右子树的大小
        if (leftLength > 0){
            root.left = b(preorder, rootIndex+1, inorder, left, rootIndexInOrder-1);
        }
        if (rightLength > 0){
            root.right = b(preorder, rootIndex+1+leftLength, inorder, rootIndexInOrder+1, right);
        }
        return root;
    }

    /**
     * 103. 二叉树的锯齿形层序遍历
     * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        /**
         * 法 1：使用两个栈
         */
        Stack<TreeNode> left2right = new Stack<>();
        Stack<TreeNode> right2left = new Stack<>();
        left2right.push(root);
        List<List<Integer>> ans = new ArrayList<>();
        if (root==null){
            return ans;
        }
        while (!left2right.isEmpty() || !right2left.isEmpty()){
            List<Integer> list1 = new ArrayList<>();
            while (!left2right.isEmpty()){
                TreeNode node = left2right.pop();
                list1.add(node.val);
                if (node.left!=null){
                    right2left.push(node.left);
                }
                if (node.right!=null){
                    right2left.push(node.right);
                }
            }
            if (list1.size()>0){
                ans.add(list1);
            }

            List<Integer> list2 = new ArrayList<>();
            while (!right2left.isEmpty()){
                TreeNode node = right2left.pop();
                list2.add(node.val);
                if (node.right!=null){
                    left2right.push(node.right);
                }
                if (node.left!=null){
                    left2right.push(node.left);
                }
            }
            if (list2.size()>0){
                ans.add(list2);
            }
        }
        return ans;
    }
}
