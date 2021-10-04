package com.sjy.easy;

public class Day10 {
    public static void main(String[] args) {
        Day10 d = new Day10();
        System.out.println('1' > '0');
    }

    /**
     * 413. 等差数列划分
     * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
     * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
     * 子数组 是数组中的一个连续序列。
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums.length < 3){
            return 0;
        }
        // dp[i]：以第 i个元素结尾的等差数列的个数
        // 如果满足 nums[i] - nums[i-1] == nums[i-1] - nums[i-2]，那么有 dp[i]+=dp[i-1]+1
        // 其他 dp[i]=0
        int[] dp = new int[nums.length];
        int ans = 0;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i-1] == nums[i-1] - nums[i-2]){
                dp[i]+=dp[i-1]+1;
            }
            ans += dp[i];
        }
        return ans;
    }

    /**
     * 91. 解码方法
     * 一条包含字母 A-Z 的消息通过以下映射进行了编码 ：
     *  'A' -> 1
     *  'B' -> 2
     *  ...
     *  'Z' -> 26
     * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
     * 题目数据保证答案肯定是一个 32 位 的整数。
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if ('0' == s.charAt(0)){
            return 0;
        }
        int len = s.length();
        // dp[i]存储前 i个字符的解码结果
        int[] dp = new int[len+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < len+1; i++) {
            if (s.charAt(i-1) == '0'){ // 如果当前数字是 0
                if (s.charAt(i-2) == '1' || s.charAt(i-2) == '2'){ // 且前一个数字是 1或2
                    // 那么只有  ***** [10]这种分法，dp[i] = dp[i-2]
                    dp[i] = dp[i-2];
                } else { // 其他情况都是 0
                    dp[i] = 0;
                }
            } else if (s.charAt(i-2) >= '3' || (s.charAt(i-2) == '2' && s.charAt(i-1) > '6')){// 如果最后两个数字无法被解码
                //只有 ****** *这种分法，dp[i] = dp[i-1]
                dp[i] = dp[i-1];
            } else if (s.charAt(i-2) == '0'){// 如果前一个数字为 0
                //只有 ****** *这种分法，dp[i] = dp[i-1]
                dp[i] = dp[i-1];
            } else {
                // 有 ***** * 和 **** ** 两种分法
                dp[i] = dp[i-1] + dp[i-2];
            }
        }
        return dp[len];
    }
}
