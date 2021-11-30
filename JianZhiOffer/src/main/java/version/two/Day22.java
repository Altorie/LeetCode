package version.two;

import java.util.HashSet;
import java.util.Set;

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
 * @TIME 2021/11/28 10:45
 * @DESCRIPTION 
 **/
public class Day22 {
    private int ans;
    public static void main(String[] args) {

    }

    /**
     * 剑指 Offer II 065. 最短的单词编码
     *
     * 如果两个单词结尾相同，且较短的那个单词包含于较长的单词，那么这两个单词可以合并
     *
     * 把所有单词逆序，构建出一颗Trie树
     * @param words
     * @return
     */
    public int minimumLengthEncoding(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            StringBuffer sb = new StringBuffer();
            String s = sb.append(word).reverse().toString();
            trie.insert(s);
        }
        traversal(trie.root, 0);
        return ans;
    }
    private void traversal(TrieNode p, int length){
        boolean isLeaf = true;
        for (int i = 0; i < 26; i++) {
            if (p.children[i]!=null){
                isLeaf = false;
                traversal(p.children[i], length+1);
            }
        }
        if (isLeaf){
            ans += length;
            ans += 1;
        }
    }

    /**
     * 剑指 Offer II 067. 最大的异或
     * @param nums
     * @return
     */
    public int findMaximumXOR(int[] nums) {
        int ans = 0;
        for (int i = 30; i >=0 ; i--) { // 从高到低逐位判断 ans 在该位上能否取 1
            Set<Integer> set = new HashSet<>();
            // 记录每个数 从最高位（30）到第 i 个二进制位的数
            for (int num : nums){
                set.add(num >> i);
            }
            // 判断 ans 的第 i 为是否能取到 1
            // 首先不管能不能取到，ans 在最右边增加一位的结果是增加一倍
            ans = ans << 1;
            // 如果能取到，说明 set 中存在两个数的异或结果为 ans+1
            for (int num : set){
                if (set.contains((ans+1)^num)){
                    ans +=1;
                    break;
                }
            }

        }
        return ans;
    }


}
