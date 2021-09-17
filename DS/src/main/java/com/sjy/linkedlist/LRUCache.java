package com.sjy.linkedlist;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class LRUCache {
    private int capacity;
    private List<Data> list;
    private Set<Integer> set = new HashSet<>();
    private AtomicInteger integer = new AtomicInteger(0);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        list = new ArrayList<>(capacity + capacity >> 1);
    }


    public Data get(int number){
        while (!integer.compareAndSet(0, 1)){
            // 每抢到锁
        }
        Data result = null;
        int position = -1;
        for (int i = 0; i < list.size(); i++) {
            // 缓存击中
            if (list.get(i).number == number){
                position = i;
            }
        }
        if (position!=-1){
            // 交换当前节点与头节点的位置
            result = list.get(position);
            list.set(position, list.get(0));
            list.set(0, result);
        } else {
            // 缓存未击中
            System.out.println("缓存未击中，从磁盘读取数据");
            result = new Data(number, 1);
            list.add(0, result);
            if (list.size() > capacity){
                // 丢弃最后一个缓存
                list.remove(capacity);
            }
        }
        // 释放自旋锁
        integer.compareAndSet(1, 0);
        return result;
    }
}


class Data{
    int number;
    Object data;

    public Data() {
    }

    public Data(int number, Object data) {
        this.number = number;
        this.data = data;
    }
}
