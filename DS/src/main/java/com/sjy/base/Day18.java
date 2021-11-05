package com.sjy.base;
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
 * @TIME 2021/11/2 11:12
 * @DESCRIPTION 
 **/
public class Day18 {

    private TreeNode ans;

    /**
     * 236. 二叉树的最近公共祖先
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ans;
    }
    private boolean dfs(TreeNode root, TreeNode p, TreeNode q){ // 以root为跟的子树中书否存在节点
        if (root==null) return false;
        boolean left = dfs(root.left, p, q); // 左子树是否包含节点p q
        boolean right = dfs(root.right, p, q); // 右子树是否包含节点p q
        // 判断是否为最近公共祖先
        // (root.val == p.val||root.val==q.val) 代表 当前节点是 p 或者 q中的一个
        // left||right 表示 当前节点的左子树或右子树中包含 p q
        // 上述两个条件合起来就是 最近公共祖先 恰好是 p 或 q 中的一个
        if ((left&&right) || ( (root.val == p.val||root.val==q.val) && (left||right) )){
            ans = root;
        }
        return left||right||(root.val == p.val || root.val == q.val);

    }


}
