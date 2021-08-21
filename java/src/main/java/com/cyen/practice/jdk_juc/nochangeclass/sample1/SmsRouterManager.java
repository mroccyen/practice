package com.cyen.practice.jdk_juc.nochangeclass.sample1;

/**
 * @author qingshanpeng
 * @date 2021/8/21 14:20
 * @since 标果工厂-托曼尼
 */
public class SmsRouterManager {

    private volatile SmsRouter instance;

    /**
     * 这里在读的的时候，可以读到旧值
     * 因为getSmsRouter和resetSmsRouter不是互斥的
     *
     * @return SmsRouter
     */
    public SmsRouter getSmsRouter() {
        if (instance == null) {
            synchronized (this) {
                if (instance == null) {
                    instance = new SmsRouter();
                    return instance;
                }
            }
        }

        return instance;
    }

    /**
     * 主要用于读多写少场景，这里可以加synchronized，写操作不是瓶颈
     */
    public synchronized void resetSmsRouter() {
        instance = new SmsRouter();
    }
}
