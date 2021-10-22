package com.sjy.easy;

public class Day19 {
    public static void main(String[] args) {
        Day19 d = new Day19();
        System.out.println(d.minDistance("a", "a"));
    }

    /**
     * 392. 判断子序列
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
     * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();
        if (len1 == 0){
            return true;
        } else if (len2 ==0){
            return false;
        }
        int s_pointer = 0;
        int t_pointer = 0;
        char target = s.charAt(s_pointer);
        while (t_pointer < len2){
            if (target == t.charAt(t_pointer)){
                if (s_pointer == len1-1){
                    return true;
                } else {
                    target = s.charAt(++s_pointer);
                }
            }
            t_pointer++;
        }
        return false;
    }

    /**
     * 1143. 最长公共子序列
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
     *
     * 一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     *
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
     * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        // dp[i][j]: text1的前 i个字符 与 text2的前 j个字符 的最长公共子序列
        // 动态转移方程为：
        //            dp[i][j] = dp[i-1][j-1] + 1(text1[i] = text2[j])
        int[][] dp = new int[len1][len2];
        dp[0][0] = text1.charAt(0)==text2.charAt(0)?1:0;
        for (int i = 1; i < len1; i++) {
            if (dp[i-1][0] == 1 || text1.charAt(i)==text2.charAt(0)){
                dp[i][0] = 1;
            }
        }
        for (int i = 1; i < len2; i++) {
            if (dp[0][i-1]==1 || text1.charAt(0)==text2.charAt(i)){
                dp[0][i] = 1;
            }
        }
        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                dp[i][j] = dp[i-1][j-1];
                if (text1.charAt(i)==text2.charAt(j)){
                    dp[i][j]++;
                } else {
                    dp[i][j] = Math.max(dp[i][j], Math.max(dp[i-1][j], dp[i][j-1]));
                }
            }
        }
        return dp[len1-1][len2-1];
    }

    /**
     * 72. 编辑距离
     * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数。
     *
     * 你可以对一个单词进行如下三种操作：
     *
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        if (len2==0){
            return len1;
        }
        // dp[i][j]表示将 word1 的前 i 个字符编辑为 word2 的前 j 个字符的最少操作数
        int[][] dp = new int[len1+1][len2+1];
        dp[0][0] = 0;
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)){// 不需要操作
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    // 三种情况：插入、删除、替换
                    // 插入：dp[i][j] = dp[i][j-1] +1（）
                    // 删除：dp[i][j] = dp[i-1][j] +1（）
                    // 替换：dp[i][j] = dp[i-1][j-1] +1
                    dp[i][j] = Math.min(Math.min(dp[i][j-1]+1, dp[i-1][j]+1), dp[i-1][j-1]+1);
                }
            }
        }
        return dp[len1][len2];
    }
}
