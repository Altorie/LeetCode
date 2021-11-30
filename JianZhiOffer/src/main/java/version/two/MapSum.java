package version.two;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @TIME 2021/11/29 8:41
 * @DESCRIPTION
 *      剑指 Offer II 066. 单词之和
 **/
public class MapSum {

    private Map<String, Integer> map;
    private Trie trie;

    public MapSum() {
        map = new HashMap<>();
        trie = new Trie();
    }

    public void insert(String key, int val) {
        map.put(key, val);
        trie.insert(key);
    }

    public int sum(String prefix) {
        List<String> list = new ArrayList<>();
        // 判断是否存在前缀
        TrieNode p = trie.root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (p.children[index] == null)return 0;
            p = p.children[index];
        }
        search(prefix, p, list, new ArrayList<>());
        int ans = 0;
        for (String str : list){
            ans += map.getOrDefault(str, 0);
        }
        return ans;
    }

    private void search(String prefix, TrieNode p, List<String> list, List<Character> path){
        if (p.isEnd){
            StringBuffer sb = new StringBuffer();
            sb.append(prefix);
            for (Character c : path){
                sb.append(c);
            }
            list.add(sb.toString());
        }
        for (int i = 0; i < 26; i++) {
            if (p.children[i]!=null){
                path.add(p.children[i].val);
                search(prefix, p.children[i], list, path);
                path.remove(path.size()-1);
            }
        }
    }
}
