package com.cyen.practice.jdk_juc.nochangeclass;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author qingshanpeng
 * @date 2021/8/21 10:40
 * @since 标果工厂-托曼尼
 */
public class SmsRouter {

    protected static final DatabaseUtils DATABASE_UTILS = new DatabaseUtils();

    private final SmsRouterInfoMap smsRouterInfoMap;

    public SmsRouter() {
        this.smsRouterInfoMap = new SmsRouterInfoMap(loadSmsInfoFromDatabase());
    }

    /**
     * 从数据库中加载数据
     *
     * @return 数据库中存储的短信信息
     */
    private Map<Integer, SmsInfo> loadSmsInfoFromDatabase() {
        return DATABASE_UTILS.getSmsInfoFromDatabase();
    }

    /**
     * 写实复制
     *
     * @return map
     */
    public Map<Integer, SmsInfo> getSmsInfoMap() {
        //todo
        //类似于写时复制
        //每个线程拿到的这个值，都是原来值的一份拷贝
        return smsRouterInfoMap.deepCopy();
    }

    private static class SmsRouterInfoMap extends TreeMap<Integer, SmsInfo> {

        private SmsRouterInfoMap(Map<Integer, SmsInfo> originSmsInfoMap) {
            this.putAll(originSmsInfoMap);
        }

        private Map<Integer, SmsInfo> deepCopy() {
            return deepCopy(this);
        }

        private Map<Integer, SmsInfo> deepCopy(Map<Integer, SmsInfo> smsInfoMap) {
            Map<Integer, SmsInfo> newMap = new SmsRouterInfoMap(smsInfoMap);
            for (Map.Entry<Integer, SmsInfo> entry : smsInfoMap.entrySet()) {
                newMap.put(entry.getKey(), new SmsInfo(entry.getValue()));
            }

            return newMap;
        }
    }
}
