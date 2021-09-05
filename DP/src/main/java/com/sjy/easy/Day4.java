package com.sjy.easy;

public class Day4 {
    public static void main(String[] args) {
        Day4 day4 = new Day4();
        System.out.println(day4.canJump(new int[]{1,2}));
    }

    /**
     * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个下标。
     *
     * 输入：nums = [2,3,1,1,4]
     * 输出：true
     * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int len = nums.length;
        if (len==1){
            return true;
        }
        if (nums[0] == 0){
            return false;
        }
        int[] dp = new int[len];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Integer.max(dp[i-1], i+nums[i]);
            if (dp[i]>=len-1){
                return true;
            }else if (dp[i] <= i){
                return false;
            }
        }
        return true;
    }
    
}
