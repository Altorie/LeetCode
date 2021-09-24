package com.sjy;

public class MergeSort {
    public static void main(String[] args) {

    }

    public void merge_sort(int[] nums, int start, int end){
        if (start >= end){
            return;
        }
        int mid = start + (end-start)/2;
        merge_sort(nums, start, mid);
        merge_sort(nums, mid+1, end);
        int[] temp = new int[end - start + 1];
        int l = start;
        int r = mid+1;
        int i = 0;
        while (l <= mid && r <= end){
            if (nums[l] < nums[r]){
                temp[++i] = nums[l];
                l++;
            } else {
                temp[++i] = nums[r];
                r++;
            }
        }
        int a = l;
        int b = mid;
        if (l > mid){
            a = r;
            b = end;
        }
        for (int j = a; j <= b ; j++) {
            temp[++i] = nums[j];
        }
        // 将temp数组的内容拷贝回原数组
        i = 0;
        for (int j = start; j <= end; j++) {
            nums[j] = temp[i++];
        }

    }
}
