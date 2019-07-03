package net.lovexq.example.java8.completablefuture;

import java.util.Random;

/**
 * 时间工具类
 *
 * @author LuPindong
 * @time 2019-07-03 20:30
 */
public class Util {

    public static final Random RANDOM = new Random(System.currentTimeMillis());

    public static void fixedDelay() {
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void randomDelay() {
        try {
            Thread.sleep(100L + RANDOM.nextInt(100));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
