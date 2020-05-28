package day5;


import day5.Account1;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author gaoqiangwei
 * @Date 2020/5/28 23:24
 * @Description
 */
public class Allocator {
    private List<Object> lock = new ArrayList();
    //一次性申请所有资源
    synchronized boolean apply(Object from, Object to) {
        if (lock.contains(from) || lock.contains(to)) {
            return false;
        }
        lock.add(from);
        lock.add(to);
        return true;
    }
    //释放资源
    synchronized void free(Object from, Object to) {
        lock.remove(from);
        lock.remove(to);
    }
}

class Account1 {

    private Integer balance;

    //应该为单例
    private Allocator allocator;

    public void transfer(Account1 target, Integer amt) {
        while (allocator.apply(this,target)) {
            try {
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
            }finally {
                allocator.free(this,target);
            }
        }
    }
}