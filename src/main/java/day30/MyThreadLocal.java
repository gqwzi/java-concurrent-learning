package day30;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author gaoqiangwei
 * @Date 2020/6/29 23:23
 * @Description
 */
public class MyThreadLocal<T> {

    Map<Thread,T> locals = new ConcurrentHashMap<>();

    //获取线程变量
    T get() {
        return locals.get(Thread.currentThread());
    }
    //设置线程变量
    void set(T t) {
        locals.put(Thread.currentThread(), t);
    }

}
