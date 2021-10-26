package com.sjy.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day1 {
    public static void main(String[] args) {
        System.out.println();
    }
    /**
     * 136. 只出现一次的数字
     * 一个数与其本身做异或运算结果为0
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result = result^nums[i];
        }
        return result;
    }

    /**
     * 169. 多数元素
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 n/2 的元素。
     *
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素.
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        // 1.先排序，再取中位数
        /*Arrays.sort(nums);
        return nums[nums.length / 2];*/

        // 2.Boyer-Moore 投票算法
        int candidate = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0){
                candidate = nums[i];
            } else {
                if (candidate == nums[i]){
                    count++;
                } else {
                    count--;
                }
            }
        }
        return candidate;
    }

    /**
     * 15. 三数之和
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
     * 请你找出所有和为 0 且不重复的三元组。
     *
     * 如果能够保真 a < b < c，那么使得 a + b + c = 0的解是唯一的。
     * 同时在遍历的过程中，要考虑到相邻元素相等的情况，此时可以直接跳过。因为此时是重复解。
     *
     * 当 a 固定时，由于 b < c ，若 a + b + c = 0，那么下一个不重复的解 a + b' + c' = 0 一定满足 b < b' < c' < c。
     * 可以使用双指针遍历优化时间
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums==null||nums.length<3){
            return  results;
        }
        Arrays.sort(nums);
        List<Integer> list = null;
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            // 如果 a 和上一次相同，直接跳过
            if (i>0&&a==nums[i-1]){
                continue;
            }
            int j = i+1;
            int k = nums.length -1;
            while(j<k){
                if (nums[j] + nums[k] == -a){
                    list = new ArrayList<>();
                    list.add(a);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    results.add(list);
                    j++;
                    while (nums[j]==nums[j-1]&&j<k){
                        j++;
                    }
                    k--;
                    while (nums[k]==nums[k+1]&&j<k){
                        k--;
                    }
                } else if (nums[j] + nums[k] <= -a){
                    j++;
                } else if (nums[j] + nums[k] >= -a){
                    k--;
                }
            }
        }
        return results;
    }
}
