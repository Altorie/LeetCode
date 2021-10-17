package com.sjy.easy;

public class Day17 {
    public static void main(String[] args) {

    }

    /**
     * 516. 最长回文子序列
     * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
     *
     * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        // dp[i][j]表示字符串 s 的下标范围 [i, j] 内的最长回文子序列的长度
        int[][] dp = new int[len][len];
        for (int i = len-1; i >= 0 ; i--) {
            dp[i][i] = 1;
            for (int j = i+1; j < len; j++) {
                //dp[i][j] dp[i+1][j-1]
                if (s.charAt(i) == s.charAt(j)){
                    dp[i][j] = 2 + dp[i+1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][len-1];
    }
}
