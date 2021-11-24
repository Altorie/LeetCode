package version.two;

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
 * @TIME 2021/11/24 8:41
 * @DESCRIPTION 
 **/
public class Day18 {
    private int val;

    /**
     * 剑指 Offer II 053. 二叉搜索树中的中序后继
     * 给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null 。
     * 节点 p 的后继是值比 p.val大的节点中键值最小的节点，即按中序遍历的顺序节点 p 的下一个节点。
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // 如果当前节点的值小于等于 p的值，那么可以确定 目标节点 一定在当前节点的 右子树
        // 如果当前节点的值大于 p ，那么有可能是 目标节点，先记录此节点。
        // 接着遍历此节点的左子树，看是否有更小的 值大于 p 的节点
        TreeNode ans = null;
        while (root!=null){
            if (root.val <= p.val){
                root = root.right;
            } else {
                ans = root;
                root = root.left;
            }
        }
        return ans;
    }

    /**
     * 剑指 Offer II 054. 所有大于等于节点的值之和
     * 给定一个二叉搜索树，请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        // 采用先遍历右子树的中序遍历
        convertBST_traversal(root);
        return root;
    }
    private void convertBST_traversal(TreeNode node){
        if (node == null) return;
        convertBST_traversal(node.right);
        int temp = node.val;
        val += temp;
        node.val = val;
        convertBST_traversal(node.left);
    }
}
