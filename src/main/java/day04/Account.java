package day04;

/**
 * @Author gaoqiangwei
 * @Date 2020/5/27 22:13
 * @Description
 */
public class Account {

    private final Object balLock = new Object();
    private Integer balance;

    private final Object pwdLock = new Object();
    private String password;

    //【取款】
    public void withDraw(Integer amt) {
        synchronized (balLock) {
            if (balance > amt) {
                balance -= amt;
            }
        }
    }
    //【查看余额】
    public Integer getWithDraw() {
        synchronized (balLock) {
            return balance;
        }
    }
    //【更新密码】
    public void updatePassword(String newPwd) {
        synchronized (pwdLock) {
            password = newPwd;
        }
    }
    //【查看密码】
    public String getPassword() {
        synchronized (pwdLock){
            return password;
        }
    }
}
