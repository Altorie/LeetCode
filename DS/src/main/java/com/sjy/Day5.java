package com.sjy;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

public class Day5 {
    public static void main(String[] args) {
        Day5 d = new Day5();
        System.out.println(d.division(-28576800*9, 9));
    }

    /**
     * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        int min = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;
        for (int n : nums){
            if (n < min){
                min = n;
            } else if (n < mid){
                mid = n;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     * 不能使用除法
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] out = new int[len];
        // 先使用out[i]保存i左边所有元素的乘积
        out[0] = 1;
        for (int i = 1; i < len; i++) {
            out[i] = out[i-1] * nums[i];
        }
        // 用不断更新的R记录i右边的元素的乘积
        int R = 1;
        // 从右边开始遍历
        for (int i = len -1; i >=0;i--){
            out[i] *= R;
            R *= nums[i];
        }
        return out;
    }

    public int division(long a, int b){// a是被除数，b是除数
        boolean flag = false;
        if (a * b < 0){
            flag = true;
        }
        a = Math.abs(a);
        b = Math.abs(b);
        int result = 0;
        while (a >= b){
            int base = 1;
            while (a >= (long) base * b){
                base *= 2;
            }
            result += base/2;
            a = a- base*b/2;
        }
        if (flag){
            result = -result;
        }
        return result;
    }

    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        /*int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0] == k?1:0;
        for (int i = 1; i < len; i++) {
            int j = i;
            int sum = 0;
            while (j>=0){
                sum += nums[j];
                if (sum == k){
                    dp[i] = dp[i-1] + 1;
                    break;
                }
                if (sum > k){
                    dp[i] = dp[i-1];
                    break;
                }
                j--;
            }
        }
        return dp[len-1];*/

        // 利用前缀和 + 哈希表
        int pre = 0, ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        // 应对数组第一个元素就等于 k 的情况
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            int target = pre - k;
            if (map.containsKey(target)){
                ans += map.get(target);
            }
            map.put(pre, map.getOrDefault(pre, 0)+1);
        }
        return ans;
    }
}
