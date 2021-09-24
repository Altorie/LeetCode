package com.sjy;

public class BubbleSort {
    public static void main(String[] args) {

    }

    public void bubble_sort(int[] nums){
        int len = nums.length;
        for (int i = 0; i < len-1; i++) {
            // 如果一趟下来都没有发生交换，那么此时数组已经有序
            boolean flag = false;
            for (int j = 0; j < len - i -1; j++) {
                if (nums[j]>nums[j+1]){
                    int temp = nums[j];
                    nums[j]=nums[j+1];
                    nums[j+1] = temp;
                    flag = true;
                }
            }
            if (!flag){
                break;
            }
        }
    }
}
