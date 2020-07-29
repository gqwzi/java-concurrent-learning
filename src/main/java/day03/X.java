package day03;

/**
 * @Author gaoqiangwei
 * @Date 2020/5/26 23:49
 * @Description
 */
public class X {
    synchronized void foo() {
        //锁一个非静态方法
    }

    synchronized static void bar() {
        //锁一个静态方法
    }

    Object obj = new Object();
    void baz() {
        //锁一段代码块
        synchronized (obj) {
            //临界区
        }
    }

}
