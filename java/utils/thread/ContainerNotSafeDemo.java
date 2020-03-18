package utils.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author zhangbaosheng
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {
        List<String> list =new CopyOnWriteArrayList<>();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        /**
         *
         1 故障现象:
                  java.util.ConcurrentModificationException  并发修改异常
         2 导致原因:
                并发争抢修改导致
         3 解决方案:
             3.1 new Vector<>()      (但要牺牲并发为代价  java 1.0 )
             3.2 Collections.synchronizedList(new ArrayList<>());
             3.3 new CopyOnWriteArrayList<>()
         4 优化建议(同样问题不犯第二次):
         *
         */
    }

}
