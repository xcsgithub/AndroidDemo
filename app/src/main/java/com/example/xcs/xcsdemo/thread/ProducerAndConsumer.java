package com.example.xcs.xcsdemo.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author RWX
 * @time 2019-04-30.
 */
public class ProducerAndConsumer {

    public static void main(String[] args){
        System.out.println("How to use wait and notify method in Java");
        System.out.println("Solving Producer Consumper Problem");
        Queue<Integer> buffer = new LinkedList<>();
        int maxSize = 10;
        Thread producer = new Producer(buffer,maxSize,"Producer");
        Thread consumer = new Consumer(buffer,maxSize,"Consumer");
        producer.start();
        consumer.start();

    }

     static class Producer extends Thread{
        private Queue<Integer> queue;
        private int maxSize;
        public Producer(Queue<Integer> queue,int maxSize,String name){
            super(name);
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true){
                synchronized (queue){
                    while (queue.size() == maxSize) {
                        System.out.println("queue is full," + "producer thread is waiting consumer consume");
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Random random = new Random();
                    int i = random.nextInt();
                    System.out.println("Producding value :" + i);
                    queue.add(i);
                    queue.notifyAll();
                }
            }
        }
    }

    static class Consumer extends Thread{
        private Queue<Integer> queue;
        private int maxSize;
        public Consumer(Queue<Integer> queue, int maxSize, String name){
            super(name);
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true){
                synchronized (queue){
                    while (queue.isEmpty()){
                        System.out.println("Queue is empty, Consumer thread is wait Producer produce");
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Comsuming value :" + queue.remove());
                    queue.notifyAll();
                }
            }
        }
    }
}
