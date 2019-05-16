package com.cyen.dev.cache;

import com.cyen.dev.CalcHandler;

/**
 * 进行解析处理，使用栈进行存储过程数据
 *
 * @author qingsp
 * @date: 2019-05-16
 */
public class CacheHandler implements CalcHandler {
    public double calc(String st) {
        for (int i = 0; i < st.toCharArray().length; i++) {
            Double.valueOf(st.charAt(i));
        }
        return 0;
    }
}
