package day29;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author gaoqiangwei
 * @Date 2020/6/28 23:14
 * @Description
 */
public class RouterTable {
    //Key：接口名
    //value：路由名
    ConcurrentHashMap<String, CopyOnWriteArraySet> rt = new ConcurrentHashMap<>();

    //根据接口名获取路由表
    public CopyOnWriteArraySet get(String iface) {
        return rt.get(iface);
    }

    public void remove(Router router) {
        Set<Router> set =  rt.get(router.getIface());
        if (set != null) {
            set.remove(router);
        }
    }

    public void add(Router router) {
        Set<Router> set = rt.computeIfAbsent(
                router.getIface(), r -> new CopyOnWriteArraySet()
        );
        set.add(router);
    }

}
