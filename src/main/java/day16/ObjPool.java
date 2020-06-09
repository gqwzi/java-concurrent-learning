package day16;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * @Author gaoqiangwei
 * @Date 2020/6/5 23:12
 * @Description
 */
public class ObjPool<T, R> {
    final List<T> pool;
    final Semaphore sem;
    ObjPool(int size, T t) {
        pool = new Vector<>();
        for (int i = 0; i < size; i++) {
            pool.add(t);
        }
        sem = new Semaphore(size);
    }
    R exec(Function<T,R> func)throws Exception{
        T t = null;
        sem.acquire();
        try {
            t = pool.remove(0);
            return func.apply(t);
        }finally {
            pool.add(t);
            sem.release();
        }
    }
    ObjPool<Long, String> obj = new ObjPool<Long, String>(10,2L);
    public static void main(String[] args) {
//    pool.exec(t -> {
//            System.out.println(t);
//            return t.toString();
//        });
    }
}
