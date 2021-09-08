package com.sjy;

import java.util.ArrayList;
import java.util.List;

public class Day2 {
    public static void main(String[] args) {
        int[][] a = new int[][]{new int[]{1, 3}, new int[]{2, 6}, new int[]{8, 10}, new int[]{15, 18}};
        Day2 d = new Day2();
        d.merge(a);
    }

    /**
     * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     * @param nums
     */
    public void sortColors(int[] nums) {
        int len = nums.length;
        int l = 0;
        int r = len -1;
        for (int i = 0; i <= r; i++) {
            while (i < r && nums[i]==2){
                int temp = nums[r];
                nums[r] = nums[i];
                nums[i] = temp;
                r--;
            }
            if (nums[i] < 1){
                int temp = nums[l];
                nums[l] = nums[i];
                nums[i] = temp;
                while (l < len &&nums[l]<1 ){
                    l++;
                }
                i = l - 1;
            }
        }
    }

    /**
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        List<Integer> starts = new ArrayList<>();
        List<Integer> ends = new ArrayList<>();
        for(int[] arr : intervals){
            int start = arr[0];
            int end = arr[1];
            for (int i = 0; i < starts.size(); i++) {
                if (start > ends.get(i) || end < starts.get(i)){
                    continue;
                } else {
                    start = Integer.min(start, starts.get(i));
                    end = Integer.max(end, ends.get(i));
                    starts.remove(i);
                    ends.remove(i);
                    i--;
                }
            }
            starts.add(start);
            ends.add(end);

        }
        int len = starts.size();
        int[][] result = new int[len][2];
        for (int i = 0; i < len; i++) {
            result[i] = new int[]{starts.get(i), ends.get(i)};
        }
        return result;
    }
}
