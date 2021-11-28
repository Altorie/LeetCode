package version.two;

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
 * @TIME 2021/11/27 21:05
 * @DESCRIPTION 
 **/
public class Day21 {

    public static void main(String[] args) {
        Day21 d = new Day21();
        d.replaceWords(Arrays.asList("a", "aa", "aaa", "aaaa"), "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa");
    }
    /**
     * 剑指 Offer II 063. 替换单词
     * @param dictionary
     * @param sentence
     * @return
     */
    public String replaceWords(List<String> dictionary, String sentence) {
        // 先利用字典中的所有单词构建一颗前缀树
        Trie trie = new Trie();
        for (String word : dictionary){
            trie.insert(word);
        }
        StringBuffer sb = new StringBuffer();
        String[] words = sentence.split(" ");
        TrieNode p = null;
        for (String word : words){
            char[] chars = word.toCharArray();
            p = trie.root;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (p.children[index]== null){
                    break;
                }
                if (p.children[index]!=null){
                    p = p.children[index];
                    if (p.isEnd){
                        word = word.substring(0, i+1);
                        break;
                    }
                }
            }
            sb.append(word).append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
