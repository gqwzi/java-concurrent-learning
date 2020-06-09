package day17;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author gaoqiangwei
 * @Date 2020/6/9 23:06
 * @Description
 */
public class Cache<K,V> {

    final Map<K,V> map = new HashMap<>();

    private ReadWriteLock rwl = new ReentrantReadWriteLock();
    //读锁
    Lock readLock = rwl.readLock();
    //写锁
    Lock writeLock = rwl.writeLock();

    private V get(K k) {
        readLock.lock();
        try {
            return map.get(k);
        }finally {
            readLock.unlock();
        }
    }

    private V set(K key, V v) {
        writeLock.lock();
        try {
            return map.put(key, v);
        }finally {
            writeLock.unlock();
        }
    }

}

class Cache1<K, V>{

    final Map<K,V> map = new HashMap<>();

    private ReadWriteLock rwl = new ReentrantReadWriteLock();
    //读锁
    final Lock readLock = rwl.readLock();
    //写锁
    final Lock writeLock = rwl.writeLock();

    V get(K key) {
        V v = null;
        readLock.lock();
        try {
            v = map.get(key);
        }finally {
            readLock.unlock();
        }
        if (v != null) {
            return v;
        }

        writeLock.lock();
        try {
            v = map.get(key);
            if (v == null) {
                //查询数据库，省略无数代码。。。
                //v =  select xxx from table where id = xxx;
                map.put(key, v);
            }
            return v;
        }finally {
            writeLock.unlock();
        }
    }
}
