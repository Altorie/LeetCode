package com.sjy.study;

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
 * @TIME 2021/11/6 16:50
 * @DESCRIPTION 
 **/
public class Solution {
    private TreeNode p;
    private int min = Integer.MAX_VALUE;

    int maxCount = 1; // 目前最高的频率
    int count = 0; // 当前节点的频率

    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
        node1.right = node2;
        node2.left = node3;
        s.findMode(node1);
    }

    /**
     * 107. 二叉树的层序遍历 II
     * 给定一个二叉树，返回其节点值自底向上的层序遍历。
     * （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)return ans;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode top = queue.removeFirst();
                list.add(top.val);
                if (top.left!=null)queue.addLast(top.left);
                if (top.right!=null)queue.addLast(top.right);
            }
            ans.add(list);
        }
        Collections.reverse(ans);
        return ans;
    }

    /**
     * 226. 翻转二叉树
     * 翻转一棵二叉树。
     *
     * 可以采用前序、后序和层序遍历实现，唯独中序遍历不行
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        /**
         * 采用非递归前序遍历实现
         */
//        Deque<TreeNode> stack = new LinkedList<>();
//        if (root!=null)stack.push(root);
//        while (!stack.isEmpty()){
//            TreeNode top = stack.pop();
//            if (top!=null){ // 说明这个节点没有遍历过
//                if (top.right!=null)stack.push(top.right);
//                if (top.left!=null)stack.push(top.left);
//                stack.push(top);
//                stack.push(null); // 标记节点，代表上一个入栈节点已经遍历过，需要处理
//            } else { // 当前节点是标记节点，需要处理下一个栈顶元素
//                top = stack.pop();
//                TreeNode temp = top.left;
//                top.left = top.right;
//                top.right = temp;
//            }
//        }
//        return root;
        /**
         * 采用非递归后序遍历实现
         */
//        Deque<TreeNode> stack = new LinkedList<>();
//        if (root!=null)stack.push(root);
//        while (!stack.isEmpty()){
//            TreeNode top = stack.pop();
//            if (top!=null){ // 说明这个节点没有遍历过
//                stack.push(top);
//                stack.push(null); // 标记节点，代表上一个入栈节点已经遍历过，需要处理
//                if (top.right!=null)stack.push(top.right);
//                if (top.left!=null)stack.push(top.left);
//            } else { // 当前节点是标记节点，需要处理下一个栈顶元素
//                top = stack.pop();
//                TreeNode temp = top.left;
//                top.left = top.right;
//                top.right = temp;
//            }
//        }
//        return root;
        /**
         * 采用层序遍历实现
         */
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root!=null)queue.addLast(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode top = queue.removeFirst();
                // 交换
                TreeNode temp = top.left;
                top.left = top.right;
                top.right = temp;
                // 子节点入队列
                if (top.left!=null)queue.addLast(top.left);
                if (top.right!=null)queue.addLast(top.right);
            }
        }
        return root;
    }

    /**
     * 101. 对称二叉树
     * 给定一个二叉树，检查它是否是镜像对称的。
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        /**
         * 1 利用递归解决
         */
