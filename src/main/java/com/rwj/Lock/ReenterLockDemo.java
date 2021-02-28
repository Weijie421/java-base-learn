package com.rwj.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable{
    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t invoke sendSMS");
        sendEmail();
    }
    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t invoke sendEmail");
    }
    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }
    public void get(){
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoke get");
            set();
        }finally {
            lock.unlock();
            lock.unlock();
        }
    }
    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoke set");
        }finally {
            lock.unlock();
        }
    }
}
public class ReenterLockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();
        for(int i=0;i<2;i++){
            new Thread(()->{
                try {
                    phone.sendSMS();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

        Thread t3 = new Thread(phone);
        Thread t4 = new Thread(phone);
        t3.start();
        t4.start();

    }
}
