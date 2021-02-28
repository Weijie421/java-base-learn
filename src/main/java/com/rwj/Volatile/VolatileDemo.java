package com.rwj.Volatile;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData{
    volatile int num = 0;
    public void addTO60(){
        this.num=60;
    }
    public void add(){
        num++;
    }
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}
/**
 * 1.验证volatile可见性
 * 1.1 假设 int number =0; 没添加volatile, 其他线程根本不知道，所以会一直hung住
 * 2.2 添加volatile后，其他线程会迅速同步，解决可见性问题
 *
 * 2。验证volatile不保证原子性，非线程安全，会出现写覆盖
 * 解决原子性：
 *      synchronized 某一时段只能有一个线程
 *      AtomicInteger
 *
 * 3. 禁止指令重排
* */
public class VolatileDemo {
    public static void main(String[] args) {
        volatileDemoNoAutomic();
    }

    private static void volatileDemoNoAutomic() {
        MyData myData = new MyData();
        for(int i = 0; i < 2000; i++){
            new Thread(()->{
                for(int j =0;j<1000;j++){
                    myData.add();
                    myData.addAtomic();
                }
            },String.valueOf(i)).start();
        }
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t finially int num is "+myData.num);
        System.out.println(Thread.currentThread().getName()+"\t finially atomic num is "+myData.atomicInteger);
    }

    //可以保证可见性，及时通知其他线程
    private static void volatileDemoExtracted() {
        MyData myData = new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
                myData.addTO60();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"\t current num is "+myData.num);
        },"AAA").start();
        while (myData.num==0){

        }
        System.out.println(Thread.currentThread().getName()+"\t mission is over, main get value "+myData.num);
    }
}
