package day18;

import java.util.concurrent.locks.StampedLock;

/**
 * @Author gaoqiangwei
 * @Date 2020/6/10 22:12
 * @Description
 */
public class Point {
    private int x,y;

    final StampedLock s1 = new StampedLock();

    double distanceOrigin() {
        long stamp = s1.tryOptimisticRead();
        int curx = x;
        int cury = y;
        //判断是否存在写操作，是的话返回false
        if (!s1.validate(stamp)) {
            stamp = s1.readLock();
            try {
                curx = x;
                cury = y;
            }finally {
                s1.unlockRead(stamp);
            }
        }
        return Math.sqrt(curx*curx + cury*cury);
    }
}
