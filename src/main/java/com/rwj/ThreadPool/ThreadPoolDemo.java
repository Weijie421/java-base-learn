package com.rwj.ThreadPool;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) {

        ExecutorService threadPool =
                new ThreadPoolExecutor(2,
                        5,
                        1L,
                        TimeUnit.SECONDS,
                        new LinkedBlockingQueue<Runnable>(3),
                        Executors.defaultThreadFactory(),
                        new ThreadPoolExecutor.DiscardPolicy());
        try {
            for (int i = 0; i < 12; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
//                TimeUnit.MILLISECONDS.sleep(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }

    }

    private static void threadPoolJDK() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
//                TimeUnit.MILLISECONDS.sleep(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
