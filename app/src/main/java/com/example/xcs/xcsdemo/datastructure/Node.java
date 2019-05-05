package com.example.xcs.xcsdemo.datastructure;

/**
 * @author RWX
 * @time 2019-04-23.
 */
public class Node {
    public int record;

    public Node nextNode;

    public Node(int record){
        super();
        this.record = record;
    }

    public int getRecord(){
        return record;
    }

    public void setRecord(int record){
        this.record = record;
    }

    public Node getNextNode(){
        return nextNode;
    }

    public void setNextNode(Node nextNode){
        this.nextNode = nextNode;
    }
}
