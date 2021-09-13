package com.sjy.easy;

public class Day6 {
    public static void main(String[] args) {
        Day6 day6 = new Day6();
        System.out.println(day6.getMaxLen(new int[]{1,2,3,5,-6,4,0,10}));
    }

    /**
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int len = nums.length;
        int[] min = new int[len];
        int[] max = new int[len];
        min[0] = nums[0];
        max[0] = nums[0];
        for (int i = 1; i < len; i++) {
            max[i] = Integer.max(nums[i], Integer.max(nums[i] * max[i-1], nums[i] * min[i-1]));
            min[i] = Integer.min(nums[i], Integer.min(nums[i] * max[i-1], nums[i] * min[i-1]));
        }
        int ans = Integer.MIN_VALUE;
        for (int i : max){
            ans = Math.max(ans, i);
        }
        return ans;
    }

    /**
     * 给你一个整数数组 nums，请你求出乘积为正数的最长子数组的长度。
     *
     * 一个数组的子数组是由原数组中零个或者更多个连续数字组成的数组。
     *
     * 请你返回乘积为正数的最长子数组长度。
     *
     * @param nums
     * @return
     */
    public int getMaxLen(int[] nums) {
        int len = nums.length;
        // positive[i]：以索引i的元素结尾的乘积为整数的子数组的长度
        int[] positive = new int[len];
        // negative[i]：以索引i的元素结尾的乘积为负数的子数组的长度
        int[] negative = new int[len];
        positive[0] = nums[0] > 0?1:0;
        negative[0] = nums[0] < 0?1:0;
        for (int i = 1; i < len; i++) {
            if (nums[i] == 0){
                positive[i] = 0;
                negative[i] = 0;
            } else if (nums[i] > 0){
                positive[i] = positive[i-1] + 1;
                negative[i] = negative[i -1] == 0?0:negative[i-1] + 1;
            } else {
                positive[i] = negative[i-1] == 0?0 : negative[i-1] + 1;
                negative[i] = positive[i-1] + 1;
            }
        }
        int ans = positive[0];
        for (int i = 1; i < len; i++) {
            ans = Integer.max(ans, positive[i]);
        }
        return ans;
    }
}
