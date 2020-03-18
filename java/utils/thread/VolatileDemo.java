package utils.thread;

/**
 * @author zhangbaosheng
 * 单例 DCL   volatile分析
 * 一 volatile 关键字
 * 二 synchronized
 */
public class VolatileDemo {
    public static volatile VolatileDemo volatileDemo =null;
    private VolatileDemo(){
        System.out.println(Thread.currentThread().getName()+"   Test构造方法   ");
    }
    public static VolatileDemo getInstance(){
        //双端检索DCL
        synchronized (VolatileDemo.class){
            if (volatileDemo ==null){
                volatileDemo = new VolatileDemo();
            }
        }
        return volatileDemo;
    }
    public static void main(String[] args) {
        //单线程(无所谓)
//        System.out.println(String.valueOf(VolatileDemo.getInstance()==VolatileDemo.getInstance()));
//        System.out.println(String.valueOf(VolatileDemo.getInstance()==VolatileDemo.getInstance()));
//        System.out.println(String.valueOf(VolatileDemo.getInstance()==VolatileDemo.getInstance()));
        //多线程(影响安全)
        for (int i = 0; i <10 ; i++) {
            new Thread(()->{
                VolatileDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}


