package version.two;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

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
 * @TIME 2021/11/22 13:45
 * @DESCRIPTION 
 **/
public class Day16 {
    private TreeNode p;
    private int ans;
    public static void main(String[] args) {
        Codec c = new Codec();
        System.out.println(c.deserialize(""));
    }

    /**
     * 剑指 Offer II 047. 二叉树剪枝
     *
     * 后序遍历，先对左右子树剪枝。再看自己是否要剪枝
     *
     * @param root
     * @return
     */
    public TreeNode pruneTree(TreeNode root) {
        if (root == null ){
            return null;
        }
        TreeNode l = pruneTree(root.left);
        TreeNode r = pruneTree(root.right);
        root.left = l;
        root.right = r;
        if (root.val == 0 && l==null && r == null)return null;
        return root;
    }

    /**
     * 剑指 Offer II 049. 从根节点到叶节点的路径数字之和
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        sumNumbers_traversal(root, list);
        return ans;
    }
    private void sumNumbers_traversal(TreeNode node, List<Integer> list){
        if (node.left == null && node.right == null){
            for (int i = 0; i < list.size(); i++) {
                ans += (list.get(i) * Math.pow(10, list.size()-1-i));
            }
        }
        if (node.left!=null){
            list.add(node.left.val);
            sumNumbers_traversal(node.left, list);
            list.remove(list.size()-1);
        }
        if (node.right!=null){
            list.add(node.right.val);
            sumNumbers_traversal(node.right, list);
            list.remove(list.size()-1);
        }
    }



}

/**
 * 剑指 Offer II 048. 序列化与反序列化二叉树
 */
class Codec {
    private int index = 0;

    /**
     * 将一个二叉树序列化为一个字符串
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        serialize_traversal(root,sb);
        return sb.toString();
    }
    private void serialize_traversal(TreeNode node, StringBuffer sb){
        if (node == null){
            sb.append('#').append(',');
            return;
        }
        // 正数占一个字符，负数占两个字符，因此用 逗号隔开
        sb.append(node.val).append(',');
        serialize_traversal(node.left, sb);
        serialize_traversal(node.right, sb);
    }

    /**
     * 将字符串反序列化为二叉树
     * @param data
     * @return
     */
    public TreeNode deserialize(String data) {
        index = 0;
        return deserialize_traversal(data.split(","));
    }
    private TreeNode deserialize_traversal(String[] data){
        if (index >= data.length)return null;
        if (data[index].equals("#")) {
            index++;
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(data[index]));
        index++;
        node.left = deserialize_traversal(data);
        node.right = deserialize_traversal(data);
        return node;
    }
}
