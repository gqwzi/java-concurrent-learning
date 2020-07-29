package day04;

/**
 * @Author gaoqiangwei
 * @Date 2020/5/27 22:22
 * @Description
 */
public class Account1 {

    private Integer balance;

    public void transfer(Account1 target,Integer amt) {
        if (balance > amt) {
            balance -= amt;
            target.balance += amt;
        }
    }
    //直觉告诉你这样应该就可以了
    public synchronized void transfer1(Account1 target,Integer amt) {
        if (balance > amt) {
            balance -= amt;
            target.balance += amt;
        }
    }
}
