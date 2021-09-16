package com.sjy.easy;

public class Day7 {
    public static void main(String[] args) {
        Day7 d = new Day7();
        d.maxScoreSightseeingPair(new int[]{8,1,5,2,6});
    }

    /**
     * 给你一个正整数数组 values，其中 values[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的 距离 为 j - i。
     * 一对景点（i < j）组成的观光组c合的得分为 values[i] + values[j] + i - j ，也就是景点的评分之和 减去 它们两者之间的距离。
     * 返回一对观光景点能取得的最高分。
     *
     * 思路：
     *      讲 values[i] + values[j] + i - j 分解成 values[i] + i 和 values[j] - j 两部分
     * @param values
     * @return
     */
    public int maxScoreSightseeingPair(int[] values) {
        int len = values.length;
        int ans = 0;
        // j 之前的 values[i] + i 的最大值
        int mx = values[0] + 0;
        for (int j = 1; j < len; j++) {
            ans = Integer.max(ans, mx + values[j] - j);
            mx = Integer.max(mx, values[j] + j);
        }
        return ans;
    }

    /**
     * 121. 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     *
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     *
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int ans = 0;
        // 记录当前遍历位置之前的最小价格
        int min = prices[0];
        for (int j = 1; j < len; j++) {
            ans = Math.max(prices[j]-min, ans);
            min = Math.min(min, prices[j]);
        }
        return ans;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     * 给定一个数组 prices ，其中prices[i] 是一支给定股票第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 输入: prices = [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     *
     * 思路：
     *      如果第 i 天的价格比第 i+1 天低，那么第 i 天可以购入；对于 j > i，如果第 j 天的价格比第 j+1 天高，那么 j 为 i 对应的卖出天数
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        int buy = 0;
        int ans = 0;
        while (buy < len-1){
            // 找到买入天数
            while(buy < len -1 && prices[buy] >= prices[buy + 1]){
                buy++;
            }
            int sell = buy;
            while (sell < len-1 && prices[sell] <= prices[sell + 1]){
                sell++;
            }
            ans += prices[sell] - prices[buy];
            buy = sell + 1;
        }
        return ans;
    }
}
