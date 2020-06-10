package day18;

import java.util.concurrent.locks.StampedLock;

/**
 * @Author gaoqiangwei
 * @Date 2020/6/10 22:45
 * @Description
 */
public class StampedLockReadTemplate {
    public static void main(String[] args) {
        final StampedLock s1 = new StampedLock();
        //先获取乐观锁
        long stamp = s1.tryOptimisticRead();
        if (!s1.validate(stamp)) {
            //升级为悲观读锁
            s1.readLock();
            try {
                //xxx
            }finally {
                s1.unlockRead(stamp);
            }
        }
        //使用局部变量操作业务
    }
}

class StampedWriteTemplate{
    public static void main(String[] args) {
        final StampedLock s1 = new StampedLock();
        long stamp = s1.writeLock();
        try {
            //写共享变量
            //……
        }finally {
            s1.unlockWrite(stamp);
        }
    }
}







