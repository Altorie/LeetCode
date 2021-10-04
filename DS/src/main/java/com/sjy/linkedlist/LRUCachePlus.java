package com.sjy.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于链表和哈希表实现单线程环境下的LRU缓存
 */
public class LRUCachePlus<T> {
    private int capacity; // 缓存的容量
    private int size; // 实际存储的数据数量
    private Node<T> head; // 保存最后被使用的缓存数据
    private Map<Integer, Node<T>> map; // 用来快速索引到缓存位置

    public LRUCachePlus(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>((int)(capacity / 0.75));
    }

    /**
     * 获取数据
     * @param address
     * @return
     */
    public T get(int address){
        // 首先查询是否存在该数据
        Node<T> target = map.get(address);
        if (target == null){ // 数据不存在
            Node<T> somehow = new Node<>(address, getDataFromMemory(address));
            if (size == capacity){ // 如果缓存容量已满
                // 获取尾部元素，这是要删除的
                Node<T> tail = head.pre;
                // 将新节点的后继指向头
                somehow.next = head;
                // 将新节点的前驱 指向 尾的前驱
                somehow.pre = tail.pre;
                // 将头节点的前驱指向新节点
                head.pre = somehow;
                // 将尾节点的前驱的后继指向新节点
                tail.pre.next = somehow;
                // 将头指针赋给新节点
                head = somehow;
            } else { // 缓存未满，直接把新节点插在头部
                Node<T> tail = head.pre;
                tail.next = somehow;
                somehow.next = head;
                head.pre = somehow;
                somehow.pre = tail;
                head = somehow;
                size++;
            }
            return getDataFromMemory(address);
        } else { // 缓存命中，把 target调整到头部
            target.pre.next = target.next;
            target.next.pre = target.pre;

            Node<T> tail = head.pre;
            tail.next = target;
            target.next = head;
            head.pre = target;
            target.pre = tail;
            head = target;
            return target.data;
        }
    }

    /**
     * 从内存中查询数据
     * @param address
     * @return
     */
    private T getDataFromMemory(int address){
        return null;
    }
}

class Node<T>{
    int address; // 模拟数据的地址
    T data; // 这块地址上的数据
    Node<T> pre;
    Node<T> next;

    public Node(int address, T data) {
        this.address = address;
        this.data = data;
    }
}
