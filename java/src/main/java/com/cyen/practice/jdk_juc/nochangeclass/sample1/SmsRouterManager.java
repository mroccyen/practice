package com.cyen.practice.jdk_juc.nochangeclass.sample1;

/**
 * @author qingshanpeng
 * @date 2021/8/21 14:20
 * @since 标果工厂-托曼尼
 */
public class SmsRouterManager {

    private volatile SmsRouter instance;

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

    public void resetSmsRouter() {
        instance = new SmsRouter();
    }
}
