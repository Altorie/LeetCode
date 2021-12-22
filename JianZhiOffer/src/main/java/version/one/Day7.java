package version.one;

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
 * @TIME 2021/12/22 8:31
 * @DESCRIPTION 
 **/
public class Day7 {

    public static void main(String[] args) {
        new Day7().repeatedStringMatch("abc","cabcabca");
    }
    /**
     * 剑指 Offer 26. 树的子结构
     * @param A
     * @param B
     * @return
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null)return false;
        if (A.val == B.val){
            boolean flag = preorder(A, B);
            if (flag)return true;
        }
        return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }
    private boolean preorder(TreeNode a, TreeNode b){
        if (b==null) return true;
        if (a == null)return false;
        if (a.val == b.val){
            return preorder(a.left, b.left) && preorder(a.right, b.right);
        } else {
            return false;
        }
    }


    /**
     * 剑指 Offer 27. 二叉树的镜像
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null)return null;
        // 翻转左右子树
        TreeNode left = mirrorTree(root.left);
        TreeNode right = mirrorTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 剑指 Offer 28. 对称的二叉树
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null)return true;
        return traversal(root.left, root.right);
    }
    private boolean traversal(TreeNode a, TreeNode b){
        if (a == null && b == null)return true;
        if (a== null || b == null) return false;
        if (a.val == b.val){
            return traversal(a.left, b.right) && traversal(a.right, b.left);
        }
        return false;
    }

    /**
     * 686. 重复叠加字符串匹配
     * @param a
     * @param b
     * @return
     */
    public int repeatedStringMatch(String a, String b) {
        int len1 = a.length();
        int len2 = b.length();
        // 如果能够匹配到，那么 a 至少要复制 below 次。
        // 这样长度才能 b
        int below = len2 / len1;
        if (len2 % len1!=0)below++;
        // 如果能匹配到，那么 a 最多复制 below +1 次
        // 因为匹配到的话，匹配起点一定在第一个 a 之中
        int up = below + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < up; i++) {
            sb.append(a);
        }
        String str = sb.toString();
        // 在 str 中寻找第一次 b 出现的位置
        int index = str.indexOf(b);
        if (index == -1)return -1;
        // 判断匹配的结尾有没有超过 a 复制 below次的长度
        // 超过就说明必须匹配 up 次
        int end = index + b.length()-1;
        if (end > a.length() * below-1)return up;
        return below;
    }
}
