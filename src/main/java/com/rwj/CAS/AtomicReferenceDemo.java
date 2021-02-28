package com.rwj.CAS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

@ToString
@Getter
@AllArgsConstructor
class User{
    String userName;
    int age;
}
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();
        User z3 = new User("z3",22);
        User l4 = new User("l4",33);
        atomicReference.set(z3);
        System.out.println(atomicReference.compareAndSet(z3,l4)+"\t"+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3,l4)+"\t"+atomicReference.get().toString());

    }
}
