package day14;

/**
 * @Author gaoqiangwei
 * @Date 2020/6/2 23:29
 * @Description
 */
public class SampleLock {
    private volatile int state;
    //加锁
    public void lock() {
        state = 1;
    }
    //解锁
    public void unlock() {
        state = 0;
    }
}
