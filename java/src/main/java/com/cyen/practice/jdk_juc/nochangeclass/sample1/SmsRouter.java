package com.cyen.practice.jdk_juc.nochangeclass.sample1;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qingshanpeng
 * @date 2021/8/21 10:40
 * @since 标果工厂-托曼尼
 */
public class SmsRouter {

    private final Map<Integer, SmsInfo> smsInfoMap;

    public SmsRouter() {
        this.smsInfoMap = loadSmsInfoFromDatabase();
    }

    private Map<Integer, SmsInfo> loadSmsInfoFromDatabase() {
        Map<Integer, SmsInfo> map = new ConcurrentHashMap<>();
        map.put(1, new SmsInfo("www.tencent.yun.com", 120L));
        map.put(2, new SmsInfo("www.aliyun.yun.com", 121L));
        map.put(3, new SmsInfo("www.baidu.yun.com", 130L));

        return map;
    }

    public Map<Integer, SmsInfo> getSmsInfoMap() {
        //todo
        //类似于写时复制
        //每个线程拿到的这个值，都是原来值的一份拷贝
        return Collections.unmodifiableMap(deep(smsInfoMap));
    }

    private Map<Integer, SmsInfo> deep(Map<Integer, SmsInfo> smsInfoMap) {
        Map<Integer, SmsInfo> newMap = new ConcurrentHashMap<>(smsInfoMap.size());
        for (Map.Entry<Integer, SmsInfo> entry : smsInfoMap.entrySet()) {
            newMap.put(entry.getKey(), new SmsInfo(entry.getValue()));
        }

        return newMap;
    }
}
