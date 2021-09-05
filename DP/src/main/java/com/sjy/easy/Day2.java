package com.sjy.easy;

public class Day2 {
    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     *
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if(n == 1 || n == 2){
            return n;
        }
        int a = 1;
        int b = 2;
        int temp = 0;
        for (int i = 0; i < n - 2; i++) {
            temp = a+b;
            a = b;
            b = temp;
        }
        return b;
    }

    /**
     * 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。
     *
     * 每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。
     *
     * 请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
     *
     * 输入：cost = [10, 15, 20]
     * 输出：15
     * 解释：最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15 。
     *
     * 假设数组长度为 i，那么爬到楼顶可以看作爬到第 i+1层楼梯。设爬到第 i层楼梯的费用为 F(i):
     *      F(i) = min{ cost[i-2]+F(i-2), cost[i-1]+F(i-1) }
     * 因为想爬到第 i 层，只有从第 i-2 层走两步  和  从 i-1 层走一步两种情况
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        if (len == 2){
            return Integer.min(cost[0], cost[1]);
        }
        /*int[][] m = new int[len+2][len+2];
        int[] min = new int[len+2];
        min[1] = 0;
        min[2] = 0;
        for (int i = 3; i < len+2; i++) {
            min[i] = Integer.MAX_VALUE;
            for (int j = i-2; j < i; j++) {
                m[i][j] = cost[j-1] + min[j];
                if (m[i][j] < min[i]){
                    min[i] = m[i][j];
                }
            }
        }
        return min[len+2];*/

        int[] m = new int[len+1];
        m[0] = 0;
        m[1] = 0;
        for (int i = 2; i < len+1; i++) {
            m[i] = Integer.min(cost[i-2]+m[i-2], cost[i-1]+m[i-1]);
        }
        return m[len];
    }
}
