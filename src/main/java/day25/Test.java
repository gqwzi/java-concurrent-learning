package day25;

import javax.sound.midi.Soundbank;
import java.util.concurrent.*;

/**
 * @Author gaoqiangwei
 * @Date 2020/6/25 12:19
 * @Description
 */
public class Test {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        CompletionService c = new ExecutorCompletionService(pool);
    }
}
