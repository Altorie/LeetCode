package version.two;

import java.util.HashMap;
import java.util.Map;
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
 * @TIME 2021/11/16 15:02
 * @DESCRIPTION 
 **/
public class Day10 {

    /**
     * 剑指 Offer II 032. 有效的变位词
     * 给定两个字符串 s 和 t ，编写一个函数来判断它们是不是一组变位词（字母异位词）。
     *
     * 注意：若 s 和 t 中每个字符出现的次数都相同且字符顺序不完全相同，则称 s 和 t互为变位词（字母异位词）
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        /**
         * 如果 s 和 t 包括 unicode字符
         */
        int n = s.length();
        int m = t.length();
        if (m!=n)return false;
        boolean flag1 = true; // 是否顺序相同
        Map<Character, Integer> map1 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map1.put(s.charAt(i), map1.getOrDefault(s.charAt(i), 0)+1);
            flag1 &= s.charAt(i) == t.charAt(i);
        }
        if (flag1)return false;
        for (int i = 0; i < n; i++) {
            char c = t.charAt(i);
            if (map1.getOrDefault(c, 0) == 0)return false;
            map1.put(c, map1.get(c)-1);
        }
        return true;
    }
}
