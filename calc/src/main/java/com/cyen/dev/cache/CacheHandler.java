package com.cyen.dev.cache;

import com.cyen.dev.CalcHandler;
import com.cyen.dev.OperationType;

/**
 * 进行解析处理
 *
 * @author qingsp
 * @date: 2019-05-16
 */
public class CacheHandler implements CalcHandler {

    private CalcCache calcCache = new CalcCache();

    @Override
    public double calc(String st) {
        for (int i = 0; i < st.toCharArray().length; i++) {
            char s = st.charAt(i);
            if (OperationType.isAddAndSub(s) || OperationType.isMulAndDiv(s)) {
                calcCache.getSigns().push(s);
            } else {
                calcCache.getValues().push(Double.valueOf(String.valueOf(s)));
            }
        }
        return 0;
    }

    public CalcCache getCalcCache() {
        return calcCache;
    }
}