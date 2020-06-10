package day18;

import java.util.concurrent.locks.StampedLock;

/**
 * @Author gaoqiangwei
 * @Date 2020/6/10 22:54
 * @Description
 */
public class TestAfterClass {
    private double x, y;
    final StampedLock sl = new StampedLock();
    void moveIfAtOrigin(double newX, double newY) {
        long stamp = sl.readLock();
        try {
            while(x == 0.0 && y == 0.0) {
                long ws = sl.tryConvertToWriteLock(stamp);
                if (ws != 0L) {
                    x = newX;
                    y = newY;
                    break;
                }else {
                    sl.unlockRead(stamp);
                    stamp = sl.writeLock();
                }
            }
        }finally {
            sl.unlock(stamp);
        }
    }
}
