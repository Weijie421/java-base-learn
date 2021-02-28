package com.rwj.ProdConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SharedData{
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increase() throws Exception {
        lock.lock();
        try {
            //判断
            while (num!=0){
                condition.await();
            }
            //干活
            num++;
            System.out.println(Thread.currentThread().getName()+"\t"+num);
            //通知
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void decrease() throws Exception{
        lock.lock();
        try {
            //判断
            while (num==0){
                condition.await();
            }
            //干活
            num--;
            System.out.println(Thread.currentThread().getName()+"\t"+num);
            //通知
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

public class ProdConsumerV0 {
    public static void main(String[] args) {
        SharedData sharedData = new SharedData();
        new Thread(()->{
            for(int i = 0;i<5;i++) {
                try {
                    sharedData.increase();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();
        new Thread(()->{
            for(int i = 0;i<5;i++) {
                try {
                    sharedData.decrease();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
        new Thread(()->{
            for(int i = 0;i<5;i++) {
                try {
                    sharedData.increase();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();
        new Thread(()->{
            for(int i = 0;i<5;i++) {
                try {
                    sharedData.decrease();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();
    }

}
