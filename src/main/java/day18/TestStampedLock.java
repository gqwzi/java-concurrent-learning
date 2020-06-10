package day18;

import java.util.concurrent.locks.StampedLock;

/**
 * @Author gaoqiangwei
 * @Date 2020/6/10 22:04
 * @Description
 */
public class TestStampedLock {
    final StampedLock stampedLock = new StampedLock();

    void testRead() {
        //获取悲观读锁
        long stamp = stampedLock.readLock();
        try {
            //xxx
        }finally {
            stampedLock.unlockRead(stamp);
        }
    }

    void testWrite() {
        //获取写锁
        long stamp = stampedLock.writeLock();
        try {
            //xxx
        }finally {
            stampedLock.unlockWrite(stamp);
        }
    }

}
