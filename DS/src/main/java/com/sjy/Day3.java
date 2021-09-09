package com.sjy;

public class Day3 {
    public static void main(String[] args) {
        System.out.println(new Day3().generateMatrix(3));
    }

    /**
     * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     *
     * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int len = matrix.length;
//        int cols = matrix[0].length;
        // 遍历层
        for (int i = 0; i <= len/2; i++) {
            for (int j= i; j< len-1-i; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[len -1 - j][i];
                matrix[len -1 - j][i] = matrix[len -1 -i][len -1  - j];
                matrix[len -1 -i][len -1 - j] = matrix[j][len -1 -i];
                matrix[j][len -1 -i] = temp;
            }
        }
    }

    /**
     * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        if (n == 1){
            return new int[][]{new int[]{1}};
        }
        int[][] m = new int[n][n];
        int value = 0;
        for (int i = 0; i <= n/2; i++) {
            if (i == n-1-i){
                m[i][i] = ++value;
                continue;
            }
            for (int j = i; j < n-1-i; j++){
                m[i][j] = ++value;
            }
            for (int j = i; j < n-1-i; j++){
                m[j][n-1-i] = ++value;
            }
            for (int j = n-1-i; j>i; j--){
                m[n-1-i][j] = ++value;
            }
            for (int j = n-1-i; j>i; j--){
                m[j][i] = ++value;
            }
        }
        return m;
    }
}
