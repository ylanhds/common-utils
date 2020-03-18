package utils.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zhangbaosheng CAS   ABA问题
 */
public class ABADemo {

    public static void main(String[] args) {
        AtomicReference<String> atomicReference = new AtomicReference<>("A");

        // ABA问题的产生
        new Thread(() -> {
            atomicReference.compareAndSet("A", "B");
            atomicReference.compareAndSet("B", "A");
            System.out.println("t1线程 查看内存值--->" + atomicReference.get());
        }, "t1").start();

        new Thread(() -> {
            try {
                //保证t1线程已经执行完毕
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicReference.compareAndSet("A", "C");
            System.out.println("t2线程看内存值--->" + atomicReference.get());
        }, "t2").start();

        // 解决ABA问题

    }
}
