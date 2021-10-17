package com.sjy.easy;

public class Day16 {
    public static void main(String[] args) {
        Day16 d = new Day16();
        d.maximalSquare(new char[][]{new char[]{'1', '1', '0', '1'},new char[]{'1', '1', '0', '1'},new char[]{'1','1','1','1'}});
    }

    /**
     * 64. 最小路径和
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 每次只能向下或者向右移动一步。
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols];
        int temp = 0;
        for (int i = 0; i < rows; i++) {
            dp[i][0] = grid[i][0] + temp;
            temp += grid[i][0];
        }
        temp = 0;
        for (int i = 0; i < cols; i++) {
            dp[0][i] = grid[0][i] + temp;
            temp += grid[0][i];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[rows-1][cols-1];
    }

    /**
     * 221. 最大正方形
     * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int max = 0;
        int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            dp[i][0] = matrix[i][0]-'0';
            max = Math.max(max, dp[i][0]);
        }
        for (int i = 0; i < cols; i++) {
            dp[0][i] = matrix[0][i]-'0';
            max = Math.max(max, dp[0][i]);
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j]=='0'){
                    dp[i][j] = 0;
                } else if(matrix[i-1][j-1]=='0'){
                    dp[i][j] = 1;
                    max = Math.max(max, dp[i][j]);
                } else {
                    dp[i][j]=1+Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }


}
