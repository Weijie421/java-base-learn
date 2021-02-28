package com.rwj.Lock;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public Object get(String key){
        lock.readLock().lock();
        Object res = null;
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在读 "+key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            res = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读 "+key+" 完成");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }
        return res;
    }
    public void put(String key, Object value){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在写入 "+key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入 "+key+" 完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for(int i = 0;i<5;i++){
            final int tmp = i;
            new Thread(()->{
                myCache.put(tmp+"",tmp+"");
            },String.valueOf(i)).start();
        }

        for(int i = 0;i<5;i++){
            final int tmp = i;
            new Thread(()->{
                myCache.get(tmp+"");
            },String.valueOf(i)).start();
        }
    }
}
