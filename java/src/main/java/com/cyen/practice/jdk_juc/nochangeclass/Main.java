package com.cyen.practice.jdk_juc.nochangeclass;

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

    public static ExecutorService executorService1 = Executors.newFixedThreadPool(5);

    public static ExecutorService executorService2 = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            executorService1.execute(() -> {
                SmsRouter router = smsRouterManager.getSmsRouter();

                Map<Integer, SmsInfo> smsInfoMap1 = router.getSmsInfoMap();
                SmsInfo smsInfo = smsInfoMap1.get(3);
                smsInfo.setUrl("www.feichi.yun.com");
                smsInfo.setDataSize(100L);
                smsInfoMap1.put(4, new SmsInfo("www.jingdong.yun.com", 150L));
                smsInfoMap1.put(6, new SmsInfo("www.dedao.yun.com", 89L));
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

        for (int i = 0; i < 5; i++) {
            executorService2.execute(() -> {
                SmsRouter router = smsRouterManager.getSmsRouter();

                Map<Integer, SmsInfo> smsInfoMap1 = router.getSmsInfoMap();
                smsInfoMap1.put(4, new SmsInfo("www.youdao.yun.com", 200L));
                smsInfoMap1.put(5, new SmsInfo("www.wangyi.yun.com", 300L));
                System.out.println("-----smsInfoMap2-----");
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
        System.out.println("-----smsInfoMap3-----");
        Map<Integer, SmsInfo> smsInfoMap2 = router.getSmsInfoMap();
        for (Map.Entry<Integer, SmsInfo> entry : smsInfoMap2.entrySet()) {
            System.out.println(Thread.currentThread().getName() + " -> " + entry.getValue().getUrl() + " " + entry.getValue().getDataSize());
        }

        executorService1.shutdown();
        executorService2.shutdown();
    }
}
