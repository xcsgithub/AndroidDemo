package com.example.xcs.xcsdemo.datastructure;

/**
 * @author RWX
 * @time 2019-04-26.
 */
public class LinkList {
    public Node head;
    private int size = 0;

    public LinkList() {
        this.head = null;
    }

    public void printLinkList(){
        if (head == null){
            System.out.print("鏈表爲空");
            return;
        }
        System.out.print(head.getRecord());
        Node node = head.getNextNode();
       while (node != null){
           if (node != null) {
               System.out.print(node.getRecord());
           }
           node = node.getNextNode();
       }
    }

    public void addNode2Last(int value){
        Node node = new Node(value);
        if (head == null){
            head = node;
            size++;
            return;
        }
        Node currentNode = head.nextNode;
        if (currentNode == null){
            head.nextNode = node;
            size ++;
            return;
        }
        while (currentNode.nextNode != null){
            currentNode = currentNode.getNextNode();
        }
        currentNode.nextNode = node;
        node.nextNode = null;
        size ++;
    }

    public Node deleteHeadNode() {
        Node tempNode = head;
        head = head.getNextNode();
        size--;
        return tempNode;
    }

    public void deleteNodeByIndex(int index) {
        Node preNode = head;
        Node curNode = head;

        if (index < 0 || index > size - 1) {
            System.out.print("下標越界");
            return;
        }
        int position = 0;
        while (position != index) {
            preNode = curNode;
            curNode = curNode.getNextNode();
            position++;
        }
        preNode.nextNode = curNode.nextNode;
    }

    public void insertNodeInIndex(int index,int value){
        Node node = new Node(value);
        if (head == null){
            System.out.print("链表为空，不可插入");
            return;
        }
        Node preNode = head;
        Node currentNode = head;
        int position = 0;
        while (position != index){
            preNode = currentNode;
            currentNode = currentNode.getNextNode();
            position ++;
        }
        preNode.nextNode = node;
        node.nextNode = currentNode;
    }

    public void reverseLinkList(){
        if (head == null || head.getNextNode() == null){
            System.out.print("链表为空或链表元素为1，不可反转");
            return ;
        }

        Node node = head;
        Node next = null;
        Node pre = null;
        while (node != null){
            next = node.nextNode;
            node.nextNode = pre;

            pre = node;
            node = next;
        }
        head = pre;
    }

    public void recrusionRevert(){
        head = revert(head);
    }

    private Node revert(Node head) {
        if (head == null || head.nextNode == null) {
            // 到达尾结点
            return head;
        }
        // 一直入栈
        Node revertHead = revert(head.nextNode);
        // 出栈并赋值nextNode对象
        head.nextNode.nextNode = head;
        head.nextNode = null;
        // 返回尾结点（逆置后的头结点）
        return revertHead;
    }

    public Node mergeTwoLinkList(Node nodeFirst,Node nodeSec){
        if (nodeFirst == null) return nodeSec;
        if (nodeSec == null) return nodeFirst;

        Node head = null;
        if (nodeFirst.record <= nodeSec.record){
            head = nodeFirst;
            head.nextNode = mergeTwoLinkList(nodeFirst.nextNode,nodeSec);
        }else {
            head = nodeSec;
            head.nextNode = mergeTwoLinkList(nodeFirst,nodeSec.nextNode);
        }
        return head;
    }
}
