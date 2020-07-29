package day05;

/**
 * @Author gaoqiangwei
 * @Date 2020/5/28 22:44
 * @Description
 */
public class Account {

    private Integer balance;

    public void transfer(Account target, Integer amt) {
        //锁定转出账户
        synchronized (this) {
            //锁定转入账户
            synchronized (target) {
                if (balance > amt) {
                    balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }
}
