package day16;

import java.util.concurrent.Semaphore;

/**
 * @Author gaoqiangwei
 * @Date 2020/6/5 22:56
 * @Description
 */
public class Test {
    static int count;
    static final Semaphore s = new Semaphore(1);

    static void addOne()throws Exception {
        s.acquire();
        try {
            count +=1;
        }finally {
            s.release();
        }
    }
}
