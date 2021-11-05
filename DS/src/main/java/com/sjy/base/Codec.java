package com.sjy.base;

import java.util.ArrayList;
import java.util.Arrays;
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
 * @TIME 2021/11/4 14:35
 * @DESCRIPTION 297.二叉树的序列化和反序列化
 *      考察的是通过一个序列代表唯一一个二叉树。
 *      仅仅前、中、后遍历的序列是不行的，需要做出一定的修改
 **/
public class Codec {
    /**
     *利用先序遍历进行编码。空节点记为‘#’，这样就能区分左子树和右子树
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        rserialize(root, sb);
        return sb.toString();
    }
    private void rserialize(TreeNode root, StringBuilder sb){
        if (root==null){
            sb.append('#').append(',');
            return;
        }
        sb.append(root.val).append(',');
        rserialize(root.left, sb);
        rserialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] split = data.split(",");
        List<String> list = new ArrayList<>(Arrays.asList(split));
        return rdeserialize(list);
    }
    private TreeNode rdeserialize(List<String> list){
        if (list.get(0).equals("#")){
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(list.get(0)));
        list.remove(0);
        node.left = rdeserialize(list);
        node.right = rdeserialize(list);
        return node;
    }
}
