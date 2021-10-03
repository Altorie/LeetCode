package com.sjy;

import java.util.Arrays;

public class MyBinarySearch {
    public static void main(String[] args) {
        int[] a = new int[]{1, 1, 3, 4, 4, 6, 10, 15, 17};
//        System.out.println(binary_search(a,1));
//        System.out.println(firstEqual(a, 1));
//        System.out.println(lastEqual(a, 1));
        System.out.println(firstLarge(a, 3));

    }

    /**
     * 在不重复的数组中寻找指定元素
     *
     * @param nums
     * @param target
     * @return
     */
    public static int binary_search(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) { // 临界条件是 <=
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 寻找第一个等于给定值的元素
     *
     * @param nums
     * @param target
     * @return
     */
    public static int firstEqual(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (mid > 0 && nums[mid - 1] == target) {// mid 不是第一个
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 寻找最后一个等于给定值的元素
     *
     * @param nums
     * @param target
     * @return
     */
    public static int lastEqual(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (mid < nums.length - 1 && nums[mid + 1] == target) {// 不是数组最后一个元素 且 不是最后一个等于给定值的元素
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 查找第一个大于等于给定值的元素
     * @param nums
     * @param target
     * @return
     */
    public static int firstLarge(int[] nums, int target){
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid  = left + (right-left) / 2;
            if (nums[mid] <= target){
                left = mid+1;
            } else if (mid > 0 && nums[mid-1] > target){
                right = mid-1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个小于等于给定值的元素
     * @param nums
     * @param target
     * @return
     */
    public static int lastSmall(int[] nums, int target){
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if (nums[mid] >= target){
                right = mid - 1;
            } else if (mid < nums.length-1 && nums[mid+1]<target){ // 不是数组最后一个 且 后一个元素仍然比给定值小
                left = mid + 1 ;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
 