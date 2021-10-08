package com.sjy.easy;

public class Day15 {
    public static void main(String[] args) {
        Day15 d = new Day15();
        System.out.println(d.uniquePaths(3, 7));
    }

    /**
     * 62. 不同路径
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     *
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     *
     * 问总共有多少条不同的路径？
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        /**
         * 法 1：使用递归
         * f(m, n) = f(m-1, n) + f(m, n-1)
         * 超出时间限制
         */
//        if (m==0 && n==0){
//            return 1;
//        }
//        int ans = 0;
//        if (m-1 >= 0){
//            ans += uniquePaths(m-1, n);
//        }
//        if (n-1 >= 0){
//            ans += uniquePaths(m, n-1);
//        }
//        return ans;
        /**
         * 法 2：基于动态规划
         */
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }

        }
        return dp[m-1][n-1];
        /**
         * 法 3：组合数学
         * 机器人从 (1,1)走到 (m,n)，一档要走 m+n-2步，且其中 m-1步是向下走。
         * 因此只需计算出C_{m+n-2}^{m-1}即可
         */
    }

    /**
     * 63. 不同路径 II
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     *
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     *
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1){// 有障碍物
                break;
            } else {
                dp[i][0] = 1;
            }
        }
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 1){//
                break;
            } else {
                dp[0][j] = 1;
            }

        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] != 1){
                    dp[i][j] = 0;
                    if (obstacleGrid[i-1][j] == 0){
                        dp[i][j] += dp[i-1][j];
                    }
                    if (obstacleGrid[i][j-1] == 0){
                        dp[i][j] += dp[i][j-1];
                    }
                }
            }
        }
        return dp[m-1][n-1];
    }
}
