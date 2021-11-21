package version.two;

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
 * @TIME 2021/11/21 14:31
 * @DESCRIPTION 
 **/
public class Day15 {

    /**
     * 剑指 Offer II 044. 二叉树每层的最大值
     * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)return ans;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        while (!queue.isEmpty()){
            int max = Integer.MIN_VALUE;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode p = queue.removeFirst();
                max = Math.max(p.val, max);
                if (p.left!=null)queue.addLast(p.left);
                if (p.right!=null)queue.addLast(p.right);
            }
            ans.add(max);
        }
        return ans;
    }

    /**
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        int ans = 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        TreeNode p = null;
        while (!queue.isEmpty()){
            ans = queue.getFirst().val;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                p = queue.removeFirst();
                if (p.left!=null)queue.addLast(p.left);
                if (p.right!=null)queue.addLast(p.right);
            }
        }
        return ans;
    }

    /**
     * 剑指 Offer II 046. 二叉树的右侧视图
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root!=null) queue.addLast(root);
        TreeNode p =null;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size-1; i++) {
                p = queue.removeFirst();
                if (p.left!=null)queue.addLast(p.left);
                if (p.right!=null)queue.addLast(p.right);
            }
            p = queue.removeFirst();
            ans.add(p.val);
            if (p.left!=null)queue.addLast(p.left);
            if (p.right!=null)queue.addLast(p.right);
        }
        return ans;
    }
}
