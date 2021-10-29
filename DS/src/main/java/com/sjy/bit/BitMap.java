package com.sjy.bit;

/**
 * 位图
 * 通过一个二进制位来表示数据是否存在
 * 可以排序、去重、判断数据是否存在
 */
public class BitMap {
    private byte[] bytes;
    private int capacity; // 存储容量

    public BitMap(int capacity) {
        this.capacity = capacity;
        bytes = new byte[(capacity >> 3) + 1];
    }

    /**
     * 向位图中插入数据 num
     * @param num
     */
    private void insert(int num){
        // 首先计算 num 在 bytes的第几个 byte里
        int index1 = num / 8;
        // 在计算 num 储存在这个 byte 的第几个 bit 上
        int index2 = num % 8 ;
        bytes[index1] &= 1 << index2;
    }

    /**
     * 判断位图中是否存在数据 num
     * @param num
     */
    private boolean isExist(int num){
        int index1 = num / 8;
        int index2 = num % 8 ;
        if ((bytes[index1] & (1 << index2))!=0){
            return true;
        }
        return false;
    }

    /**
     * 删除位图中的一个数据
     * @param num
     */
    private void remove(int num){

    }
}
