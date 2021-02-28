package com.rwj.Lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
           System.out.println("-----召唤神龙");
        });

        for (int i = 0; i < 7; i++) {
            final int tmp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t收集到第 "+tmp+" 龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
