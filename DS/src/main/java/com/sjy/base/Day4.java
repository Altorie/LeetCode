package com.sjy.base;

public class Day4 {
    public static void main(String[] args) {
        Day4 d = new Day4();
        int[][] a = new int[][]{new int[]{1,2},new int[]{2,3},new int[]{3,4},new int[]{1,3}};
        d.quickSort(a, 0, 3);
    }

    /**
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     *
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int i = rows - 1;
        int j = 0;
        while(i >= 0 && j <= cols - 1){
            if(matrix[i][j] > target){
                i--;
            } else if(matrix[i][j] < target){
                j++;
            } else{
                return true;
            }
        }
        return false;
    }

    /**
     * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠
     * 注意:
     *      1.可以认为区间的终点总是大于它的起点。
     *      2.区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
     * 题解：
     *      移除最少的区间数，使剩余区间不重叠  等价于  找到最多的不重叠区间
     *      只需要不断选择区间结束点小的即可。
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        int ans = 0;
        int preEnd = Integer.MIN_VALUE;
        quickSort(intervals, 0, intervals.length-1);
        for (int[] arr : intervals){
            if (arr[0] >= preEnd){
                ans++;
                preEnd = arr[1];
            }
        }
        return intervals.length-ans;
    }

    public void quickSort(int[][] arr, int start, int end){
        if (start >= end){
            return;
        }
        int base = arr[start][1];
        int l = start;
        int r = end;
        for (int i = l; i <= r; i++) {
            while (i < r && arr[i][1] > base){
                int[] temp = arr[i];
                arr[i] = arr[r];
                arr[r] = temp;
                r--;
            }
            if (arr[i][1] < base){
                int[] temp = arr[i];
                arr[i] = arr[l];
                arr[l] = temp;
                l++;
            }
        }
        quickSort(arr, start, l);
        quickSort(arr, r, end);
    }
}
