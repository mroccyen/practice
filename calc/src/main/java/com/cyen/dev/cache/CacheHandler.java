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
    private StringBuffer dotValue = new StringBuffer();

    @Override
    public double calc(String st) {
        for (int i = 0; i < st.toCharArray().length; i++) {
            //右括号
            if (st.charAt(i) == OperationType.RPARENTHESIS.getSign()) {

            } else {
                char s = st.charAt(i);
                if (OperationType.isAddAndSub(s)
                        || OperationType.isMulAndDiv(s)) {
                    if (dotValue.capacity() != 0) {
                        calcCache.getValues().add(Double.valueOf(dotValue.toString()));
                    }
                    calcCache.getSigns().add(s);
                    dotValue = new StringBuffer();
                } else {
                    dotValue.append(s);
                }
            }
        }
        return 0;
    }
}
