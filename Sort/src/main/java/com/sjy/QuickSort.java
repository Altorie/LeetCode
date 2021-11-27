package com.sjy;

/**
 * 快速排序本质上是一个二叉树的前序遍历
 */
public class QuickSort {
    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        int[] a = new int[]{3,2,1,5,6,4};

    }

    /**
     * 快排
     * 第一种解法
     * @param nums
     * @param start
     * @param end
     */
    public void quick_sort1(int[] nums, int start, int end){
        if (start >= end){
            return;
        }
        int base = nums[start];
        int l = start;
        int r = end;
        for (int i = start; i <= r; i++) {
            while (i <= r && nums[i] > base){
                int temp = nums[i];
                nums[i] = nums[r];
                nums[r++] = temp;
            }
            if (nums[i] < base){
                int temp = nums[i];
                nums[i] = nums[l];
                nums[l++] = temp;
            }
        }
        quick_sort1(nums, start, l-1);
        quick_sort1(nums, r+1, end);
    }

    /**
     * 快排
     * 第二种解法
     * @param nums
     * @param start
     * @param end
     */
    public void quick_sort2(int[] nums, int start, int end){
        if (start >= end){
            return;
        }
        int base = nums[start];
        int l = start;
        int r = end;
        while(l < r){
            while (l<r && nums[r] >= base){
                r--;
            }
            while (l<r && nums[l] <= base){
                l++;
            }
            if (l!=r){
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
            }
        }
        int temp = nums[l];
        nums[l] = base;
        nums[start] = temp;
        quick_sort2(nums, start, l-1);
        quick_sort2(nums, l+1, end);
    }

    /**
     * 找到数组中第K大的元素
     * @param nums
     * @param k
     * @return
     */
    public int findMaxK(int[] nums, int start, int end, int k){
        if (start >= end){
            return nums[start];
        }
        int base = nums[start];
        int l = start;
        int r = end;
        for (int i = start; i <= r; i++) {
            while (i <= r && nums[i] < base){
                int temp = nums[i];
                nums[i] = nums[r];
                nums[r--] = temp;
            }
            if (nums[i] > base){
                int temp = nums[i];
                nums[i] = nums[l];
                nums[l++] = temp;
            }
        }
        if (k-1 < l){
            return findMaxK(nums, start, l-1, k);
        } else if (k-1 > r){
            return findMaxK(nums, r+1, end, k);
        }else {
            return nums[l];
        }
    }
}
