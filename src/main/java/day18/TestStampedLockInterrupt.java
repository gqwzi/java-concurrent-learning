package day18;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * @Author gaoqiangwei
 * @Date 2020/6/10 22:33
 * @Description
 */
public class TestStampedLockInterrupt {
    public static void main(String[] args)throws Exception {

    final StampedLock lock = new StampedLock();

    Thread t1 = new Thread(() -> {
        lock.writeLock();
        //永远阻塞在此处，不释放写锁
        LockSupport.park();
    });
    t1.start();
    //保证T1先获取写锁
    Thread.sleep(100);

    Thread t2 = new Thread(() -> {
        //阻塞在悲观读锁
        long stamp = lock.readLock();
    });
    t2.start();
    //保证t2阻塞在读锁
    Thread.sleep(100);
    t2.interrupt();
    t2.join();
    }
}
