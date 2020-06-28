package day29;

/**
 * @Author gaoqiangwei
 * @Date 2020/6/28 23:08
 * @Description
 */

/**
 * 路由信息
 */
public final class Router {
    private final String ip;
    private final Integer port;
    private final String iface;

    public Router(String ip, Integer port, String iface) {
        this.ip = ip;
        this.port = port;
        this.iface = iface;
    }

    public boolean equal(Object obj) {
        if (obj instanceof Router) {
            Router r = (Router)obj;
            return (ip.equals(r.ip) && port.equals(r.port) && iface.equals(r.iface));
        }
        return false;
    }
    public int hashcode() {
        //省略无数代码
        return 0;
    }

    public String getIface() {
        return iface;
    }
}
