package com.sjy.easy;

import java.util.*;

public class Day9 {
    public static void main(String[] args) {
        Day9 d = new Day9();
        System.out.println("abcbc".indexOf("abc"));
        System.out.println("catcat".substring(5-3+1,6));
    }

    /**
     * 139. 单词拆分
     * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s==null || s.equals("")){
            return false;
        }
        /**
         * 动态规划
         * dp[i]表示 s 前 i 个字符能否被拆分
         * dp[i]=1 的条件为：
         *      存在 dp[j]=1 && s.substring(j, i) 存在于字典里
         */
        int[] dp = new int[s.length()];
        dp[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            for (String word : wordDict){
                if (i - word.length() >= 0 && dp[i - word.length()] == 1 && wordDict.contains(s.substring(i-word.length(), i))){
                    dp[i] =1;
                }
            }
        }
        return dp[s.length()-1] == 1;
    }

    /**
     * 42. 接雨水
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     *
     * 第 i根柱子处能接到的雨水取决于 其左右两侧最高长度的柱子中较矮的那个
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int len = height.length;
        if (len < 3){
            return 0;
        }
        /**
         * 两个数组
         */
//        // 左边的最大值
//        int[] leftMax = new int[len];
//        int max = 0;
//        for (int i = 0; i < len-1; i++) {
//            max = Math.max(max, height[i]);
//            leftMax[i+1] = max;
//        }
//        // 右边的最大值
//        int[] rightMax = new int[len];
//        max = 0;
//        for (int i = len-1; i > 0 ; i--) {
//            max = Math.max(max, height[i]);
//            rightMax[i-1] = max;
//        }
//        int ans = 0;
//        for (int i = 0; i < len; i++) {
//            ans += Math.max(0, height[i] - Math.min(leftMax[i], rightMax[i]));
//        }
//        return ans;
        /**
         * 双指针
         */
        int left = 0;
        // 当前的height数组左侧的最大值
        int leftMax = 0;
        int right = len-1;
        // 当前的height数组右侧的最大值
        int rightMax = 0;
        int ans = 0;
        /**
         * 对于当前要计算的 left和 right.
         * 规则仍然不变：i 能接的雨水高度取决于 两侧最高元素中较矮的那个
         * 如果 leftMax > rightMax，那么不需要计算处 right左侧精确的 leftMax。结果一定是 rightMax - height[right]
         * 如果 rightMax > leftMax，结果一定是 leftMax - height[left]
         */
        while (left < right){
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax >= rightMax){
                ans += rightMax - height[right];
                right--;
            } else {
                ans += leftMax - height[left];
                left++;
            }
        }
        return ans;
    }
}
