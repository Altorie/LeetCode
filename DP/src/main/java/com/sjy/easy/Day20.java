package com.sjy.easy;

import java.util.HashMap;
import java.util.Map;

public class Day20 {
    public static void main(String[] args) {
        Day20 d = new Day20();
        d.change(5, new int[]{1,2,5});
    }

    /**
     * 322. 零钱兑换
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     *
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     *
     * 你可以认为每种硬币的数量是无限的。
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0){
            return 0;
        }
        int len = coins.length;
        int[][] dp = new int[amount+1][len+1];
        for (int i = 1; i <= amount; i++) {
            for (int j = 1; j <= len; j++) {
                int coin = coins[j-1];
                if (i == coin){
                    dp[i][j] = 1;
                } else if (i-coin > 0){
                    int min = Integer.MAX_VALUE;
                    for(int value : dp[i-coin]){
                        if (value != 0){
                            min = Math.min(min, value);
                        }
                    }
                    if (min != Integer.MAX_VALUE){
                        dp[i][j] = min + 1;
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int value : dp[amount]) {
            if (value != 0){
                ans = Math.min(ans, value);
            }
        }
        return ans==Integer.MAX_VALUE?-1:ans;
    }

    /**
     * 518. 零钱兑换 II
     * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
     *
     * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
     *
     * 假设每一种面额的硬币有无限个.
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int len = coins.length;
        // dp[i]：组成 金额 i的组合数
        int[] dp = new int[amount+1];
        dp[0] = 1;
        // 为什么外层循环一定是 硬币面额？
        // 首先，题目要求是组合数，不能重复。
        // 如果外层循环是 总金额，那么可能会出现 选择不同的硬币面额作为结尾导致重复，如 1 2 和 2 1
        // 但是如果固定硬币面额，那么对于不同的总金额，硬币组合一定是不同的。
        for(int coin : coins){
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i-coin];
            }
        }

        return dp[amount];
    }
}
