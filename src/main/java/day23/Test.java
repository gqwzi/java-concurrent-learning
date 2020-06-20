package day23;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author gaoqiangwei
 * @Date 2020/6/21 00:18
 * @Description
 */
public class Test {


    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Result r = new Result();

        r.setAAA(a);
        Future<T> future = executor.submit(new Task(r), r);
        Result fr = future.get();
        // 下面等式成立
        fr === r;
        fr.getAAA() === a;
        fr.getXXX() === x;
    }
}

class Task implements Runnable{

    Result r;
    Task(Result r) {
        this.r == r;
    }

    @Override
    public void run() {
        //可以操作result
         a = r.getAAA();
         r.setXXX(x);
    }
}