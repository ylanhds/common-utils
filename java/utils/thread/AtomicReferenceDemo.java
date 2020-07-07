package utils.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zhangbaosheng
 */
class User{
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User zs=new User("z3",22);
        User lisi=new User("lisi",309);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(zs);
        System.out.println(atomicReference.compareAndSet(zs, lisi)+"===="+atomicReference.toString());
        System.out.println(atomicReference.compareAndSet(zs, lisi)+"===="+atomicReference.toString());
    }
}
