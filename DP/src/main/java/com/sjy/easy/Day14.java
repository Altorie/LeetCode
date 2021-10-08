package com.sjy.easy;

public class Day14 {
    public static void main(String[] args) {
        Day14 d = new Day14();
        int[][] a = new int[][]{new int[]{1,2,3},new int[]{4,5,6},new int[]{7,8,9}};
        System.out.println(d.matrixBlockSum(a, 1));
    }

    /**
     * 1314. 矩阵区域和
     * 给你一个 m x n 的矩阵 mat 和一个整数 k ，请你返回一个矩阵 answer，其中每个 answer[i][j]是所有满足下述条件的元素 mat[r][c] 的和：
     *   i - k <= r <= i + k,
     *   j - k <= c <= j + k 且
     *   (r, c) 在矩阵内。
     * @param mat
     * @param k
     * @return
     */
    public int[][] matrixBlockSum(int[][] mat, int k) {
        /**
         * 法1 时间复杂度 O(mn)
         */
//        int rows = mat.length;
//        int cols = mat[0].length;
//        int[][] a = new int[rows][cols];
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                a[i][j] = mat[i][j];
//                for (int l = Math.max(0, j-k); l <= Math.min(cols-1, j+k); l++) {
//                    a[i][j] += mat[i][l];
//                }
//            }
//        }
//        int[][] b = new int[rows][cols];
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                b[i][j] = a[i][j];
//                for (int l = Math.max(0, i-k); l <= Math.min(rows-1, i+k); l++) {
//                    b[i][j] += a[l][j];
//                }
//            }
//        }
//        return b;
        /**
         * 法 2：使用二维数组的前缀和
         */
        int rows = mat.length;
        int cols = mat[0].length;
        // mat的二维前缀和数组
        int[][] p = new int[rows][cols];
        int temp1 = 0;
        for (int j = 0; j < cols; j++) {
            p[0][j] = mat[0][j] + temp1;
            temp1 += mat[0][j];
        }
        for (int i = 1; i < rows; i++) {
            int temp2 = 0;
            for (int j = 0; j < cols; j++) {
                p[i][j] = p[i-1][j] + mat[i][j] + temp2;
                temp2 += mat[i][j];
            }
        }
        // 计算区域和
        int[][] ans = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 左上角为 (i-k, j-k)，右下角为 (i+k, j+k)
                int x1 = Math.max(0, i-k);
                int y1 = Math.max(0, j-k);
                int x2 = Math.min(i+k, rows-1);
                int y2 = Math.min(j+k, cols-1);
                ans[i][j] = p[x2][y2];
                if (x1-1 >= 0){
                    ans[i][j] -= p[x1-1][y2];
                }
                if (y1-1 >= 0){
                    ans[i][j] -= p[x2][y1-1];
                }
                if (x1-1 >= 0 && y1-1 >= 0){
                    ans[i][j] += p[x1-1][y1-1];
                }
            }
        }
        return ans;
    }
}
