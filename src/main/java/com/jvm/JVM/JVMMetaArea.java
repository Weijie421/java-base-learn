package com.jvm.JVM;

public class JVMMetaArea {
    public static void main(String[] args) {
        System.out.println("电脑 CPU "+Runtime.getRuntime().availableProcessors());
        long maxMemory = Runtime.getRuntime().maxMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("-Xmx: Max_memory = "+maxMemory+"(字节)、"+(maxMemory/(double)1024/1024)+" MB");
        System.out.println("-Xms: Total_memory = "+totalMemory+"(字节)、"+(totalMemory/(double)1024/1024)+" MB");

    }
}
