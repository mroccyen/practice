package com.ruyuan.twelve.juc.week07;

/**
 * @author <a href="mailto:little@163.com">little</a>
 * version: 1.0
 * Description:
 **/
public class SmsVerificationCodeManagerTest {

    public static void main(String[] args) throws InterruptedException {
        SmsVerificationCodeManager verificationCodeManager = new SmsVerificationCodeManager();
        verificationCodeManager.sendVerificationCode("123456789");
        verificationCodeManager.sendVerificationCode("123456788");
        verificationCodeManager.sendVerificationCode("123456787");
        verificationCodeManager.sendVerificationCode("123456786");

        Thread.sleep(100);
    }
}