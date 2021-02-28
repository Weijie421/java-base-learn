package com.rwj.ProdConsumer;

import java.sql.Time;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
    //默认开启，进行生产+消费
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }
    public void product() throws InterruptedException {
        String data = null;
        boolean retValue;
        while (FLAG){
            data=atomicInteger.incrementAndGet()+"";
            retValue = blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName()+"\t 插入队列 "+data+" 成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t 插入队列 "+data+" 失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t 停了停了，生产结束");
    }
    public void consumer() throws InterruptedException {
        String res = null;
        while (FLAG){
            res = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if(null==res||res.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t 超过两秒没消费到，消费退出");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费队列 "+res+" 成功");
        }
    }
    public void stop() throws Exception{
        this.FLAG = false;
    }
}

public class ProdConsumerV1 {
    public static void main(String[] args) throws Exception {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t生产线程启动");
            try {
                myResource.product();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Prod").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t消费线程启动");
            try {
                myResource.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Cons").start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("老板叫停，结束了");
        myResource.stop();
    }
}
