package com.cyen.dev.cache;

import com.cyen.dev.CalcHandler;
import com.cyen.dev.OperationType;

/**
 * 进行解析处理
 * 例如：4*3/6+(6-4)*2-7、(6*1+2*3-1+5*2-1)-9
 *
 * @author qingsp
 * @date: 2019-05-16
 */
public class CacheHandler extends CalcSupport implements CalcHandler {

    private CalcCache calcCache = new CalcCache();

    /**
     * 计算表达式值
     *
     * @param st 表达式
     * @return 计算结果
     */
    public double calc(String st) {
        //最后如果为优先级高的符号
        //加上0可以将末尾的优先级高的符号进行计算
        st += "+0";
        //处理表达式
        for (int i = 0; i < st.toCharArray().length; i++) {
            char s = st.charAt(i);
            if (OperationType.isAdd(s)
                    || OperationType.isSub(s)) {
                //处理优先级的值
                handlePriority();
                calcCache.pushSign(s);
            } else if (OperationType.isMul(s)
                    || OperationType.isDiv(s)) {
                calcCache.setPriority(1);
                calcCache.pushSign(s);
            } else if (OperationType.isRightParenthesis(s)) {
                //处理优先级的值
                handlePriority();
                //计算括号里面的值
                calcParenthesis(calcCache);
            } else if (OperationType.isLeftParenthesis(s)) {
                calcCache.pushSign(s);
            } else {
                calcCache.pushValue(Double.valueOf(String.valueOf(s)));
            }
        }
        //计算最终结果
        return calcResult(this.calcCache);
    }

    /**
     * 处理优先级高的值
     */
    private void handlePriority() {
        if (calcCache.checkPriority(0)) {
            //如果出现优先级低的符号
            //计算之前符号优先级高的值
            calcPriority(calcCache);
            calcCache.setPriority(0);
        }
    }
}