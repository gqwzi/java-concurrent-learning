package day02;

/**
 * @Author gaoqiangwei
 * @Date 2020/5/25 23:06
 * @Description
 */
public class Test01 {
    public static void main(String[] args) {
        Thread B = new Thread( () -> {
            //主线程调用 B.start() 之前,所有对共享变量的修改，此处皆可见,此例中，var==77
        });
        // 此处对共享变量 var 修改 var = 77;
        int var = 77;
        //主线程启动子线程
        B.start();
    }
}

class VolatileExample {
    int x = 0;
    volatile boolean v = false;
    public void write() {
        x = 42;
        v = true;
    }
    public void reader() {
        if (v) {
            //这里x是多少呢？
        }
    }
}

class DemoClass {
    private static int x = 10;
    public void test() {
        synchronized (this) {
            //x是共享变量，初始值是10
            if (this.x < 12) {
                this.x = 12;
            }
        }//此处自动解锁
    }
}
