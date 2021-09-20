package com.sjy.linkedlist;

public interface MyLinkedList<T> {

    /**
     * 在尾部插入
     * @param node
     * @return
     */
    boolean add(MyNode<T> node);

    /**
     * 在指定位置处插入
     * @param index
     * @param node
     * @return
     */
    boolean add(int index, MyNode<T> node);

    /**
     * 获取指定位置的元素
     * @param index
     * @return
     */
    MyNode<T> get(int index);

    /**
     * 删除元素
     * @param index
     * @return
     */
    boolean remove(int index);

}
