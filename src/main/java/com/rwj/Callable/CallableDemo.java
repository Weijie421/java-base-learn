package com.rwj.Callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThreadCallable implements Callable{
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+" Come in... callable");
        TimeUnit.SECONDS.sleep(2);
        return 1024;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThreadCallable myThreadCallable = new MyThreadCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(myThreadCallable);
        new Thread(futureTask,"AA").start();
        new Thread(futureTask,"BB").start();
        int res0 = 100;
        while (!futureTask.isDone()){}
        int res1 = futureTask.get();
        System.out.println("res "+res0+" "+res1);
    }
}
