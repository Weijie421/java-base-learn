package com.rwj.CAS;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5,2021)+"\t current data "+atomicInteger);
        System.out.println(atomicInteger.compareAndSet(5,2022)+"\t current data "+atomicInteger);
    }
}
