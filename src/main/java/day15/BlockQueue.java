package day15;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author gaoqiangwei
 * @Date 2020/6/3 23:02
 * @Description
 */
public class BlockQueue<T> {

    final Lock lock = new ReentrantLock();
    // 条件变量:队列不满
    Condition notFull = lock.newCondition();
    // 条件变量:队列不空
    Condition notEmpty = lock.newCondition();

    //入队
    void enq(T x) {
        lock.lock();
        try {
            //while(队列不满) {
            //    notFull.await();
            //}
            // 省略入队操作...
            // 入队后, 通知可出队
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }
    //出队
    void deq(T x) {
        lock.lock();
        try {
            //while(队列不空) {
            //    notEmpty.await();
            //}
            // 省略出队操作...
            // 出队后，通知可入队
            notFull.signal();
        }finally {
            lock.unlock();
        }
    }
}
