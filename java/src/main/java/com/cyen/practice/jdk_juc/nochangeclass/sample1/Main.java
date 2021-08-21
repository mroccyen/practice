package com.cyen.practice.jdk_juc.nochangeclass.sample1;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author qingshanpeng
 * @date 2021/8/21 10:51
 * @since 标果工厂-托曼尼
 */
public class Main {

    public static SmsRouterManager smsRouterManager = new SmsRouterManager();

    public static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                SmsRouter router = smsRouterManager.getSmsRouter();

                Map<Integer, SmsInfo> smsInfoMap1 = router.getSmsInfo();
                SmsInfo smsInfo = smsInfoMap1.get(3);
                smsInfo.setUrl("www.feichi.yun.com");
                smsInfo.setDataSize(100L);
                smsInfoMap1.put(4, new SmsInfo("www.biaoguo.yun.com", 150L));
                System.out.println("-----smsInfoMap1-----");
                for (Map.Entry<Integer, SmsInfo> entry : smsInfoMap1.entrySet()) {
                    System.out.println(Thread.currentThread().getName() + " -> " + entry.getValue().getUrl() + " " + entry.getValue().getDataSize());
                }

                //更新数据到数据库
                SmsRouter.DATABASE_UTILS.updateSmsInfoToDatabase(smsInfoMap1);
                //重置SmsRouter
                smsRouterManager.resetSmsRouter();
            });
        }

        Thread.sleep(500);

        SmsRouter router = smsRouterManager.getSmsRouter();
        System.out.println("-----smsInfoMap2-----");
        Map<Integer, SmsInfo> smsInfoMap2 = router.getSmsInfo();
        for (Map.Entry<Integer, SmsInfo> entry : smsInfoMap2.entrySet()) {
            System.out.println(Thread.currentThread().getName() + " -> " + entry.getValue().getUrl() + " " + entry.getValue().getDataSize());
        }
    }
}
