package version.one;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
 * @TIME 2021/12/28 9:20
 * @DESCRIPTION 
 **/
public class Day13 {
    /**
     * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {
        int n = nums.length;
        int l = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 != 0){
                int temp = nums[i];
                nums[i] = nums[l];
                nums[l] = temp;
                l++;
            }
        }
        return nums;
    }

    /**
     * 剑指 Offer 57. 和为s的两个数字
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        while (l < r){
            int sum = nums[l] + nums[r];
            if (sum == target){
                return new int[]{nums[l], nums[r]};
            } else if (sum > target){
                r--;
            } else {
                l++;
            }
        }
        return new int[]{};
    }

    /**
     * 剑指 Offer 58 - I. 翻转单词顺序
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        s = s.trim();
        StringBuilder sb = new StringBuilder();
        for (int i = s.length()-1; i >= 0; i--) {
            if (s.charAt(i)!=' '){
                int j = i;
                while (j >= 0 && s.charAt(j) != ' '){
                    j--;
                }
                sb.append(s.substring(j+1, i+1)).append(" ");
                i = j;
            }
        }
        return sb.toString().trim();
    }

    /**
     * 472. 连接词
     * @param words
     * @return
     */
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> list = new ArrayList<>();
        // 先把字符串数组排序
        Arrays.sort(words, Comparator.comparingInt(String::length));
        TrieTree trie = new TrieTree();
        for (String word : words){
            if ("".equals(word))continue;
            if (isConcatenatedWord(word, trie.root)){
                list.add(word);
            } else {
                trie.add(word);
            }
        }
        return list;
    }
    private boolean isConcatenatedWord(String word, TrieNode root){
        if(word.equals("")){
            return true;
        }
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (p.children[index] == null){
                return false;
            } else {
                if (p.children[index].isEnd){ //
                    if (isConcatenatedWord(word.substring(i+1), root)){
                        return true;
                    }
                }
                p = p.children[index];
            }
        }
        return false;
    }
}
class TrieTree{
    TrieNode root = new TrieNode('/');

    public void add(String word){
        char[] chars = word.toCharArray();
        TrieNode p = root;
        for (char c : chars){
            int index = c - 'a';
            if (p.children[index] == null){
                p.children[index] = new TrieNode(c);
            }
            p = p.children[index];
        }
        p.isEnd = true;
    }
}
class TrieNode{
    char value;
    TrieNode[] children = new TrieNode[26];
    boolean isEnd;

    public TrieNode(char value) {
        this.value = value;
    }
}

