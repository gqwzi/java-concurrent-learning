package day30;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author gaoqiangwei
 * @Date 2020/6/29 22:40
 * @Description
 */
public class ThreadId {
    static final AtomicLong nextId = new AtomicLong(0);
    static final ThreadLocal<Long> tl = ThreadLocal.withInitial(() -> nextId.getAndIncrement());
    static long get() {
        return tl.get();
    }
}
