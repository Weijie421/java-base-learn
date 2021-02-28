package com.jvm.JVM;

import com.jvm.classLoader.String;

public class MyObject {
    public static void main(String[] args) {
        //自带的走bootstrap加载器
        String object = new String();
        System.out.println(object.getClass().getClassLoader());
        //自定义的走app加载器
        MyObject myObject = new MyObject();
        System.out.println(myObject.getClass().getClassLoader());
        System.out.println(myObject.getClass().getClassLoader().getParent());
        System.out.println(myObject.getClass().getClassLoader().getParent().getParent());
    }
}
