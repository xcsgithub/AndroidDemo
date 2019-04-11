package com.example.xcs.xcsdemo;

import org.junit.Test;

/**
 * @author RWX
 * @time 2018-08-01.
 */

public class LinkedStack<T> {
    private static class Node<U> {
        U item;
        Node<U> next;
        Node(){
            item = null;
            next = null;
        }
        Node(U item, Node<U> next){
            this.item = item;
            this.next = next;
        }
        boolean end(){
            return item == null && next == null;
        }
    }
    private Node<T> top = new Node<T>();
    public void push(T item){
        top = new Node<>(item,top);
    }
    public T pop(){
        T result = top.item;
        if (!top.end()){
            top = top.next;
        }
        return result;
    }
    @Test
    public void test(){
        LinkedStack<String> lss = new LinkedStack<>();
        for (String s : "phasers or stun!".split(" ")){
            lss.push(s);
        }
        String s ;
        while ((s = lss.pop()) != null){
            System.out.print(s);
        }
    }
}
