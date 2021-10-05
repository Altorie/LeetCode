package com.sjy.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Day11 {
    public static void main(String[] args) {
        Day11 d = new Day11();
        System.out.println(d.nthUglyNumber(3));
    }

    /**
     * 264. 丑数 II
     * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。
     * 丑数 就是只包含质因数 2、3 和/或 5 的正整数。
     *
     * 输入：n = 10
     * 输出：12
     * 解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
     *
     * 输入：n = 1
     * 输出：1
     * 解释：1 通常被视为丑数。
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        /**
         * 可以通过，耗费时间很多
         */
//        List<Long> list = new ArrayList<>();
//        list.add(2l);
//        list.add(3l);
//        list.add(5l);
//        long temp = 1;
//        int count = 1;
//        while (count < n){
//            temp = list.get(0);
//            list.remove(0);
//            if (!list.contains(temp*2)){
//                list.add(temp*2);
//            }
//            if (!list.contains(temp*3)){
//                list.add(temp*3);
//            }
//            if (!list.contains(temp*5)){
//                list.add(temp*5);
//            }
//            Collections.sort(list);
//            count++;
//        }
//        return (int) temp;
        /**
         * 动态规划解法
         * 每一个质数，都是之前质数的2，3，5倍数中的一个。每一个质数能生成三个质数。
         * 定义三个指针p2,p3,p5，指向动态规划数组dp中的不同值。
         * dp[i]是 dp[p2] dp[p3] dp[p4]中的最小值。
         * 对应的指针+1，表示下一个质数不可能仍是这个质数的 2/3/5倍
         */
        int p2 = 1;
        int p3 = 1;
        int p5 = 1;
        int[] dp = new int[n+1];
        dp[1] = 1;
        for (int i = 2; i <= n ; i++) {
            dp[i] = Math.min(dp[p2]*2, Math.min(dp[p3]*3, dp[p5]*5));
            if (dp[i] == dp[p2]*2){
                p2++;
            }
            if (dp[i] == dp[p3]*3){
                p3++;
            }
            if (dp[i] == dp[p5]*5){
                p5++;
            }
        }
        return dp[n];
    }

    /**
     * 96. 不同的二叉搜索树
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
     * 返回满足题意的二叉搜索树的种数。
     * @param n
     * @return
     */
    public int numTrees(int n) {
        // dp[i]表示 n个节点的结果。n个节点组成的二叉树由一下情况组成
        // 左子树空，右子树 2~n共 n-1个节点；dp[0]*dp[n-1]
        // 左子树 1共 1个节点，右子树 3~n 共 n-2个节点 dp[1]*dp[n-2]
        // 左子树 1~2共 2个节点，右子树 4~n 共 n-3个节点 dp[2]*dp[n-3]
        // ...
        // 左子树 1~n-1共 n-1个节点，右子树空 dp[n-1]*dp[0]
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n ; i++) {
            for (int j = 0; j < i ; j++) {
                dp[i] += dp[j] * dp[i-j-1];
            }
        }
        return dp[n];
    }
}
