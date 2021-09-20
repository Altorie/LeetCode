package com.sjy.linkedlist;

import java.util.List;

public class MySingleList<T> implements MyLinkedList<T> {

    private int size = 0;
    MyNode<T> head = null;

    public MySingleList() {
    }

    @Override
    public boolean add(MyNode<T> node) {
        try{
            if (size == 0 && head == null){
                head = node;
                head.setNext(null);
            } else {
                MyNode<T> temp = head;
                while(temp.getNext()!=null){
                    temp = temp.getNext();
                }
                node.setNext(null);
                temp.setNext(node);
            }
            size++;
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean add(int index, MyNode<T> node) {
        try{
            if(index == 0){
                node.setNext(head);
                head = node;
            } else {
                MyNode<T> temp = head;
                for (int i = 0; i < index - 1; i++) {
                    temp = temp.getNext();
                }
                node.setNext(temp.getNext());
                temp.setNext(node);
            }
            size++;
            return true;
        } catch(Exception e){
            return false;
        }

    }

    @Override
    public MyNode<T> get(int index) {
        if (size<1){
            return null;
        }
        MyNode<T> result = head;
        for (int i = 0; i < index; i++) {
            result = result.getNext();
        }
        return result;
    }

    @Override
    public boolean remove(int index) {
        if (index >= size ){
            return false;
        } else if (index == 0){
            head = head.getNext();
            return true;
        } else {
            MyNode<T> temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
            }
            temp.setNext(temp.getNext().getNext());
            return true;
        }

    }
}
