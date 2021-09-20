package com.sjy.linkedlist;

public class MyNode<T> {
    private MyNode<T> pre;
    private T data;
    private MyNode<T> next;

    public MyNode() {
    }

    public MyNode(MyNode<T> pre, T data, MyNode<T> next) {
        this.pre = pre;
        this.data = data;
        this.next = next;
    }

    public MyNode<T> getPre() {
        return pre;
    }

    public void setPre(MyNode<T> pre) {
        this.pre = pre;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public MyNode<T> getNext() {
        return next;
    }

    public void setNext(MyNode<T> next) {
        this.next = next;
    }
}
