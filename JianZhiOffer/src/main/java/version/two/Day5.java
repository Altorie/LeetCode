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
 * @TIME 2021/11/10 22:24
 * @DESCRIPTION 
 **/
public class Day5 {
    public static void main(String[] args) {
        Day5 d = new Day5();
        System.out.println(d.lengthOfLongestSubstring(
                " "));
    }

    /**
     * 剑指 Offer II 014. 字符串中的变位词
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            --cnt[s1.charAt(i) - 'a'];
            ++cnt[s2.charAt(i) - 'a'];
        }
        int diff = 0;
        for (int c : cnt) {
            if (c != 0) {
                ++diff;
            }
        }
        if (diff == 0) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            int x = s2.charAt(i) - 'a', y = s2.charAt(i - n) - 'a';
            if (x == y) {
                continue;
            }
            if (cnt[x] == 0) {
                ++diff;
            }
            ++cnt[x];
            if (cnt[x] == 0) {
                --diff;
            }
            if (cnt[y] == 0) {
                ++diff;
            }
            --cnt[y];
            if (cnt[y] == 0) {
                --diff;
            }
            if (diff == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 剑指 Offer II 015. 字符串中的所有变位词
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的 变位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     *
     * 变位词 指字母相同，但排列不同的字符串。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/VabMRr
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int n = s.length();
        int m = p.length();
        if (n < m) return ans;
        // 记录两个字符串的区别。
        int[] cnt = new int[26];
        for (int i = 0; i < m; i++) {
            cnt[p.charAt(i)-'a']--;
            cnt[s.charAt(i)-'a']++;
        }
        // 两个字符串中不同字符的个数
        int diff = 0;
        for (int c : cnt){
            if (c!=0)diff++;
        }
        if (diff==0)ans.add(0);
        for (int i = 1; i+m-1 < n; i++) {
            int in = s.charAt(i+m-1)-'a';
            int out = s.charAt(i-1)-'a';
            if (cnt[in] == 0){ // 如果本来该字符没有差异
                diff++;
            }
            cnt[in]++;
            if (cnt[in] == 0){ // 如果该字符加入后两个字符串在该字符上没有差异
                diff--;
            }
            if (cnt[out] == 0){
                diff++;
            }
            cnt[out]--;
            if (cnt[out] == 0){
                diff--;
            }
            if (diff==0)ans.add(i);
        }
        return ans;
    }

    /**
     * 剑指 Offer II 016. 不含重复字符的最长子字符串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        // 记录当前窗口内出现的字符数量
        Map<Character, Integer> map = new HashMap<>();
        int ans = 0;
        int l=0, r=0, temp=0;
        for (; r < len; r++) {
            char c = s.charAt(r);
            map.put(c, map.getOrDefault(c, 0)+1);
            if (map.getOrDefault(c, 0)==1){
                temp++;
                ans = Math.max(ans, temp);
            } else {
                // 移动l指针
                while (l < r && map.getOrDefault(s.charAt(l),0) < 2){
                    map.put(s.charAt(l), 0);
                    l++;
                    temp--;
                }
                map.put(s.charAt(l), 1);
                l++;
            }
        }
        return ans;
    }
}
