//package day19;
//
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * @Author gaoqiangwei
// * @Date 2020/6/10 23:18
// * @Description
// */
//public class TestDiff {
//    public static void main(String[] args)throws Exception {
//        while (存在未对账订单) {
//            Thread t1 = new Thread( () -> {
//                //查询未对账订单
//            });
//            t1.start();
//            Thread t2 = new Thread( () -> {
//                //查询未派送订单
//            });
//            t2.start();
//
//            t1.join();
//            t2.join();
//            diff = check(xxx, xxx);
//            save(diff);
//        }
//    }
//
//    public static  void diffByPool() {
//        int count=2;
//        ExecutorService es = Executors.newFixedThreadPool(2);
//        while (//存在未对账订单) {
//            es.execute(() -> {
//                //查询未对账订单
//            });
//            es.execute(() -> {
//                //查询未派送订单
//            });
//            /* ??如何实现等待??*/
//            diff = check(xxx, xxx);
//            save(diff);
//        }
//    }
//
//    public static void diffByCountDownLatch()throws Exception {
//        CountDownLatch cdl = new CountDownLatch(2);
//        ExecutorService es = Executors.newFixedThreadPool(2);
//        while(存在未对账订单) {
//            es.execute(() -> {
//                //查询未对账订单
//                cdl.countDown();
//            });
//            es.execute(() -> {
//                //查询未派送订单
//                cdl.countDown();
//            });
//            cdl.await();
//            diff = check(xxx, xxx);
//            save(diff);
//        }
//    }
//}
//
