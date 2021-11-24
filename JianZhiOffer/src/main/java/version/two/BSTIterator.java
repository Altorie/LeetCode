package version.two;

import sun.reflect.generics.tree.Tree;

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
 * @TIME 2021/11/24 14:29
 * @DESCRIPTION
 *      实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
 **/
public class BSTIterator {

    private TreeNode cur;
    private Deque<TreeNode> stack = new LinkedList<>();

    public BSTIterator(TreeNode root) {
        cur = root;
        while (cur!=null){
            stack.push(cur);
            cur = cur.left;
        }
    }

    /**
     * 将指针向右移动，然后返回指针处的数字
     * @return
     */
    public int next() {
        cur = stack.pop();
        int ans = cur.val;
        cur = cur.right;
        while (cur!=null){
            stack.push(cur);
            cur = cur.left;
        }
        return ans;
    }

    /**
     * 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false
     * @return
     */
    public boolean hasNext() {
        return cur!=null || !stack.isEmpty();
    }
}
