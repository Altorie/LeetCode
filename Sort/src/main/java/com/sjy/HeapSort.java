package com.sjy;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        HeapSort sort = new HeapSort();
        int[] nums = new int[]{1,3,8,4,1,3,6,15,7,3,6,3,1,3,5,8};
        sort.heap_sort(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }

    public void heap_sort(int[] nums){
        int len = nums.length;
        // 首先构建大顶堆，从第一个非叶子节点开始
        for (int i = len/2 - 1; i >= 0 ; i--) {
            adjust_node(nums, i, len);
        }
        // 调换堆顶
        for (int i = 0; i < len-1; i++) {
            int temp = nums[0];
            nums[0] = nums[len-1-i];
            nums[len-1-i] = temp;
            // 调整第 0 个元素
            adjust_node(nums, 0, len-1-i);
        }

    }

    private void adjust_node(int[] nums, int i, int len){
        int leftChild = 2*i+1;
        int rightChild = 2*i + 2;
        // 记录最大值的位置
        int maxIndex = i;
        // 如果存在左节点且左节点值大于当前最大值，更新最大值的索引maxIndex
        if (leftChild < len && nums[leftChild] > nums[maxIndex]){
            maxIndex = leftChild;
        }
        // 如果存在左节点且左节点值大于当前最大值，更新最大值的索引maxIndex
        if (rightChild < len && nums[rightChild] > nums[maxIndex]){
            maxIndex = rightChild;
        }
        // 交换父节点和最大值的节点
        if (maxIndex != i){
            int temp = nums[maxIndex];
            nums[maxIndex] = nums[i];
            nums[i] = temp;
            // 交换之后，maxIndex位置的值可能不符合大顶堆要求
            adjust_node(nums, maxIndex, len);
        }
    }



}
