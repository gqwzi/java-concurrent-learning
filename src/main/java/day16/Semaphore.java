package day16;

import java.util.Queue;

/**
 * @Author gaoqiangwei
 * @Date 2020/6/5 22:47
 * @Description
 */
public class Semaphore {
    //计数
    int count;
    //等待队列
    Queue queue;
    //初始化
    Semaphore(int c) {
        this.count=c;
    }

    void down() {
        this.count--;
        if (this.count<0) {
            //将当前队列插入等待队列
            //阻塞当前线程
        }
    }

    void up() {
        count ++;
        if(count <=0) {
            //移除等待队列中的某个线程T
            //唤醒线程T
        }
    }
}