//        if (root==null)return true;
//        return traverse(root.left, root.right);
        /**
         * 2 利用迭代解决
         *
         */
        if (root == null)return true;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root.left);
        queue.addLast(root.right);
        while (!queue.isEmpty()){
            TreeNode leftNode = queue.removeFirst();
            TreeNode rightNode = queue.removeFirst();
            if (leftNode==null && rightNode == null){
                continue;
            }
            if (leftNode!=null && rightNode!=null && leftNode.val == rightNode.val){
                queue.addLast(leftNode.left);
                queue.addLast(rightNode.right);

                queue.addLast(leftNode.right);
                queue.addLast(rightNode.left);
            } else {
                return false;
            }
        }
        return true;
    }
    private boolean traverse(TreeNode left, TreeNode right){
        if (left == null && right == null){
            return true;
        } else if (left!=null && right!=null){
            return left.val == right.val && traverse(left.right, right.left) && traverse(left.left, right.right);
        }
        return false;
    }

    /**
     * 111. 二叉树的最小深度
     * 给定一个二叉树，找出其最小深度。
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     *
     * 这道题和求二叉树的最大深度有一点区别：如果某一个子节点为 null，那在求最小值时这个子节点的 0 不能考虑
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null)return 0;
        if (root.left == null && root.right == null){
            return 1;
        }
        int min = Integer.MAX_VALUE;
        if (root.left!=null)min = Math.min(min, minDepth(root.left));
        if (root.right!=null)min = Math.min(min, minDepth(root.right));
        return min+1;
    }

    /**
     * 222. 完全二叉树的节点个数
     * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root==null){
            return 0;
        }
        int leftDepth = 0, rightDepth = 0;
        TreeNode l = root.left;
        while (l!=null){
            leftDepth++;
            l = l.left;
        }
        TreeNode r = root.right;
        while (r!=null){
            rightDepth++;
            r =r.right;
        }
        if (leftDepth == rightDepth){ // 这棵树是满二叉树
            return (2 << leftDepth)-1;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    /**
     * 257. 二叉树的所有路径
     * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
     * 叶子节点 是指没有子节点的节点。
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<Integer> path = new ArrayList<>();
        List<String> result = new ArrayList<>();
        if (root!=null) backtrack(root, path, result);
        return result;
    }
    private void backtrack(TreeNode node, List<Integer> path, List<String> result){
        path.add(node.val);
        if (node.left == null && node.right == null){ // 遍历到叶子节点
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < path.size()-1; i++) {
                sb.append(path.get(i)).append("->");
            }
            sb.append(path.get(path.size()-1));
            result.add(sb.toString());
        }else {
            if (node.left!=null){
                backtrack(node.left, path, result);
                path.remove(path.size()-1);
            }
            if (node.right!=null){
                backtrack(node.right, path, result);
                path.remove(path.size()-1);
            }
        }
    }

    /**
     * 404. 左叶子之和
     * 计算给定二叉树的所有左叶子之和。
     *
     * 判断一个节点是否是左叶子，必须都通其父亲判断
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        /**
         * 1. 使用递归
         */
        if (root == null){
            return 0;
        }
        if (root.left!=null && root.left.left==null && root.left.right==null){
            return root.left.val + sumOfLeftLeaves(root.right);
        }
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    /**
     * 513. 找树左下角的值
     * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
     * 假设二叉树中至少有一个节点。
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        int result = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            result = queue.getFirst().val;
            for (int i = 0; i < size; i++) {
                TreeNode top = queue.removeFirst();
                if (top.left!=null) queue.addLast(top.left);
                if (top.right!=null) queue.addLast(top.right);
            }
        }
        return result;
    }

    /**
     * 112. 路径总和
     * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum
     * 判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        /**
         * 1. 递归
         * 只要 左子树或右子树存在和为 targetSum - root.val 的路径
         */
        if (root == null){
            return false;
        }
        if (root.left == null && root.right == null && root.val == targetSum){
            return true;
        }
        boolean ans = false;
        if (root.left!=null){
            ans |= hasPathSum(root.left, targetSum-root.val);
        }
        if (root.right!=null){
            ans |= hasPathSum(root.right, targetSum-root.val);
        }
        return ans;
        /**
         * 2.遍历
         */
    }

    /**
     * 113. 路径总和 II
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     * 叶子节点 是指没有子节点的节点。
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null){
            return ans;
        }
        List<Integer> path = new ArrayList<>();
        path.add(root.val);
        pathSum_backtrack(root, targetSum, path, ans);
        return ans;
    }
    private void pathSum_backtrack(TreeNode root, int targetSum, List<Integer> path, List<List<Integer>> ans){
        if (root.left==null && root.right == null && root.val == targetSum){
            List<Integer> list = new ArrayList<>(path);
            ans.add(list);
        }
        if (root.left!=null){
            path.add(root.left.val);
            pathSum_backtrack(root.left, targetSum-root.val, path, ans);
            path.remove(path.size()-1);
        }
        if (root.right!=null){
            path.add(root.right.val);
            pathSum_backtrack(root.right, targetSum-root.val, path, ans);
            path.remove(path.size()-1);
        }
    }


    /**
     * 617. 合并二叉树
     * 使用递归
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees1(TreeNode root1, TreeNode root2) {
        // 如果 root1 为空，直接返回 root2。同时，即使 root2还有子节点，对应的 root1仍为 null
        // 直接返回的还是 root2 的子节点本身
        // 由于 root2 和 其子节点本来就是串联的，因此不必再往下遍历
        if (root1==null){
            return root2;
        }
        if (root2 == null){ // 同理
            return root1;
        }
        root1.val += root2.val;
        root1.left = mergeTrees1(root1.left, root2.left);
        root1.right = mergeTrees1(root1.right, root2.right);
        return root1;
    }

    /**
     * 617. 合并二叉树
     * 使用迭代
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root1);
        queue.addLast(root2);
        while (!queue.isEmpty()){
            TreeNode node1 = queue.removeFirst();
            TreeNode node2 = queue.removeFirst();
            node1.val+=node2.val;
            if (node1.left!=null && node2.left !=null){
                queue.addLast(node1.left);
                queue.addLast(node2.left);
            }
            if (node1.right!=null && node2.right!=null){
                queue.addLast(node1.right);
                queue.addLast(node2.right);
            }
            if (node1.left == null){
                node1.left = node2.left;
            }
            if (node1.right==null){
                node1.right = node2.right;
            }
        }
        return root1;
    }

    /**
     * 98. 验证二叉搜索树
     *
     * 中序遍历应该是一个递增的序列
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        /**
         * 递归中序遍历
         */
