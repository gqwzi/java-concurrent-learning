package day43;

/**
 * @Author gaoqiangwei
 * @Date 2020/7/29 23:31
 * @Description
 */
public class VersionedRef<T> {

    final T value;
    final long version;
    public VersionedRef(T value,long version) {
        this.value = value;
        this.version = version;
    }

}
