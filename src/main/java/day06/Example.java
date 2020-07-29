package day06;

/**
 * @Author gaoqiangwei
 * @Date 2020/5/30 09:30
 * @Description
 */
public class Example {
    public static void main(String[] args)throws Exception {
        Product p = new Product();

        Thread t1 = new Thread( () -> {
            try {
                System.out.println("生产");
                for (int i = 0;i<200;i++) {
                    Thread.sleep(1000);
                    p.produce();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread( () -> {
            try {
                System.out.println("【消费】");
                for (int i = 0;i<200;i++) {
                    Thread.sleep(2000);
                    p.consumer();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
    }

}

class Product{

    private Integer goods = 0;

    public  synchronized void produce()throws Exception {
        while (goods >= 10) {
            wait();
        }
        goods += 1;
        System.out.println("【生产】1个,【当前】：" + goods);
        notifyAll();
    }

    public  synchronized void consumer()throws Exception {
        while (goods <= 0) {
            wait();
        }
        goods -= 1;
        System.out.println("消费》1个,【当前】：" + goods);
        notifyAll();
    }
}