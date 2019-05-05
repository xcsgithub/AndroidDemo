package com.example.xcs.xcsdemo.thread;

/**
 * sleep 和 wait 方法的区别
 * @author RWX
 * @time 2019-04-30.
 */
public class ThreadBlockTest implements Runnable {

    int number = 10;

    public void add(){
        synchronized (this){
            number += 100;
            System.out.print(number);
        }
    }

    public void block() throws Exception{
        synchronized (this){
//            Thread.sleep(10000);
            this.wait(10000);
            number *= 200;
        }
    }

    @Override
    public void run() {
        add();
    }

    public static void main(String[] args) throws Exception {
        ThreadBlockTest test = new ThreadBlockTest();
        Thread thread = new Thread(test);
        thread.start();
        test.block();
    }
}
