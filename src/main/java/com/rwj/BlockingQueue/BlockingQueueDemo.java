package com.rwj.BlockingQueue;

import java.util.List;
import java.util.concurrent.*;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t put 1");
                synchronousQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"\t put 2");
                synchronousQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"\t put 3");
                synchronousQueue.put("3");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        },"AA").start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"\t take "+synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"\t take "+synchronousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"\t take "+synchronousQueue.take());

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        },"BB").start();
    }

    private static void BlockingQueueDemoFunc() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.offer("a",2L,TimeUnit.SECONDS);
        System.out.println(blockingQueue.toString());
        blockingQueue.offer("b",2L,TimeUnit.SECONDS);
        System.out.println(blockingQueue.toString());
        blockingQueue.offer("c",2L,TimeUnit.SECONDS);
        System.out.println(blockingQueue.toString());
        System.out.println("+++++++++++++++");
        blockingQueue.offer("d",2L,TimeUnit.SECONDS);
        System.out.println(blockingQueue.toString());
        System.out.println("===============");
        blockingQueue.take();
        System.out.println(blockingQueue.toString());
        blockingQueue.take();
        System.out.println(blockingQueue.toString());
        blockingQueue.take();
        System.out.println(blockingQueue.toString());
    }
}
