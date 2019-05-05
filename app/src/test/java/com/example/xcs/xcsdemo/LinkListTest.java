package com.example.xcs.xcsdemo;

import com.example.xcs.xcsdemo.datastructure.LinkList;

import org.junit.Test;

/**
 * @author RWX
 * @time 2019-04-28.
 */
public class LinkListTest {

    public LinkList createLinkList(){
        LinkList linkList = new LinkList();
        for (int i = 0; i< 10; i++){
            linkList.addNode2Last(i);
        }
        return linkList;
    }

    @Test
    public void deleteLinkListNode(){
        LinkList linkList = createLinkList();
        linkList.deleteNodeByIndex(2);
        linkList.printLinkList();
    }

    @Test
    public void insertNode(){
        LinkList linkList = createLinkList();
        linkList.insertNodeInIndex(2,55);
        linkList.printLinkList();
    }

    @Test
    public void reverseLinkList(){
        LinkList linkList = createLinkList();
        linkList.reverseLinkList();
        linkList.printLinkList();
    }

    @Test
    public void recursionRevert(){
        LinkList linkList = createLinkList();
        linkList.recrusionRevert();
        linkList.printLinkList();
    }
}
