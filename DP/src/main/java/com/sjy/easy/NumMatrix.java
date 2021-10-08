package com.sjy.easy;

public class NumMatrix {
    private int[][] matrix; // 数组
    private int[][] p; // 数组的二维前缀和矩阵
    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        // 给 p 赋值
        int rows = matrix.length;
        int cols = matrix[0].length;
        p = new int[rows][cols];
        // 先计算第一行
        int temp = 0;
        for (int j = 0; j < cols; j++) {
            p[0][j] = matrix[0][j] + temp;
            temp = p[0][j];
        }
        // 计算剩下 rows-1 行
        for (int i = 1; i < rows; i++) {
            int temp2 = 0;
            for (int j = 0; j < cols; j++) {
                p[i][j]  = p[i-1][j] + matrix[i][j] + temp2;
                temp2 += matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int ans = p[row2][col2];
        if (row1 -1 >= 0){
            ans -= p[row1-1][col2];
        }
        if (col1 - 1 >= 0){
            ans -= p[row2][col1-1];
        }
        if (row1 -1 >= 0 && col1 - 1 >= 0){
            ans += p[row1-1][col1-1];
        }
        return ans;
    }
}
