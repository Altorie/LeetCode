package com.sjy.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day3 {
    public static void main(String[] args) {

    }

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int len = nums.length;
        // m[i]为小偷光顾第 i 家时偷到的最多的钱
        int[] m = new int[len+1];
        m[1] = nums[0];
        for (int i = 2; i < len+1; i++) {
            m[i] = Integer.max(m[i-2]+nums[i-1], m[i-1]);
        }
        return m[len];
    }

    /**
     * 和上一题相同，只是此题中的房屋围城一圈，第一个房屋和最后一个房屋相邻
     *
     * 不难看出，数组的第一个值和最后一个值一定不会同时出现在结果中。因此只需将这个圆拆成
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        int len = nums.length;
        if (len == 1){
            return nums[0];
        }
        if (len == 2){
            return Integer.max(nums[0], nums[1]);
        }
        // m[i]为小偷光顾第 i 家时偷到的最多的钱
        int a = nums[0];
        int b = Integer.max(nums[1], a);
        for (int i = 2; i < len - 1; i++) {
            int temp = Integer.max(b, a+nums[i]);
            a = b;
            b = temp;
        }
        int result1 = b;
        a = nums[1];
        b = Integer.max(a, nums[2]);
        for (int i = 3; i < len; i++) {
            int temp = Integer.max(b, a+nums[i]);
            a = b;
            b = temp;
        }
        return Integer.max(b, result1);
    }

    /**
     * 给你一个整数数组 nums，你可以对它进行一些操作。
     *
     * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。
     *
     * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
     *
     * 解析：
     *      不能选择与 nums[i] 相邻的元素删除，可以将本题转化为 打家劫舍问题
     * @param nums
     * @return
     */
    public int deleteAndEarn(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        int[] m = new int[nums[len-1]+1];
        for (int i:nums){
            m[i] += i;
        }
        if (m.length == 1){
            return m[0];
        }
        int a = m[0];
        int b = Integer.max(a, m[1]);
        for (int i = 2; i < m.length; i++) {
            int temp = Integer.max(b, a+m[i]);
            a = b;
            b = temp;
        }
        return b;
    }
}
