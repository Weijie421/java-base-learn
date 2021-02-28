package com.jvm.JVM;

public class NativeInterface {
    public static void main(String[] args) {
        Thread t1 = new Thread();
        t1.start();
        //调用两次以上，调用的是start0() ---> native修饰的方法
        //java.lang.IllegalThreadStateException
        //t1.start();
    }
}
