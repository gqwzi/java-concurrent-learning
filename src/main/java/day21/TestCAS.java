package day21;

/**
 * @Author gaoqiangwei
 * @Date 2020/6/13 11:59
 * @Description
 */
public class TestCAS {
    private int count;

    synchronized int cas(int expect, int newValue) {
        //读目前count的值
        int curCount = count;
        //一致才修改
        if (curCount == expect) {
            count = newValue;
        }
        return curCount;
    }
}

class TestCAS1{

    int count = 0;

    int newValue;

    void addOne() {
        do{
            newValue = count + 1;//1
        }while( count != cas(count, newValue));//2
    }

    synchronized int cas(int expect, int newValue) {
        int curValue = count;
        if (curValue == newValue) {
            count = newValue;
        }
        return curValue;
    }
}
