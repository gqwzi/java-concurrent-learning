package day03;

/**
 * @Author gaoqiangwei
 * @Date 2020/5/26 23:59
 * @Description
 */
public class SafeCalc {
    int value = 0;
    int get() {
        return value;
    }
    synchronized void addOne() {
        value +=1;
    }
}
