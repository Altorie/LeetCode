package com.sjy.tree;

/**
 * 字典树
 */
public class Trie {
    private TrieNode root = new TrieNode('/');

    /**
     * 插入一个字符串
     * @param str
     */
    public void insert(String str){
        char[] chars = str.toCharArray();
        TrieNode p = root;
        for (char c : chars){
            int index = c - 'a';
            if (p.children[index]!=null){
                p = p.children[index];
            } else {
                TrieNode node = new TrieNode(c);
                p.children[index] = node;
                p = node;
            }
        }
        p.isEndingNode = true; // 设置这个子段的意义在于查找
    }

    /**
     * 查找是否存在指定字符串
     * @param str
     * @return
     */
    public boolean find(String str){
        char[] chars = str.toCharArray();
        TrieNode p = root;
        for (char c : chars){
            int index = c - 'a';
            if (p.children[index] == null){
                return false;
            }
            p = p.children[index];
        }
        // 只有存在这样一条序列，且序列最后一个节点是终止节点，才能认为存在这样的字符串
        // 否则只能认为存在具有以目标字符串为前缀的字符串
        // 例如：找 abcd，只有 abcde。如果 d的 isEndingNode 子段不为 true，那么就不存在
        if (p.isEndingNode){
            return true;
        }
        return false;
    }
}

class TrieNode{
    char val;
    TrieNode[] children = new TrieNode[26];  // 假设字符集为 26个小写英文字母
    boolean isEndingNode = false;  // 记录该节点是否为某个串的终止节点

    public TrieNode(char val) {
        this.val = val;
    }
}
