package day06;

import java.util.List;

/**
 * @Author gaoqiangwei
 * @Date 2020/5/30 00:00
 * @Description
 */
public class Allocator {
    private List<Object> als;
    //一次性申请所有资源
    synchronized boolean apply(Object from,Object to)throws Exception {
        //经典写法
        while (als.contains(from) || als.contains(to)) {
            wait();
        }
        als.add(from);
        als.add(to);
        return true;
    }
    //归还资源
    synchronized void free(Object from,Object to) {
        als.remove(from);
        als.remove(to);
        notifyAll();
    }
}
