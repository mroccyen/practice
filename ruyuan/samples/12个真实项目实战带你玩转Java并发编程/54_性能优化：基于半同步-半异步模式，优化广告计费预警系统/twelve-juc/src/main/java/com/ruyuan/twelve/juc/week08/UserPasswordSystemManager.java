package com.ruyuan.twelve.juc.week08;

import java.util.concurrent.*;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:高安全性用户密码系统 当用户初始化注册的时候给用户随机生成一个密码发送到用户手机上
 **/
public class UserPasswordSystemManager {

    /**
     * 单例对象
     */
    private static final UserPasswordSystemManager INSTANCE = new UserPasswordSystemManager();

    /**
     * 私有化
     */
    private UserPasswordSystemManager() {

    }

    /**
     * 获取单例对象
     *
     * @return 结果
     */
    public static UserPasswordSystemManager getInstance() {
        return INSTANCE;
    }

    private static final ExecutorService EXECUTOR = new ThreadPoolExecutor(1,
                                                                           Runtime.getRuntime().availableProcessors() * 2,
                                                                           60,
                                                                           TimeUnit.SECONDS,
                                                                           new SynchronousQueue<>(),
                                                                           new ThreadFactory() {
                                                                               @Override
                                                                               public Thread newThread(Runnable r) {
                                                                                   Thread thread = new Thread(r, "register-thread-");
                                                                                   thread.setDaemon(true);
                                                                                   return thread;
                                                                               }
                                                                           });

    /**
     * 注册用户
     */
    public void register(String phoneNumber) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                // 随机生成一个用户的6位数密码
                ThreadSpecificSecureRandom threadSpecificSecureRandom = ThreadSpecificSecureRandom.getInstance();
                StringBuilder passwordBuilder = new StringBuilder();
                for (int i = 0; i < 6; i++) {
                    passwordBuilder.append(threadSpecificSecureRandom.nextInt(10));
                }
                String initPassword = passwordBuilder.toString();

                // 注册用户
                saveUser(phoneNumber, initPassword);

                // 发送短信
                sendMessage(phoneNumber, initPassword);
            }
        };

        EXECUTOR.submit(task);
    }

    /**
     * 保存用户
     *
     * @param phoneNumber  手机号
     * @param initPassword 密码
     */
    private void saveUser(String phoneNumber, String initPassword) {
        System.out.println("保存用户手机号：" + phoneNumber + ", 密码：" + initPassword);
    }

    private void sendMessage(String phoneNumber, String initPassword) {
        // 发送短信
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("手机号：" + phoneNumber + "用户注册完成" + ", 密码：" + initPassword);
    }
}