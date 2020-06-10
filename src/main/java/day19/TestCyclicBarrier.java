//package day19;
//
//import java.util.Vector;
//import java.util.concurrent.CyclicBarrier;
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;
//
///**
// * @Author gaoqiangwei
// * @Date 2020/6/10 23:52
// * @Description
// */
//public class TestCyclicBarrier {
//    //订单队列
//    Vector<P> pos;
//    //派送单队列
//    Vector<D> dos;
//
//    Executor executors = Executors.newFixedThreadPool(1);
//
//    final CyclicBarrier barrier = new CyclicBarrier(2,()-> {
//        executors.execute(() -> check());
//    });
//
//    void check() {
//        P p = pos.remove(0);
//        D d = dos.remove(0);
//        diff = check(xxx, xxx);
//        save(diff);
//    }
//
//    void checkAll() {
//        // 循环查询订单库
//        Thread t1 = new Thread(() -> {
//            while(存在未对账订单) {
//                // 查询订单库
//                pos.add(getPOrders());
//                // 等待
//                barrier.await();
//            }
//        });
//        t1.start();
//        Thread t2 = new Thread(() -> {
//            while(存在未派送订单) {
//                // 查询订单库
//                pos.add(getPDos());
//                // 等待
//                barrier.await();
//            }
//        });
//        t2.start();
//    }
//}
