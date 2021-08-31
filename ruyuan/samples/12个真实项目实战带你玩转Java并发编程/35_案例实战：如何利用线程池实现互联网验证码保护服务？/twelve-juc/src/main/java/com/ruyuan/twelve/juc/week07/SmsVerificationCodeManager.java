package com.ruyuan.twelve.juc.week07;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:互联网验证码保护服务，短信验证码管理组件，生成随机的6位数的验证，发送验证码到对应的用户手机中
 * 每个用户的发送当做一个task任务，通过线程池的方式来进行提交task
 **/
public class SmsVerificationCodeManager {


    private static final ExecutorService EXECUTOR = new ThreadPoolExecutor(1,
                                                                           Runtime.getRuntime().availableProcessors() * 2,
                                                                           60,
                                                                           TimeUnit.SECONDS,
                                                                           new SynchronousQueue<>(),
                                                                           new ThreadFactory() {
                                                                               @Override
                                                                               public Thread newThread(Runnable r) {
                                                                                   Thread thread = new Thread(r, "verificationCode-thread");
                                                                                   thread.setDaemon(true);
                                                                                   return thread;
                                                                               }
                                                                           });

    /**
     * 发送验证码
     *
     * @param phoneNumber 用户手机号
     */
    public void sendVerificationCode(String phoneNumber) {
        // 创建一个发送验证的task任务
        Runnable task = new Runnable() {
            @Override
            public void run() {
                // 随机生成一个验证码
                String verificationCode = generateVerificationCode();
                // 发送短信
                sendSms(phoneNumber, verificationCode);
            }
        };
        EXECUTOR.submit(task);
    }

    /**
     * 发送短信给用户
     *
     * @param phoneNumber      用户手机号
     * @param verificationCode 验证码
     */
    private void sendSms(String phoneNumber, String verificationCode) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发送给用户手机号：" + phoneNumber + "登录验证码：" + verificationCode);
    }

    /**
     * 随机生成6位数的验证阿妈
     *
     * @return
     */
    private String generateVerificationCode() {
        Random random = new Random();
        StringBuilder verificationCodeBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int code = random.nextInt(10);
            verificationCodeBuilder.append(code);
        }
        return verificationCodeBuilder.toString();
    }
}