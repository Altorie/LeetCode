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
 * @TIME 2021/11/28 9:12
 * @DESCRIPTION
 *      剑指 Offer II 064. 神奇的字典
 **/
public class MagicDictionary {

    private TrieNode root = new TrieNode('/');

    private int count = 0;

    public MagicDictionary() {

    }

    public void buildDict(String[] dictionary) {
        TrieNode p = null;
        for (String word : dictionary){
            p = root;
            char[] chars = word.toCharArray();
            for (char c : chars){
                int index = c-'a';
                if (p.children[index] == null){
                    p.children[index] = new TrieNode(c);
                }
                p = p.children[index];
            }
            p.isEnd = true;
        }
    }

    public boolean search(String searchWord) {
        TrieNode p = root;
        char[] chars = searchWord.toCharArray();
        return backtrack(p, chars, 0);
    }
    private boolean backtrack(TrieNode p, char[] chars, int i){
        if (i == chars.length){
            if (p.isEnd && count == 1)return true;
            return false;
        }
        boolean ans = false;
        int index = chars[i]-'a';
        for (int j = 0; j < 26; j++) {
            if (p.children[j]!=null){
                if (j!=index)count++;
                ans |= backtrack(p.children[j], chars, i+1);
                if (j!=index)count--;
            }

        }

        return ans;
    }
}
