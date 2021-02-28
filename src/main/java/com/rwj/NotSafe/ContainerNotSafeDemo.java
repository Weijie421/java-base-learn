package com.rwj.NotSafe;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * ArrayList：
 * 1。故障现象
 *      java.util.ConcurrentModificationException
 * 2。导致原因
 *      并发争抢修改导致
 * 3。解决方案
 *      3.1 Vector()
 *      3.2 Collections.synchronizedList()
 *      3.3 CopyOnWriteArrayList  写时复制
 * 4。优化建议
 *
 * */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        Map<String,String> map = new ConcurrentHashMap<>();

        for(int i =0;i<30;i++){
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
        //arrayListMultiThread();
    }

    private static void arrayListMultiThread() {
        List<String> list = new CopyOnWriteArrayList<>();
        for(int i =0;i<30;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(Thread.currentThread().getName()+"\t"+list);
                },String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName()+"\t"+list);
    }
}
