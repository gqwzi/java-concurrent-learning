package day1;

/**
 * @Author gaoqiangwei
 * @Date 2020/5/24 19:19
 * @Description
 */
public class Test02 {
}

class Singleton {

    static Singleton instance;

    static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
