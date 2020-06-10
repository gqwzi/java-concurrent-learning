package day20;

import java.util.ArrayList;
import java.util.Vector;

/**
 * @Author gaoqiangwei
 * @Date 2020/6/11 07:15
 * @Description
 */
public class SafeArrayList<T> {
    Vector v;
    ArrayList<T> c = new ArrayList();

    // 控制访问路径

    public synchronized T get(int idx) {
        return c.get(idx);
    }

    public synchronized void add(int idx, T t) {
        c.add(idx, t);
    }

    public synchronized boolean addIfNotExit(T t) {
        if (!c.contains(t)) {
            c.add(t);
            return true;
        }
        return false;
    }
}
