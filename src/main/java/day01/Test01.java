package day01;

/**
 * 【并发导致的问题】
 * 现象：多个线程协作操作同一个变量，每个线程都操作一遍，每次对count加1，讲道理最后这个count的值应该为20000
 * 结果：最后这个值介于10000~20000之间
 * 原因：并发
 *
 * @Author gaoqiangwei
 * @Date 2020/5/24 17:48
 * @Description
 */
public class Test01 {
    private int count = 0;
    private void add10k() {
        int start= 0;
        while (start++ < 10000) {
            count+=1;
        }
    }

    public static void main(String[] args) throws Exception{
        final Test01 test01 = new Test01();
        Thread t1 = new Thread(() -> {
            test01.add10k();
        });
        Thread t2 = new Thread(() -> {
            test01.add10k();
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("最终结果：" + test01.count);
    }
}


