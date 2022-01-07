package version.one;

import javafx.util.Pair;
import sun.reflect.generics.tree.Tree;

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
 * @TIME 2022/1/2 19:49
 * @DESCRIPTION
 **/
public class Day18 {

    public static void main(String[] args) {
        Day18 d= new Day18();
        d.lastRemaining(9);
    }

    /**
     * 剑指 Offer 55 - I. 二叉树的深度
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root==null)return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /**
     * 剑指 Offer 55 - II. 平衡二叉树
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return traversal(root).getValue();

    }
    private Pair<Integer, Boolean> traversal(TreeNode node){
        if (node == null)return new Pair<>(0, true);
        Pair<Integer, Boolean> left = traversal(node.left);
        Pair<Integer, Boolean> right = traversal(node.right);
        int depth = 1+Math.max(left.getKey(), right.getKey());
        if (left.getValue() && right.getValue()){
            return new Pair<>(depth,
                    Math.abs(left.getKey() - right.getKey()) <= 1);
        }else {
            return new Pair<>(depth, false);
        }
    }



    /**
     * 390. 消除游戏
     * 本质上是模拟等差数列的一道题
     * @param n
     * @return
     */
    public int lastRemaining(int n) {
        int a1 = 1; // 首项
        int size = n; // 等差数列的大小
        int d = 1; // 公差
        for (int i = 0; size > 1; i++) {
            if (i % 2 == 0){ // 从左边第一个开始删
                a1 += d;
            } else { // 从右边第一个开始删
                if (size % 2 == 0){ // 偶数个，不会删掉 a1
                } else { // 奇数个，删掉 a1
                    a1 += d;
                }
            }
            size /= 2;
            d *= 2;
        }
        return a1;
    }
}
