package com.sjy.tree;

/**
 * 二叉搜索树
 */
public class MyBinarySearchTree {
    private Node root;

    /**
     * 查询
     * @param val
     * @return
     */
    public Node find(int val){
        Node temp = root;
        while (temp!=null){
            if (val < temp.val){
                temp = temp.left;
            }else if (val > temp.val){
                temp = temp.right;
            }else {
                return temp;
            }
        }
        return null;
    }

    /**
     * 插入。将新节点插在叶子节点上
     * @param node
     */
    public void insert(Node node){
        if (root == null){
            root = node;
        } else {
            Node temp = root;
            while (temp!=null){
                if (node.val <= temp.val){
                    if (temp.left == null){
                        temp.left = node;
                        return;
                    }
                    temp = temp.left;
                } else {
                    if (temp.right == null){
                        temp.right = node;
                    }
                    temp = temp.right;
                }
            }
        }
    }

    /**
     * 删除操作
     * @param val
     */
    public void delete(int val){
        Node temp = root;
        Node father = null;
        while (temp!=null){
            if (val < temp.val){
                father = temp;
                temp = temp.left;
            } else if (val > temp.val){
                father = temp;
                temp = temp.right;
            } else { // 找到目标节点，删除
                if (temp.left != null && temp.right != null){ // temp有两个子节点的情况
                    // 找到左子树中最大的节点 或 右子树中最小的节点，将其替换 temp
                    Node min = temp.right;
                    Node min_father = temp;
                    while (min.left != null){
                        min_father = min;
                        min = min.left;
                    }
                    // 把 temp 节点和 min 节点的值交换
                    temp.val = min.val;
                    // 此时只需删除节点 min即可，min 只有一个或没有子节点。
                    // 将 目标节点有两个子节点的情况 转换为 只有一个或没有子节点的情况
                    temp = min;
                    father = min_father;
                }
                // 目标节点只有一个或没有子节点的情况
                // 获取目标节点的子节点
                Node child = null;
                if (temp.left != null) child = temp.left;
                else if (temp.right != null) child = temp.right;
                // 删除目标节点
                if (father == null) root = null; // 删除的是根节点
                else if (father.left == temp) father.left = child;
                else father.right = child;

            }
        }
    }

    public static void main(String[] args) {
        Node a = new Node();
        a.val = 1;
        Node b = a.left;
        b = new Node();
        b.val = 2;
        System.out.println(a.left.val);
    }
}

class Node{
    int val;
    Node left;
    Node right;

    public Node() {
    }
}
