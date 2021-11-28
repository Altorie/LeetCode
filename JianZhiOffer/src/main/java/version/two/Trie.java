package version.two;
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
 * @TIME 2021/11/27 20:32
 * @DESCRIPTION
 *      剑指 Offer II 062. 实现前缀树
 **/
public class Trie {
    public TrieNode root;
    public Trie() {
        root = new TrieNode('/');
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] chars = word.toCharArray();
        TrieNode p = root;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i]-'a';
            if (p.children[index] == null){
                p.children[index] = new TrieNode(chars[i]);
            }
            p = p.children[index];
        }
        p.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode p = root;
        char[] chars = word.toCharArray();
        for (char c : chars){
            int index = c-'a';
            if (p.children[index] == null){
                return false;
            }
            p = p.children[index];
        }
        return p.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode p = root;
        char[] chars = prefix.toCharArray();
        for (char c : chars){
            int index = c-'a';
            if (p.children[index] == null){
                return false;
            }
            p = p.children[index];
        }
        return true;
    }
}

class TrieNode{
    char val; // 这个节点存储的字符
    TrieNode[] children = new TrieNode[26]; // 子节点
    boolean isEnd; // 是否有以该节点为结束的字符串
    public TrieNode(char val){
        this.val = val;
    }
}