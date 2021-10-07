package com.sjy.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day12 {
    public static void main(String[] args) {
        Day12 d = new Day12();
        List<List<Integer>> list = new ArrayList<>();
    }

    /**
     * 931. 下降路径最小和
     * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
     *
     * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。
     * 在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。
     * 具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
     * @param matrix
     * @return
     */
    public int minFallingPathSum(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        // dp[i][j]表示从位置（i, j）走出 i行的 matrix的最小和
        int[][] dp = new int[rows+1][cols];
        for (int i = 1; i <= rows; i++) {
            for (int j = 0; j < cols ; j++) {
                int min = dp[i-1][j];
                if (j-1>=0){
                    min = Math.min(min, dp[i-1][j-1]);
                }
                if (j+1<cols){
                    min = Math.min(min, dp[i-1][j+1]);
                }
                dp[i][j] = matrix[i-1][j] +min;
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i:dp[rows]){
            min = Math.min(min, i);
        }
        return min;
    }

    /**
     * 120. 三角形最小路径和
     * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
     * 每一步只能移动到下一行中相邻的结点上。
     * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
     * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        List<int[]> dp = new ArrayList<>();
        dp.add(new int[]{triangle.get(0).get(0)});
        for (int i = 1; i < len; i++) {
            List<Integer> row = triangle.get(i);
            int[] temp = new int[row.size()];
            for (int j = 0; j < row.size(); j++) {
                int min = Integer.MAX_VALUE;
                if (j < row.size()-1){
                    min = Math.min(min, dp.get(i-1)[j]);
                }
                if (j-1>=0){
                    min = Math.min(dp.get(i-1)[j-1], min);
                }
                temp[j] = triangle.get(i).get(j) + min;
            }
            dp.add(temp);
        }
        return Arrays.stream(dp.get(len-1)).min().getAsInt();
    }
}
