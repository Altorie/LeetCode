package com.sjy.easy;

import java.util.Arrays;

public class Day5 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        if (len == 1){
            return nums[0];
        }
        int[] dp = new int[len];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Integer.max(nums[i], dp[i-1]+nums[i]);
        }
        int result = Integer.MIN_VALUE;
        for (int i:dp){
            result = Integer.max(result, i);
        }
        return result;
    }

    /**
     * 一个区间 [l, r]的四个信息
     */
    class Status{
        int lSum; //以l为左端点的最大连续子段和
        int rSum; //以r为右端点的最大连续子段和
        int mSum; //[l, r]内的最大连续子段和
        int iSum; //[l, r]区间和

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    /**
     * 利用分治思想，通过线段树这一数据结构求解
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums){
        return getStatus(nums, 0, nums.length-1).mSum;
    }

    private Status getStatus(int[] arr, int l, int r){
        if(l == r){
            return new Status(1,1,1,1);
        }
        int m = (l+r)/2;
        Status lStatus = getStatus(arr, l, m);
        Status rStatus = getStatus(arr, m + 1, r);
        // 根据左右两个子区间的维护信息，求得本区间的信息
        // 1. iSum:等于 左子区间的iSum + 右子区间的iSum
        int iSum = lStatus.iSum + rStatus.iSum;
        /**
         * 2. lSum:以区间第一个点为端点的最大连续子段和
         * 要么等于它左子区间的lSum，要么等于它左子区间的iSum + 右子区间的lSum
         */
        int lSum = Integer.max(lStatus.lSum, lStatus.iSum + rStatus.lSum);
        /**
         * 3.rSum:以区间最后一个点为端点的最大连续字段和
         * 要么等于它右子区间的 rSum，要么等于右子区间的 iSum + 左子区间的rSum
         */
        int rSum = Integer.max(rStatus.rSum, rStatus.iSum + lStatus.rSum);
        /**
         * 4.mSum:区间内的最大连续子段和
         * 完全落在左子区间内，完全落在右子区间内，跨越左右两子区间
         */
        int mSum = Integer.max(Integer.max(lStatus.mSum, rStatus.mSum), lStatus.rSum + rStatus.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }

    /**
     * 给定一个由整数数组 A 表示的环形数组 C，求 C 的非空子数组的最大可能和
     * 结果只有两种情况
     *      1.这个子数组不同时包括起点和终点
     *      2.这个数组同时包括起点和终点
     * @param nums
     * @return
     */
    public int maxSubarraySumCircular(int[] nums) {
        int len = nums.length;
        if (len == 1){
            return nums[0];
        }
        // 计算第一种情况的最大值
        int[] dp = new int[len];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Integer.max(nums[i], dp[i-1]+nums[i]);
        }
        int result1 = Integer.MIN_VALUE;
        for (int i:dp){
            result1 = Integer.max(result1, i);
        }
        /**
         * 计算第二种情况的最大值。nums[0], ..., nums[l], ..., nums[r], ..., nums[len-1]
         * 目标是[0, l]和[r, len-1]区间内的元素之和最大，也就是要求[l+1, r-1]区间内的和最小
         */
        dp[1] = nums[1];
        for (int i = 2; i < len -1; i++) {
            dp[i] = Integer.min(nums[i], nums[i] + dp[i-1]);
        }
        int minSum = Integer.MAX_VALUE;
        for (int i = 1; i < len -1; i++) {
            minSum = Integer.min(minSum, dp[i]);
        }
        int sum = Arrays.stream(nums).sum();
        return Integer.max(result1, sum - minSum);
    }
}
