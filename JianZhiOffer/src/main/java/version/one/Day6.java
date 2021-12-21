package version.one;

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
 * @TIME 2021/12/21 11:12
 * @DESCRIPTION 
 **/
public class Day6 {
    /**
     * 剑指 Offer 32 - I. 从上到下打印二叉树
     * @param root
     * @return
     */
    public int[] levelOrder(TreeNode root) {
        if (root==null)return new int[]{};
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.pop();
            list.add(node.val);
            if (node.left!=null)queue.addLast(node.left);
            if (node.right!=null)queue.addLast(node.right);
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    /**
     * 剑指 Offer 32 - II. 从上到下打印二叉树 II
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root==null)return ans;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        while (!queue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pop();
                list.add(node.val);
                if (node.left!=null)queue.addLast(node.left);
                if (node.right!=null)queue.addLast(node.right);
            }
            ans.add(list);
        }
        return ans;
    }

    /**
     * 剑指 Offer 32 - III. 从上到下打印二叉树 III
     * 使用两个栈进行层序遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root==null)return ans;
        Deque<TreeNode> stack1 = new LinkedList<>();
        Deque<TreeNode> stack2 = new LinkedList<>();
        stack1.push(root);
        while (!stack1.isEmpty() || !stack2.isEmpty()){
            List<Integer> list = new ArrayList<>();
            if (!stack1.isEmpty()){
                while (!stack1.isEmpty()){
                    TreeNode node = stack1.pop();
                    list.add(node.val);
                    if (node.left!=null)stack2.push(node.left);
                    if (node.right!=null)stack2.push(node.right);
                }
            }else {
                while (!stack2.isEmpty()){
                    TreeNode node = stack2.pop();
                    list.add(node.val);
                    if (node.right!=null)stack1.push(node.right);
                    if (node.left!=null)stack1.push(node.left);
                }
            }
            ans.add(list);
        }
        return ans;
    }

    /**
     * 1154. 一年中的第几天
     * @param date
     * @return
     */
    public int dayOfYear(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));
        int[] arr = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        // 闰年 2月加一天
        if (year%400==0 || (year%4==0 && year%100!=0))arr[1] += 1;
        int ans = 0;
        for (int i = 0; i < month-1; i++) {
            ans += arr[i];
        }
        ans += day;
        return ans;
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
