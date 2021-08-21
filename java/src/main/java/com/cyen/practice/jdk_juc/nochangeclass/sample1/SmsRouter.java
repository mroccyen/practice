package com.cyen.practice.jdk_juc.nochangeclass.sample1;

import java.util.Map;
import java.util.Set;
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

    public Map<Integer, SmsInfo> getSmsInfo() {
        return smsRouterInfoMap.deepCopy();
    }

    private static class SmsRouterInfoMap extends TreeMap<Integer, SmsInfo> {

        /**
         * 原始短信服务器信息
         */
        private final Map<Integer, SmsInfo> smsInfoMap;

        private SmsRouterInfoMap(Map<Integer, SmsInfo> originSmsInfoMap) {
            //todo
            //类似于写时复制
            //每个线程拿到的这个值，都是原来值的一份拷贝
            this.smsInfoMap = deepCopy(originSmsInfoMap);
            this.putAll(originSmsInfoMap);
        }

        @Override
        public SmsInfo put(Integer key, SmsInfo value) {
            smsInfoMap.put(key, value);
            return value;
        }

        @Override
        public SmsInfo get(Object key) {
            return smsInfoMap.get(key);
        }

        @Override
        public Set<Map.Entry<Integer, SmsInfo>> entrySet() {
            return smsInfoMap.entrySet();
        }

        public Map<Integer, SmsInfo> getSmsInfoMap() {
            return smsInfoMap;
        }

        public Map<Integer, SmsInfo> getOriginSmsInfoMap() {
            return this;
        }

        private Map<Integer, SmsInfo> deepCopy() {
            return deepCopy(this);
        }

        private Map<Integer, SmsInfo> deepCopy(Map<Integer, SmsInfo> smsInfoMap) {
            Map<Integer, SmsInfo> newMap = new TreeMap<>();
            for (Map.Entry<Integer, SmsInfo> entry : smsInfoMap.entrySet()) {
                newMap.put(entry.getKey(), new SmsInfo(entry.getValue()));
            }

            return newMap;
        }
    }
}
