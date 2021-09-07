package com.sjy.easy;

public class Day4 {
    public static void main(String[] args) {
        Day4 day4 = new Day4();
        System.out.println(day4.jump(new int[]{2,3,1,1,4}));
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

    /**
     * 给你一个非负整数数组nums ，你最初位于数组的第一个位置。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     * 假设你总是可以到达数组的最后一个位置。
     *
     * 输入: nums = [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     *     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。

     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int len = nums.length;
        if (len == 1 || len == 2){
            return len - 1;
        }
        int[] dp = new int[len];
        dp[0] = 0;
        for (int i = 0; i < len-1; i++) {
            int r = Integer.min(i+nums[i], len-1);
            for (int j = i+1; j <= r; j++) {
                if (dp[j]==0){
                    dp[j] = dp[i]+1;
                }
                if (j == len-1){
                    return dp[j];
                }
            }
        }
        return dp[len-1];
    }
    
}
