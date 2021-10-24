package com.sjy.heap;

/**
 * 大顶堆
 */
public class MyHeap {
    private int[] arr; // 存储堆数据，从下标 1 开始
    private int capacity; // 堆的容量
    private int count;

    public MyHeap(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity+1];
        count = 0;
    }

    public void insert(int data){
        if (count == capacity) return;
        count++;
        arr[count] = data;
        int i = count;
        while (i/2 >0 && arr[i] > arr[i/2]){
            int temp = arr[i];
            arr[i] = arr[i/2];
            arr[i/2] = temp;
            i /= 2;
        }
    }

    public void removeMax(){
        if (count == 0) return;
        arr[1] = arr[count];
        // 调整堆结构
        int i = 1;
        while(true){
            int left = 2*i, right = 2*i+1;
            int maxIndex = i;
            if (left <= count && arr[left] > arr[maxIndex]) maxIndex = left;
            if (right <= count && arr[right] > arr[maxIndex]) maxIndex = right;
            if (maxIndex == i){ // 说明调整结束
                break;
            }
            // 将 i 和 maxIndex位置的元素调换位置
            int temp = arr[i];
            arr[i] = arr[maxIndex];
            arr[maxIndex] = temp;
            // 调换之后不能保证 maxIndex位置的元素比他的子节点都大，因此要再次检验
            i = maxIndex;
        }
    }


}
