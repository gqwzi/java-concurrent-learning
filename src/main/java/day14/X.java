package day14;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author gaoqiangwei
 * @Date 2020/6/2 23:23
 * @Description
 */
public class X {
    private final Lock lock = new ReentrantLock();
    private Integer value = 0;
    public void addOne() {
        //加锁
        lock.lock();
        try {
            value +=1;
        }finally {
            //保证能释放锁
            lock.unlock();
        }
    }
}

class XX {
    private final Lock lock = new ReentrantLock();
    private Integer value = 0;
    public int get() {
        lock.lock();    //②
        try {
            return value;
        }finally {
            lock.unlock();
        }
    }
    public void addOne() {
        //加锁
        lock.lock();
        try {
            value = get() + 1; //①
        }finally {
            //保证能释放锁
            lock.unlock();
        }
    }
}