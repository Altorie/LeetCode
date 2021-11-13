package version.two;

import java.util.HashMap;
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
 * @TIME 2021/11/12 8:59
 * @DESCRIPTION 
 **/
public class Day6 {
    public static void main(String[] args) {
        Day6 d = new Day6();
        d.countSubstrings("aaa");
    }

    /**
     * 剑指 Offer II 017. 含有所有字符的最短字符串
     * 给定两个字符串 s 和 t 。返回 s 中包含 t 的所有字符的最短子字符串。
     * 如果 s 中不存在符合条件的子字符串，则返回空字符串 "" 。
     *
     * 如果 s 中存在多个符合条件的子字符串，返回任意一个.
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        int n = s.length();
        int m = t.length();
        if (n<m){
            return "";
        }
        // 用来存储窗口内当前还缺少的字符的数量
        // 'a':1 就表示窗口内还缺少 1个 A 字符
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()){
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        // value 代表窗口总共缺少的字符种类数（多个重复字符算一个值）
        int value = map.keySet().size();
        // 目前最小的窗口的起始和结束位置
        int start = -1, end = -1;
        // 目前最小的窗口长度
        int min = Integer.MAX_VALUE;
        int l = 0, r = 0;
        for (; r < n; r++) {
            if (value > 0 && map.containsKey(s.charAt(r))){ // 如果当前还有缺失，窗口向右增长
                // 新字符若是目标串中的字符，就更新缺失的数量
                int a = map.get(s.charAt(r))-1;
                map.put(s.charAt(r), a);
                if (a==0){ // 如果更新后为 0，表示不缺少这个字符，那总体的缺失量减 1
                    value--;
                }
            }
            if (value==0){ // 当前窗口已经包括了所有字符
                if (min > r-l+1){ // 更新一下变量
                    min = r-l+1;
                    start = l;
                    end = r;
                }
                while (value == 0){ // 窗口向右减少，知道缺失字符
                    char c = s.charAt(l);
                    if (!map.containsKey(c)){

                    } else { // 如果当前减去的字符是目标字符
                        int b = map.get(c) + 1;
                        map.put(c, b); // 更新其缺失量
                        if (b > 0){ // 如果更新后窗口内这个字符的数量有缺失
                            // 总体缺失量加一
                            value++;
                        }
                        if (value > 0 && min > r-l+1){ // 如果这个字符的减去将导致不能包括所有字符
                            // 更新变量
                            min = r - l+1;
                            start = l;
                            end = r;
                        }
                    }
                    l++;
                }
            }
        }
        if (start==-1){
            return "";
        }
        return s.substring(start, end+1);
    }

    /**
     * 剑指 Offer II 018. 有效的回文
     * 给定一个字符串 s ，验证 s 是否是 回文串 ，只考虑字母和数字字符，可以忽略字母的大小写。
     *
     * 本题中，将空字符串定义为有效的 回文串 。
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if (s==null || s.equals("")) return true;
        int l = 0, r = s.length()-1;
        while (l < r){
            char char1 = s.charAt(l);
            char char2 = s.charAt(r);
            if (!Character.isDigit(char1) && !Character.isAlphabetic(char1)){
                l++;
            } else if (!Character.isDigit(char2) && !Character.isAlphabetic(char2)){
                r--;
            } else {
                if (Character.isAlphabetic(char1)) char1 = Character.toLowerCase(char1);
                if (Character.isAlphabetic(char2)) char2 = Character.toLowerCase(char2);
                if (char1==char2){
                    l++;
                    r--;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 剑指 Offer II 019. 最多删除一个字符得到回文
     * 给定一个非空字符串 s，请判断如果 最多 从字符串中删除一个字符能否得到一个回文字符串。
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        if (s==null || s.equals("")) return true;
        boolean alreadyDelete = false;
        int l = 0, r = s.length()-1;
        while (l < r && s.charAt(l) == s.charAt(r)){
                l++;
                r--;

        }
        // 检测 l+1，r 和 l，r-1 是否是回文串
        boolean flag1 = true;
        int start = l+1, end = r;
        while (start < end){
            if (s.charAt(start)!=s.charAt(end)){
                flag1 = false;
                break;
            } else {
                start++;
                end--;
            }
        }
        boolean flag2 = true;
        start = l;
        end = r-1;
        while (start < end){
            if (s.charAt(start)!=s.charAt(end)){
                flag2 = false;
                break;
            } else {
                start++;
                end--;
            }
        }
        return flag1|flag2;

    }

    /**
     * 剑指 Offer II 020. 回文子字符串的个数
     * 给定一个字符串 s ，请计算这个字符串中有多少个回文子字符串。
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        /**
         * 动态规划，能ac，有点慢
         */
//        int len = s.length();
//        // dp[i][j] 表示子串 [i, j]是否为回文串
//        boolean[][] dp = new boolean[len][len];
//        for (int i = 0; i < len; i++) {
//            dp[i][i] = true;
//        }
//        for (int i = 0; i < len-1; i++) {
//            dp[i][i+1] = s.charAt(i) == s.charAt(i+1);
//        }
//        for (int i = 3; i <= len; i++) { // 遍历子串的长度
//            for (int j = 0; j+i-1 < len; j++) {
//                dp[j][j+i-1] = dp[j+1][j+i-2] & (s.charAt(j) == s.charAt(j+i-1));
//            }
//        }
//        int ans = 0;
//        for (int i = 0; i < len; i++) {
//            for (int j = 0; j < len; j++) {
//                if (dp[i][j])ans++;
//            }
//        }
//        return ans;
        /**
         * 中心扩展
         */
//        int n = s.length();
//        int ans = 0;
//        // 首先检测回文中心为奇数个的情况
//        for (int i = 0; i < n; i++) {
//            int l = i;
//            int r = i;
//            // 开始向两侧扩展
//            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)){
//                l--;
//                r++;
//                ans++;
//            }
//        }
//        // 检测回文中心为偶数个的情况
//        for (int i = 0; i < n-1; i++) {
//            int l = i;
//            int r = l+1;
//            // 开始向两侧扩展
//            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)){
//                l--;
//                r++;
//                ans++;
//            }
//        }
//        return ans;
        /**
         * 马拉车算法
         */
        // 首先对原字符串进行处理
        StringBuffer sb = new StringBuffer("#");
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i)).append("#");
        }
        String str = sb.toString();
        int n = str.length();
        int max_arm = -1; // 记录臂长的最远边界
        int p = -1; // 上述臂长对应的回文中心
        int[] arms = new int[n]; // 以每个元素为中心时的臂长
        for (int i = 0; i < n; i++) {
            int i_arm = 0; // i 为中心时的臂长
            if (i > max_arm){ // i 超出最远臂长范围
                int l=i-1,r=i+1;
                while (l >=0 && r < n && str.charAt(l) == str.charAt(r)){
                    i_arm++;
                    l--;
                    r++;
                }
                p = i;
                max_arm = r-1;
            } else { // i 位于最远臂长的范围内
                int j = 2*p-i; // j 是 i关于 p 的镜像节点
                int j_arm = arms[j];
                if (i + j_arm < max_arm){
                    i_arm = j_arm;
                } else {
                    i_arm += max_arm - i;
                    int l=i-i_arm-1,r=i+i_arm+1;
                    while (l >=0 && r < n && str.charAt(l) == str.charAt(r)){
                        i_arm++;
                        l--;
                        r++;
                    }
                    if (i+i_arm > max_arm){
                        p = i;
                        max_arm = r-1;
                    }
                }

            }
            arms[i] = i_arm;
        }
        int ans = 0;
        for (int arm : arms){
            ans += Math.ceil(arm/2d);
        }
        return ans;
    }
}
