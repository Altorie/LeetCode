package com.sjy.base;

import java.util.*;

/**
 *                             _ooOoo_
 *                            o8888888o
 *                            88" . "88
 *                            (| -_- |)
 *                            O\  =  /O
 *                         ____/`---'\____
 *                       .'  \\|     |//  `.
 *                      /  \\|||  :  |||//  \
 *                     /  _||||| -:- |||||-  \
 *                     |   | \\\  -  /// |   |
 *                     | \_|  ''\---/''  |   |
 *                     \  .-\__  `-`  ___/-. /
 *                   ___`. .'  /--.--\  `. . __
 *                ."" '<  `.___\_<|>_/___.'  >'"".
 *               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *               \  \ `-.   \_ __\ /__ _/   .-` /  /
 *          ======`-.____`-.___\_____/___.-`____.-'======
 *                             `=---='
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *                     佛祖保佑        永无BUG
 * @AUTHOR zuo-zhenjun
 * @TIME 2021/11/6 14:21
 * @DESCRIPTION 
 **/
public class Day20 {

    public static void main(String[] args) {
        Day20 d = new Day20();
        d.findKthLargest(new int[]{3,2,1,5,6,4}, 2);
    }
    /**
     * 215. 数组中的第K个最大元素
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        /**
         * 1 利用优先级队列（堆）
         */
//        Queue<Integer> queue = new PriorityQueue<>(k);
//        for (int num : nums){
//            if (queue.size()<k){
//                queue.offer(num);
//            }else if (queue.size() == k && num > queue.peek()) {
//                queue.poll();
//                queue.offer(num);
//            }
//        }
//        return queue.peek();
        /**
         * 2 利用快速排序的思想
         */
        return byQuickSort(nums, 0, nums.length-1, k);
    }
    private int byQuickSort(int[] nums, int start, int end, int k){
        if (start >= end){
            return nums[start];
        }
        int l = start;
        int r = end;
        int base = nums[start];
        int i = l;
        while (i <= r){
            while (i <= r && nums[i] < base){
                int temp = nums[i];
                nums[i] = nums[r];
                nums[r] = temp;
                r--;
            }
            if (nums[i] > base){
                int temp = nums[i];
                nums[i] = nums[l];
                nums[l] = temp;
                l++;
            }
            i++;
        }
        if (k-1 < l){
            return byQuickSort(nums, start, l-1, k);
        } else if (k-1 > r){
            return byQuickSort(nums, r+1, end, k);
        }else {
            return base;
        }
    }


    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num:nums){
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        // 定义一个比较器
        Queue<Integer> queue = new PriorityQueue<>(k, Comparator.comparingInt(map::get));
        for (int num : map.keySet()) {
            if (queue.size() < k){
                queue.add(num);
            } else if (map.get(num) > map.get(queue.peek())){
                queue.poll();
                queue.offer(num);
            }
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = queue.poll();
        }
        return ans;
    }
}
