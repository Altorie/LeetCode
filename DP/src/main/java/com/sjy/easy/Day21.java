package com.sjy.easy;

public class Day21 {
    public static void main(String[] args) {
    }

    /**
     * 377. 组合总和 Ⅳ
     * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
     *
     * 顺序不同的序列被是做不同的组合
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 0; i <= target; i++) {
            for(int num : nums){
                if (i-num >= 0){
                    dp[i] += dp[i-num];
                }
            }
        }
        return dp[target];
    }

    /**
     * 343. 整数拆分
     * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        /**
         * 法 1：动态规划
         */
//        int[] dp = new int[n+1];
//        dp[1] = 1;
//        for (int i = 2; i <= n; i++) {
//            for (int j = 1; j <i ; j++) {
//                dp[i] = Math.max(j*Math.max(i-j, dp[i-j]), dp[i]);
//            }
//        }
//        return dp[n];
        /**
         * 法 2：数学
         * 将数字 n 尽可能以因子 3 等分时，乘积最大。
         *
         * 根据 n 除以 33 的余数进行分类讨论：
         * 如果余数为 0，即 n=3m(m≥2)，则将 n 拆分成 m 个 3；
         *
         * 如果余数为 1，即 n=3m+1(m≥1)，由于 2×2>3×1，因此将 n 拆分成 m-1 个 3 和 2 个 2；
         *
         * 如果余数为 2，即 n=3m+2(m≥1)，则将 n 拆分成 m 个 3 和 1 个 2。
         */
        if(n <= 3){
            return n-1;
        }
        int a = n / 3;
        int b = n % 3;
        if (b == 0){
            return (int) Math.pow(3, a);
        } else if (b == 1){
            return (int) Math.pow(3, a-1) * 4;
        } else {
            return (int)Math.pow(3, a)*2;
        }
    }

    /**
     * 279. 完全平方数
     * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
     *
     * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
     *
     * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。
     * 例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
\
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int num = (int) Math.pow(i, 0.5);
//            dp[i] = 1 + dp[i-num * num];
            if (i == num * num){
                dp[i] = 1;
            }else {
                dp[i] = Integer.MAX_VALUE;
            }
            for (int j = 1; j*j < i; j++) {
                dp[i] = Math.min(1+dp[i-j*j], dp[i]);
            }
        }
        return dp[n];
    }
}
