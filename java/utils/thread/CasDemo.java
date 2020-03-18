package utils.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangbaosheng
 * cas :  compare and set  //
 * 比较 并 交换
 *
 */
public class CasDemo {

    public static void main(String[] args) {

        //主内存 原始值
        AtomicInteger atomicInteger = new AtomicInteger(5);

        // 读取主内存原始值  5  然后进行比较  相等  则 设置新的 值 2020
        System.out.println(atomicInteger.compareAndSet(5, 2020)+"---"+atomicInteger.get());

        // 这里 上一次主内存修改成了 2020  进行比较 肯定不相等  所以 从新读取主内存的值
        System.out.println(atomicInteger.compareAndSet(5, 1024)+"---"+atomicInteger.get());

        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.getAndIncrement());
    }



}
