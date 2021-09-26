package com.sjy.easy;

import java.util.ArrayList;
import java.util.List;

public class Day8 {
    public static void main(String[] args) {
        Day8 d = new Day8();
        int[] a = new int[]{6,1,3,2,4,7};
        d.maxProfit5(a);

    }

    /**
     * 309. 最佳买卖股票时机含冷冻期
     *
     * 给定一个整数数组，其中第 i个元素代表了第 i天的股票价格 。
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * @param prices
     * @return
     */
    public int maxProfit5(int[] prices) {
        int len = prices.length;
        if (len<2){
            return 0;
        }
        // dp[i]表示第i天的最大利润
        // dp[i][0]表示这天持股
        // dp[i][1]表示这天未持股，由于卖出了股票
        // dp[i][2]表示这天未持股，但没卖股票
        int[][] dp = new int[prices.length][3];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = 0;
        for (int i = 1; i < len; i++) {
            // 昨天就持有股票  或者 今天买入（昨天一定没股票且不是昨天卖出造成的）
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2]-prices[i]);
            // 只有可能昨天持有股票，今天卖出
            dp[i][1] = dp[i-1][0] + prices[i];
            // 昨天有股票然后卖了   或者   昨天本来就没有股票
            dp[i][2] = Math.max(dp[i-1][1], dp[i-1][2]);
        }
        return Math.max(dp[len-1][1], dp[len-1][2]);
    }

    /**
     * 714. 买卖股票的最佳时机含手续费
     * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
     *
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     *
     * 返回获得利润的最大值。
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit6(int[] prices, int fee) {
        int len = prices.length;
        if (len < 2){
            return 0;
        }
        int[][] dp = new int[len][2];
        // dp[i]表示第i天的最大利润
        // dp[i][0]表示这天持股
        // dp[i][1]表示这天未持股
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]-prices[i]);
            dp[i][1] = Math.max(dp[i-1][0] + prices[i] - fee, dp[i-1][1]);
        }
        return Math.max(dp[len-1][0], dp[len-1][1]);
    }
}
