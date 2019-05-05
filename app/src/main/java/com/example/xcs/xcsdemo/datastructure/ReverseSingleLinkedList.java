package com.example.xcs.xcsdemo.datastructure;

/**
 * @author RWX
 * @time 2019-04-23.
 */
public class ReverseSingleLinkedList {
     public static void main(String[] args){
         LinkList linkList = new LinkList();
         for (int i = 0; i< 10; i++){
             linkList.addNode2Last(i);
         }
         linkList.printLinkList();
     }
}
