# 18 | StampedLock:有没有比读写锁更快的锁?

# 背景
读写锁使用与读多写少，允许多个线程同时读，那么有没有比读写锁更快的锁呢?还真有~~~

# StampedLock 支持的三种锁模式
StampedLock 和 ReadWriteLock 有哪些区别？      
- ReadWriteLock 支持两种模式:一种是读锁，一种是写锁。
- 而 StampedLock 支持三种模式，分别 是:写锁、悲观读锁、乐观读     
解释：写锁和悲观读锁与readWriteLock的写锁和读锁语义相似，允许多个线程同时获取悲观读锁，悲观读锁和写锁互斥。
不同的是：stampedLock的写锁和悲观读锁加锁后会返回一个stamp，解锁的时候需要传入stamp；

```text
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
```

StampedLock 的性能之所以比 ReadWriteLock 还要好，其关键是 StampedLock 支持乐观读的方 式。       
- ReadWriteLock 支持多个线程同时读，但是当多个线程同时读的时候，所有的写操作会被阻 塞;
- 而 StampedLock 提供的乐观读，是允许一个线程获取写锁的，也就是说不是所有的写操作 都被阻塞。
> 注意这里，我们用的是“乐观读”这个词，而不是“乐观读锁”，是要提醒你，【乐观读这个操作 是无锁的】，所以相比较 ReadWriteLock 的读锁，乐观读的性能更好一些。

```java
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
```
解释：代码出自JAVA SDK示例，略做修改！
- 首先，通过调用 tryOptimisticRead() 获取了一个 stamp，这里的 tryOptimisticRead() 就是我们 前面提到的乐观读。
- 之后，将共享变量 x 和 y 读入方法的局部变量中，不过需要注意的是，由于 tryOptimisticRead() 是无锁的，所以共享变量 x 和 y 读入方法局部变量时，x 和 y 有可能被其他 线程修改了。
- 最后，因此还需要再次验证一下是否存在写操作，这个验证操作是通过调 用 validate(stamp) 来实现的。

补充：     
在上面这个代码示例中，如果执行乐观读操作的期间，存在写操作，会把乐观读升级为悲观读 锁。
这个做法挺合理的，否则你就需要在一个循环里反复执行乐观读，直到执行乐观读操作的期 间没有写操作(只有这样才能保证 x 和 y 的正确性和一致性)，
而循环读会浪费大量的 CPU。升 级为悲观读锁，代码简练且不易出错，建议你在具体实践时也采用这样的方法。


# 进一步理解乐观锁
如果你曾经用过数据库的乐观锁，可能会发现 StampedLock 的乐观读和数据库的乐观锁有异曲 同工之妙。      
数据库的乐观锁实现很简单，在生产订单的表 product_doc 里增加了一个数值型版本号字段 version， 每次更新 product_doc 这个表的时候，都将 version 字段加 1。生产订单的 UI 在展示的时候，需 要查询数据库，此时将这个 version 字段和其他业务字段一起返回给生产订单 UI。假设用户查询 的生产订单的 id=777，那么 SQL 语句类似下面这样:
```text
select id,version,* from table where id = 777
```
用户在生产订单 UI 执行保存操作的时候，后台利用下面的 SQL 语句更新生产订单，此处我们假 设该条生产订单的 version=9。
```text
update table set version = version +1,... where id = 777 and version = 9
```

你会发现数据库里的乐观锁，查询的时候需要把 version 字段查出来，更新的时候要利用 version 字段做验证。这个 version 字段就类似于 StampedLock 里面的 stamp。这样对比着看，相信你会 更容易理解 StampedLock 里乐观读的用法。


# stampedLock使用注意事项
对于读多写少的场景 StampedLock 性能很好，【简单的应用场景基本上可以替代 ReadWriteLock】， 但是【StampedLock 的功能仅仅是 ReadWriteLock 的子集】
使用的时候需要注意：      
- stampedLock 在命名上并没有增加 Reentrant，相必你已经意识到【stampedLock不支持可重入】。

- 另外，StampedLock 的【悲观读锁、写锁都不支持条件变量】，这个也需要你注意。

- 还有需要注意的是：如果线程阻塞在 StampedLock 的 readLock() 或者 writeLock() 上时，此时调用该阻塞线程的 interrupt() 方法，会导致 CPU 飙升。
如下面代码：      
```text
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
```
> 因此：所以，使用 StampedLock 一定不要调用中断操作，如果需要支持中断功能，
一定使用可中断的 悲观读锁 readLockInterruptibly() 和写锁 writeLockInterruptibly()。这个规则一定要记清楚。

# 总结

- StampedLock 的使用看上去有点复杂，但是如果你能理解乐观锁背后的原理，使用起来还是比较 流畅的。
- 建议你认真揣摩 Java 的官方示例，这个示例基本上就是一个最佳实践。
- 我们把 Java 官 方示例精简后，形成下面的代码模板，建议你在实际工作中尽量按照这个模板来使用。
```text
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
```


# 课后思考
StampedLock 支持锁的降级(通过 tryConvertToReadLock() 方法实现)和升级(通过 tryConvertToWriteLock() 方法实现)，但是建议你要慎重使用。
\下面的代码也源自 Java 的官方示 例，我仅仅做了一点修改，隐藏了一个 Bug，你来看看 Bug 出在哪里吧。
```text
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
```

















