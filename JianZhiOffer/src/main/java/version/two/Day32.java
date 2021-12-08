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
 * @TIME 2021/12/7 10:53
 * @DESCRIPTION 
 **/
public class Day32 {
    /**
     * 剑指 Offer II 094. 最少回文分割
     * 给定一个字符串 s，请将 s 分割成一些子串，使每个子串都是回文串。
     * 返回符合要求的 最少分割次数 。
     *
     * 思路 1 ：回溯
     * 找出分割的所有可能情况，获取最少分割次数
     *
     * 思路 2：dp
     *
     * @param s
     * @return
     */
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n+1];
        // 存储同一个字符出现的不同位置，升序排列
        Map<Character, List<Integer>> map = new HashMap<>();
        // 存储[i, j]是否是回文串
        // 0：为检测   1：是   2：不是
        int[][] flags = new int[n][n];
        List<Integer> list = new ArrayList<>();
        list.add(0);
        map.put(s.charAt(0), list);
        for (int i = 2; i < n+1; i++) {
            dp[i] = Integer.MAX_VALUE;
            // 当前遍历到的字符
            char c = s.charAt(i-1);
            // 记录位置
            if (!map.containsKey(c)){
                List<Integer> l = new ArrayList<>();
                l.add(i-1);
                map.put(c, l);
            } else {
                map.get(c).add(i-1);
            }
            for (int j : map.get(c)){ // [j, i] 是否是一个回文字符串
                if (flags[j][i-1] == 0){
                    flags[j][i-1] = flag(s, j, i-1);
                }
                if (flags[j][i-1] == 1){
                    if (j==0){
                        dp[i] = Math.min(dp[i], dp[j]);
                    }else {
                        dp[i] = Math.min(dp[i], 1 + dp[j]);
                    }
                }
            }
        }
        return dp[n];
    }
    private int flag(String s, int l, int r){
        while (l < r){
            if (s.charAt(l) != s.charAt(r))return 2;
            l++;
            r--;
        }
        return 1;
    }


    /**
     * 剑指 Offer II 095. 最长公共子序列
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 剑指 Offer II 096. 字符串交织
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
//        int m = s1.length();
//        int n = s2.length();
//        int l = s3.length();
//        if (m == 0) return s2.equals(s3);
//        if (n == 0) return s3.equals(s1);
//        if (m+n!=l) return false;
//        boolean[][] dp = new boolean[m+1][n+1];
//        for (int i = 0; i < m+1; i++) {
//            if (s1.substring(0, i).equals(s3.substring(0,i))){
//                dp[i][0] = true;
//            }
//        }
//        for (int i = 0; i < n+1; i++) {
//            if (s2.substring(0, i).equals(s3.substring(0,i))){
//                dp[0][i] = true;
//            }
//        }
//        for (int i = 1; i < m+1; i++) {
//            for (int j = 1; j < n+1; j++) {
//                for (int k = 0; k <j ; k++) {
//                    if (dp[i][k] && s2.substring(k, j).equals(s3.substring(i+k, i+j))){
//                        dp[i][j] = true;
//                        break;
//                    }
//                }
//                if (!dp[i][j]){
//                    for (int k = 0; k < i; k++) {
//                        if (dp[k][j] && s1.substring(k, i).equals(s3.substring(j+k, i+j))){
//                            dp[i][j] = true;
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//        return dp[m][n];

        int m = s1.length();
        int n = s2.length();
        int l = s3.length();
        if (m == 0) return s2.equals(s3);
        if (n == 0) return s3.equals(s1);
        if (m+n!=l) return false;
        boolean[][] dp = new boolean[m+1][n+1];
        for (int i = 0; i < m+1; i++) {
            if (s1.substring(0, i).equals(s3.substring(0,i))){
                dp[i][0] = true;
            }
        }
        for (int i = 0; i < n+1; i++) {
            if (s2.substring(0, i).equals(s3.substring(0,i))){
                dp[0][i] = true;
            }
        }
        for (int i = 1; i <= m ; i++) {
            for (int j = 1; j <= n ; j++) {
                if (s3.charAt(i+j-1) == s1.charAt(i-1)){
                    dp[i][j] |= dp[i-1][j];
                }
                if (s3.charAt(i+j-1) == s2.charAt(j-1)){
                    dp[i][j] |= dp[i][j-1];
                }
            }
        }
        return dp[m][n];
    }
}
