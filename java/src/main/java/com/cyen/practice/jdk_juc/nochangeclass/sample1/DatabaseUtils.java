package com.cyen.practice.jdk_juc.nochangeclass.sample1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qingshanpeng
 * @date 2021/8/21 14:37
 * @since 标果工厂-托曼尼
 */
public class DatabaseUtils {

    Map<Integer, SmsInfo> smsInfoMap = new ConcurrentHashMap<>();

    public DatabaseUtils() {
        smsInfoMap.put(1, new SmsInfo("www.tencent.yun.com", 120L));
        smsInfoMap.put(2, new SmsInfo("www.aliyun.yun.com", 121L));
        smsInfoMap.put(3, new SmsInfo("www.baidu.yun.com", 130L));
    }

    /**
     * 从数据库中加载数据
     *
     * @return 数据库中存储的短信信息
     */
    public Map<Integer, SmsInfo> getSmsInfoFromDatabase() {
        return smsInfoMap;
    }

    /**
     * 更新数据到数据库
     *
     * @param smsInfoMap 数据库中存储的短信信息
     */
    public void updateSmsInfoToDatabase(Map<Integer, SmsInfo> smsInfoMap) {
        this.smsInfoMap = smsInfoMap;
    }
}
