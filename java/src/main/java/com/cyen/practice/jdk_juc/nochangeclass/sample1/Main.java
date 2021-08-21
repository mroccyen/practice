package com.cyen.practice.jdk_juc.nochangeclass.sample1;

import java.util.Map;

/**
 * @author qingshanpeng
 * @date 2021/8/21 10:51
 * @since 标果工厂-托曼尼
 */
public class Main {
    public static void main(String[] args) {
        SmsRouter router = new SmsRouter();

        Map<Integer, SmsInfo> smsInfoMap1 = router.getSmsInfoMap();
        SmsInfo smsInfo = smsInfoMap1.get(3);
        smsInfo.setUrl("www.feichi.yun.com");
        smsInfo.setDataSize(100L);
        System.out.println("-----smsInfoMap1-----");
        for (Map.Entry<Integer, SmsInfo> entry : smsInfoMap1.entrySet()) {
            System.out.println(entry.getValue().getUrl() + " " + entry.getValue().getDataSize());
        }

        System.out.println("-----smsInfoMap2-----");
        Map<Integer, SmsInfo> smsInfoMap2 = router.getSmsInfoMap();
        for (Map.Entry<Integer, SmsInfo> entry : smsInfoMap2.entrySet()) {
            System.out.println(entry.getValue().getUrl() + " " + entry.getValue().getDataSize());
        }
    }
}
