package com.cyen.dev.calcutor.cache;

import com.cyen.dev.calcutor.CalcHandler;
import com.cyen.dev.calcutor.OperationType;

/**
 * 进行解析处理
 * 例如：4*3/6+(6-4)*2-7=-1、(6*1+2*3-1+5*2-1)-9=11、(6+4)*2/(1+3)=5、(6+4)+8/(1+3)=12、(6+4)*2/(1.1+2.9)=5
 *
 * @author qingsp
 * @date: 2019-05-16
 */
public class CacheHandler extends CalcSupport implements CalcHandler {

    private CalcCache calcCache = new CalcCache();
    /**
     * 对于小数点处理
     */
    private StringBuffer value = new StringBuffer();

    /**
     * 对于小数点处理，标志是否可以取值
     */
    private int acquire = 0;

    /**
     * 计算表达式值
     *
     * @param st 表达式
     * @return 计算结果
     */
    @Override
    public double calc(String st) {
        //最后如果为优先级高的符号
        //加上0可以将末尾的优先级高的符号进行计算
        st += "+0";
        System.out.println("计算表达式：" + st);
        //记录左括号前面的优先级
        int lastPriority = 0;
        //处理表达式
        for (int i = 0; i < st.toCharArray().length; i++) {
            char s = st.charAt(i);
            if (OperationType.isAdd(s) || OperationType.isSub(s)) {
                checkAndSetValue();
                //处理优先级的值
                handlePriority();
                calcCache.pushSign(s);
            } else if (OperationType.isMul(s) || OperationType.isDiv(s)) {
                checkAndSetValue();
                calcCache.setPriority(1);
                calcCache.pushSign(s);
            } else if (OperationType.isRightParenthesis(s)) {
                checkAndSetValue();
                //处理优先级的值
                handlePriority();
                //计算括号里面的值
                calcParenthesis(calcCache);
                //遇到右括号时，重置为左括号之前的优先级
                calcCache.setPriority(lastPriority);
            } else if (OperationType.isLeftParenthesis(s)) {
                checkAndSetValue();
                //遇到左括号时，记录下左括号之前的优先级
                lastPriority = calcCache.getPriority();
                //左括号重置优先级标志
                calcCache.setPriority(0);
                calcCache.pushSign(s);
            } else {
                value.append(s);
                acquire = 0;
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

    /**
     * 小数点处理
     */
    private void checkAndSetValue() {
        if (acquire == 0 && value.length() > 0) {
            calcCache.pushValue(Double.valueOf(value.toString()));
            acquire = 1;
            value = new StringBuffer();
        }
    }
}