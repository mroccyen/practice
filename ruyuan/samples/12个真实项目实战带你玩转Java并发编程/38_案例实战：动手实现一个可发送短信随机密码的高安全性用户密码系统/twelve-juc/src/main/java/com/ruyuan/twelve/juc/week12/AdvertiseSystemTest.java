/*
授权声明：
本源码系《Java多线程编程实战指南（设计模式篇）第2版》一书（ISBN：978-7-121-38245-1，以下称之为“原书”）的配套源码，
欲了解本代码的更多细节，请参考原书。
本代码仅为原书的配套说明之用，并不附带任何承诺（如质量保证和收益）。
以任何形式将本代码之部分或者全部用于营利性用途需经版权人书面同意。
将本代码之部分或者全部用于非营利性用途需要在代码中保留本声明。
任何对本代码的修改需在代码中以注释的形式注明修改人、修改时间以及修改内容。
本代码可以从以下网址下载：
https://github.com/Viscent/javamtp
http://www.broadview.com.cn/38245
*/

package com.ruyuan.twelve.juc.week12;


/**
 * 广告计费智能预警系统，广告计费的时候，如果发现广告主钱快没了，就智能预警通知他
 */
public class AdvertiseSystemTest {

    // 告警日志抑制阈值
    private static final int ALARM_MSG_SUPRESS_THRESHOLD = 10;

    static {

        // 初始化告警模块
        AlarmManager.getInstance().init();
    }

    public static void main(String[] args) {
        AdvertiseSystemTest advertiseSystemTest = new AdvertiseSystemTest();
        String advertiser = "zhss";
        String phoneNumber = "12332423423";
        try {
            /**
             * 投放广告
             */
            advertiseSystemTest.putAdvertise(advertiser, phoneNumber);

        } catch (Exception e) {
            // 当前广告主钱快不够了预警
            final AlarmManager alarmManager = AlarmManager.getInstance();
            // 告警被重复发送至告警模块的次数
            int duplicateSubmissionCount;
            String alarmId = "00000010000020";

            // 上报预警系统到智能预警系统
            duplicateSubmissionCount = alarmManager.sendAlarm(AlarmType.FAULT,
                                                              alarmId,
                                                              advertiser,
                                                              phoneNumber);
            if (duplicateSubmissionCount < ALARM_MSG_SUPRESS_THRESHOLD) {
                // 超过一定的告警次数后打印日志，不在上报到智能预警系统
                System.out.println("Alarm[" + alarmId + "] advertiser:"
                                           + advertiser + "，phoneNumber:" + phoneNumber);
            } else {
                if (duplicateSubmissionCount == ALARM_MSG_SUPRESS_THRESHOLD) {
                    System.out.println("Alarm[" + alarmId + "] was raised more than "
                                               + ALARM_MSG_SUPRESS_THRESHOLD
                                               + " times, it will no longer be logged.");
                }
            }
        }


    }

    // 投放广告
    private void putAdvertise(String advertiser, String phoneNumber) throws Exception {
        // 根据广告内容，计算广告的费用,低于多少阀值的时候预警
        throw new RuntimeException(advertiser + "号主，xxxx广告系统钱快没了，请及时充值");
    }


}
