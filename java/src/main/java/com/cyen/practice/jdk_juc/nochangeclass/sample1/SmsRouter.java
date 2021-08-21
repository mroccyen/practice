package com.cyen.practice.jdk_juc.nochangeclass.sample1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qingshanpeng
 * @date 2021/8/21 10:40
 * @since 标果工厂-托曼尼
 */
public class SmsRouter {

    protected static final DatabaseUtils DATABASE_UTILS = new DatabaseUtils();

    private final SmsRouterInfo smsRouterInfo;

    public SmsRouter() {
        this.smsRouterInfo = new SmsRouterInfo(loadSmsInfoFromDatabase());
    }

    /**
     * 从数据库中加载数据
     *
     * @return 数据库中存储的短信信息
     */
    private Map<Integer, SmsInfo> loadSmsInfoFromDatabase() {
        return DATABASE_UTILS.getSmsInfoFromDatabase();
    }

    public Map<Integer, SmsInfo> getSmsInfo() {
        return smsRouterInfo.getOriginDeepCopySmsInfoMap();
    }

    private static class SmsRouterInfo {

        /**
         * 原始短信服务器信息
         */
        private final Map<Integer, SmsInfo> originSmsInfoMap;

        private SmsRouterInfo(Map<Integer, SmsInfo> originSmsInfoMap) {
            this.originSmsInfoMap = originSmsInfoMap;
        }

        public Map<Integer, SmsInfo> getOriginDeepCopySmsInfoMap() {
            //todo
            //类似于写时复制
            //每个线程拿到的这个值，都是原来值的一份拷贝
            return deepCopy(originSmsInfoMap);
        }

        private Map<Integer, SmsInfo> deepCopy(Map<Integer, SmsInfo> smsInfoMap) {
            Map<Integer, SmsInfo> newMap = new ConcurrentHashMap<>(smsInfoMap.size());
            for (Map.Entry<Integer, SmsInfo> entry : smsInfoMap.entrySet()) {
                newMap.put(entry.getKey(), new SmsInfo(entry.getValue()));
            }

            return newMap;
        }
    }
}