//        if (root == null) return true;
//        boolean left = isValidBST(root.left);
//        // p 是前一个遍历的节点
//        // 如果前一个节点的值 大于等于 本节点，那么肯定不是二叉搜索树
//        if (p!=null && p.val >= root.val){
//            return false;
//        }
//        // 更新 p 指针
//        p = root;
//        boolean right = isValidBST(root.right);
//        return left&& right;
        /**
         * 迭代中序遍历
         */
        Deque<TreeNode> stack = new LinkedList<>();
        // 记录前一个节点
        TreeNode pre = null;
        while (root!=null || !stack.isEmpty()){
            if (root!=null){
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (pre!=null && pre.val >= root.val)return false;
                pre = root;
                root = root.right;
            }
        }
        return true;
    }

    /**
     * 530. 二叉搜索树的最小绝对差
     * 最小绝对差只会出现在中序遍历序列的相邻两个元素之间
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        /**
         * 递归式中序遍历
         */
        getMinimumDifference_travesal(root);
        return min;
    }
    private void getMinimumDifference_travesal(TreeNode cur){
        if (cur!=null){
            getMinimumDifference_travesal(cur.left);
            if (p!=null){
                min = Integer.min(cur.val - p.val, min);
            }
            p = cur;
            getMinimumDifference_travesal(cur.right);
        }
    }

    /**
     * 501. 二叉搜索树中的众数
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        findMode_travesal(root, result);
        int[] ans = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }
        return ans;
    }
    private void findMode_travesal(TreeNode node,List<Integer> result){
        if (node == null) return;
        findMode_travesal(node.left, result);
        if (p == null){
            count=1;
        } else if (p.val == node.val){
            count ++;
        } else {
            count = 1;
        }
        if (count > maxCount){
            maxCount = count;
            result.clear();
            result.add(node.val);
        } else if (count == maxCount){
            result.add(node.val);
        }
        p = node;
        findMode_travesal(node.right, result);
    }

    /**
     * 235. 二叉搜索树的最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > q.val){
            return lowestCommonAncestor(root, q, p);
        }
        while (true){
            if (root.val == p.val || root.val == q.val || ( root.val > p.val && root.val < q.val) ){
                return root;
            }
            if (root.val < p.val){
                root = root.left;
            } else {
                root = root.right;
            }
        }
    }

    /**
     * 删除二叉搜索树中的节点
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode p = root;
        TreeNode father = null; // 父节点
        while (p!=null){
            if (key < p.val){
                father = p;
                p = p.left;
            } else if (key > p.val){
                father = p;
                p = p.right;
            } else { // 找到目标节点
                if (p.left!=null && p.right!=null){ // 目标节点的左右孩子都存在
                    // 找到其右子树最小的节点
                    TreeNode pp = p.right;
                    TreeNode ppf = p;
                    while (pp.left!=null){
                        ppf = pp;
                        pp = pp.left;
                    }
                    // 交换节点 p 和 节点 pp 的值
                    int temp = p.val;
                    p.val = pp.val;
                    pp.val = temp;
                    // 此时要删除的节点就变成了 pp
                    p = pp;
                    father = ppf;
                }
                // 目标节点只有一个或没有孩子的情况
                TreeNode child = null;
                if (p.left!=null) child = p.left;
                if (p.right!=null) child = p.right;
                if (father == null) return child;
                if (p == father.left) father.left = child;
                if (p == father.right) father.right = child;
                break;
            }
        }
        return root;
    }

    /**
     * 669. 修剪二叉搜索树
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        /**
         * 1.递归
         */
//        if (root == null) return null;
//        // 如果 root 比 high大，那么结果与 左子树的修剪结果 是相同的
//        if (root.val > high){
//            return trimBST(root.left, low, high);
//        }
//        // 如果 root 比 low 小，那么结果与 右子树的修剪结果 是相同的
//        if (root.val < low){
//            return trimBST(root.right, low, high);
//        }
//        // 左子树的修剪结果 加上 右子树的修剪结果
//        root.left = trimBST(root.left, low, high);
//        root.right = trimBST(root.right, low, high);
//        return root;

        /**
         * 2.迭代
         *  先把 root 节点移动到区间内
         *  修剪 root 的左子树
         *  修剪 root 的右子树
         */
        while (root!=null && (root.val < low || root.val > high)){
            if (root.val < low){
                root = root.right;
            } else {
                root = root.left;
            }
        }
        // 修剪左子树
        TreeNode l = root;
        while (l!=null){
            while (l.left!=null && l.left.val < low){
                l.left = l.left.right;
            }
            l = l.left;
        }
        // 修剪右子树
        TreeNode r = root;
        while (r!=null){
            while (r.right!=null && r.right.val > high){
                r.right = r.right.left;
            }
            r = r.right;
        }
        return root;
    }
}
