package com.cyen.dev.cache;

import com.cyen.dev.CalcHandler;
import com.cyen.dev.OperationFactory;
import com.cyen.dev.OperationType;

/**
 * 进行解析处理
 *
 * @author qingsp
 * @date: 2019-05-16
 */
public class CacheHandler implements CalcHandler {

    private CalcCache calcCache = new CalcCache();

    public double calc(String st) {
        //最后如果为优先级高的符号
        //加上0可以将末尾的优先级高的符号进行计算
        st += "+0";
        //处理表达式
        for (int i = 0; i < st.toCharArray().length; i++) {
            char s = st.charAt(i);
            if (OperationType.isAdd(s)
                    || OperationType.isSub(s)) {
                if (calcCache.checkPriority(0)) {
                    //如果出现优先级低的符号
                    //计算之前符号优先级高的值
                    calc();
                }
                calcCache.setPriority(0);
                calcCache.pushSign(s);
            } else if (OperationType.isMul(s)
                    || OperationType.isDiv(s)) {
                calcCache.setPriority(1);
                calcCache.pushSign(s);
            } else {
                calcCache.pushValue(Double.valueOf(String.valueOf(s)));
            }
        }
        return calcResult(this.calcCache);
    }

    /**
     * 计算优先级高的值
     */
    private void calc() {
        CalcCache c = new CalcCache();

        double v2 = calcCache.popValue();
        char o = calcCache.popSign();
        double v1 = calcCache.popValue();
        c.pushValue(v2);
        c.pushSign(o);
        c.pushValue(v1);

        char ch = 0;
        while (!calcCache.signsEmpty()
                && (OperationType.isMul(ch = calcCache.popSign()) || OperationType.isDiv(ch))) {
            c.pushSign(ch);
            double v3 = calcCache.popValue();
            c.pushValue(v3);
        }
        if (!calcCache.signsEmpty() && ch != 0) {
            calcCache.pushSign(ch);
        }
        calcCache.pushValue(calcResult(c));
    }

    /**
     * 计算结果
     *
     * @return
     */
    private double calcResult(CalcCache calcCache) {
        //打印每次的信息
        System.out.println(calcCache.toString());
        //获取栈大小
        int size = calcCache.valuesSize();
        if (size == 1) {
            return calcCache.popValue();
        } else if (size == 2) {
            double v1 = calcCache.popValue();
            char o = calcCache.popSign();
            double v2 = calcCache.popValue();
            return OperationFactory.createOperation(o).calc(v1, v2);
        } else {
            double v1 = calcCache.popValue();
            char o = calcCache.popSign();
            double v2 = calcCache.popValue();
            double v = OperationFactory.createOperation(o).calc(v1, v2);
            calcCache.pushValue(v);
            //递归
            return calcResult(calcCache);
        }
    }
}