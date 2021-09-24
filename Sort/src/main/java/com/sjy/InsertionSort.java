package com.sjy;

public class InsertionSort {
    public static void main(String[] args) {

    }

    public void insertion_sort(int[] nums){
        int len = nums.length;
        if (len < 2){

        }else {
            for (int i = 1; i < len; i++) {
                // 寻找第i个元素在有序区的位置
                int j = i-1;
                int temp = nums[i];
                while (j >= 0 && nums[j] > temp){
                    nums[j+1] = nums[j];
                    j--;
                }
                nums[j+1] = temp;
            }
        }

    }
}
