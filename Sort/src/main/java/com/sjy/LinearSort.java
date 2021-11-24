package com.sjy;

import javax.management.ValueExp;

/**
 * 线性排序
 */
public class LinearSort {
    public static void main(String[] args) {

    }

    /**
     * 计数排序
     *  当数据范围不大的时候（例如范围为 K），可以将数据划分成 K个桶，每个桶内的数据都是相同的。
     *  是 桶排序 的一种特例
     * @param nums
     * @return
     */
    public static int[] countingSort(int[] nums){
        // 找到数组大最大值和最小值
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        // 获取数据的范围
        int k = max-min+1;
        // a[i]保存 min+i 这一元素在nums数组中出现次数
        int[] a = new int[k];
        for (int i = 0; i < nums.length; i++) {
            a[nums[i] - min]++;
        }
        // 对数组a累计求和。此时啊 a[i] 保存 小于等于 min+i的元素出现的次数
        int temp = 0;
        for (int i = 0; i < k; i++) {
            a[i] += temp;
            temp = a[i];
        }
        int[] result = new int[nums.length];
        /**
         * 反向遍历原数组。(反向遍历的目的是为了是排序稳定)
         *      对遍历到的每个元素值value，查找a[value-min]，代表此时 <= value的元素个数为 a[value-min]个。
         *      那么，value在有序数组中的索引为 a[value-min]-1.
         *      对 a[value]执行减 1 操作。确保下一个值为 value的元素在有序数组中的位置仍可用 a[value-min]-1 表示
         */
        for (int i = nums.length - 1; i >= 0; i++) {
            int value = nums[i];
            int index = a[value-min]-1;
            result[index] = value;
            a[value-min] -= 1;
        }
        return result;
    }

    /**
     * 基数排序
     * 基数排序对处理的数据有要求：数据必须能够分割成 “位”，且位与位之间有递进关系。如果 a的高位比 b的高位 数字大，那么 a 就应该大于b ，而不需要再去比较剩下的位
     * 如 11位手机号，字符串等（对于字符串长度不等的问题，可以在后面补 0）。
     *
     * 基本思路为，从低位开始，对数据进行计数排序，一直到最高位。
     * @param nums
     * @return
     */
    public static int[] radixSort(int[] nums){
        return null;
    }
}
