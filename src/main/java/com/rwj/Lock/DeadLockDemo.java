package com.rwj.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;
    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 自己持有 "+lockA+"\t尝试获得 "+lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 自己持有 "+lockB+"\t尝试获得 "+lockA);
            }
        }
    }
}
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        String lockAA = "lockA";
        String lockBB = "lockB";
        new Thread(new HoldLockThread(lockA,lockB),"AA").start();
        new Thread(new HoldLockThread(lockBB,lockAA),"BB").start();
    }
}
