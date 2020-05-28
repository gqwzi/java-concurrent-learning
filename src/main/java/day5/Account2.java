package day5;

/**
 * @Author gaoqiangwei
 * @Date 2020/5/28 23:42
 * @Description
 */
public class Account2 {
    private Integer id;
    private Integer balance;

    public void transfer(Account2 target, Integer amt) {
        Account2 left = this; //①
        Account2 right = target; //②
        if (this.id > target.id) { //③
            left = target; //④
            right = this; //⑤
        } //⑥
        // 锁定序号小的账户
        synchronized (left) {
            // 锁定序号大的账户
            synchronized (right) {
                if (balance > amt) {
                    balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }
}
