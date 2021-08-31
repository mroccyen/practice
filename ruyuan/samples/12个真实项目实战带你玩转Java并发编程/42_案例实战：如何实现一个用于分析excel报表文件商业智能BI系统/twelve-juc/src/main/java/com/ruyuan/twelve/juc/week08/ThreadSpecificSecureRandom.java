package com.ruyuan.twelve.juc.week08;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:基于线程特有存储模式来随机生成高安全的密码
 **/
public class ThreadSpecificSecureRandom {

    private static final ThreadSpecificSecureRandom INSTANCE = new ThreadSpecificSecureRandom();

    private ThreadSpecificSecureRandom() {

    }

    public static ThreadSpecificSecureRandom getInstance() {
        return INSTANCE;
    }

    /**
     * 定义 线程本地存储的TsObjectProxy代理类，secureRandom：TSObject
     */
    private static final ThreadLocal<SecureRandom> SECURE_RANDOM_THREAD_LOCAL = new ThreadLocal<SecureRandom>() {

        @Override
        protected SecureRandom initialValue() {
            // 当当前线程持有的TsObjectProxy中TSObject对象为空的时候进行初始化
            SecureRandom secureRandom = null;
            try {
                secureRandom = SecureRandom.getInstance("SHA1PRNG");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                secureRandom = new SecureRandom();
            }
            return secureRandom;
        }
    };

    /**
     * 随机生成一个数
     *
     * @param bound 范围
     * @return 结果
     */
    public int nextInt(int bound) {
        SecureRandom secureRandom = SECURE_RANDOM_THREAD_LOCAL.get();
        return secureRandom.nextInt(bound);
    }
}