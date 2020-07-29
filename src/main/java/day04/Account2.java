package day04;

/**
 * @Author gaoqiangwei
 * @Date 2020/5/27 22:39
 * @Description
 */
public class Account2 {

    private Integer balance;

    private Object lock;

    private Account2(Object lock) {
        this.lock = lock;
    }

    public void transfer(Account2 target, Integer amt) {
        synchronized (lock) {
            if (balance > amt) {
                balance -= amt;
                target.balance += amt;
            }
        }
    }

    public void transfer1(Account2 target, Integer amt) {
        synchronized (Account2.class) {
            if (balance > amt) {
                balance -= amt;
                target.balance += amt;
            }
        }
    }
}
