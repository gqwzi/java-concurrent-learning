package day21;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author gaoqiangwei
 * @Date 2020/6/12 23:41
 * @Description
 */
public class TestCount {
    int count = 0;
    public void add10k() {
        int idx = 0;
        while(idx ++ < 10000) {
            count += 1;
        }
    }
}

class TestAtomic {
    AtomicLong atomicLong = new AtomicLong(0);
    public void add10k() {
        int idx = 0;
        while(idx++ < 10000) {
            atomicLong.getAndIncrement();
        }
    }
}