package com.sjy.base;

import java.util.Arrays;

public class MinStack {
    private int[] arr = new int[32];
    private int size = 0;
    private int min = Integer.MAX_VALUE;
    public MinStack() {

    }

    /**
     * 每一次添加元素，先入栈算上val最小的元素值，在入栈val
     * @param val
     */
    public void push(int val) {
        if (size * 2 == arr.length){
            // 扩容
            int[] temp = new int[arr.length << 1];
            System.arraycopy(arr, 0, temp, 0, arr.length);
            arr = temp;
        }
        min = Math.min(min, val);
        arr[size*2] = min;
        arr[size*2+1] = val;
        size++;
    }

    public void pop() {
        size--;
        if (size == 0){
            min = Integer.MAX_VALUE;
        }else {
            min = arr[2*size-2];
        }
    }

    public int top() {
        return arr[2*size-1];
    }

    public int getMin() {
        return min;
    }
}
