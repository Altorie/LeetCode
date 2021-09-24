package com.sjy;

public class SelectionSort {
    public static void main(String[] args) {

    }

    /**
     * 选择排序
     * @param nums
     */
    public void selection_sort(int[] nums){
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            int min_index = i;
            for (int j = i; j < len; j++) {
                min_index = nums[min_index] <= nums[j]?min_index:j;
            }
            int temp = nums[min_index];
            nums[min_index] = nums[i];
            nums[i] = temp;
        }
    }
}
