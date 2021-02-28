package com.rwj.Singleton;
/**
 * 多线程下的单例
 *
 * synchronized，太重了
 * DCL（Double check lock双端检测锁）
 * */
public class SingletonDemo {

    private static volatile SingletonDemo instance = null;
    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t SingletonDemo（） 构造方法");
    }
    public static SingletonDemo getInstance(){
        if(instance==null){
            synchronized (SingletonDemo.class){
                //对象可能没有完成初始化
                if (instance==null){
                    //memory=allocate();    分配对象内存空间
                    //instance(memory);     初始化对象
                    //instance = memory;    设置instance指向刚分配的内存地址，此时instance!=null
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }
    public static void main(String[] args) {
        for (int i = 0; i<10;i++){
            new Thread(()->{
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
