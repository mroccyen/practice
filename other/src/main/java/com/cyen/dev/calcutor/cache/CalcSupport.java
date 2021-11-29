package com.cyen.dev.calcutor.cache;

import com.cyen.dev.calcutor.OperationFactory;
import com.cyen.dev.calcutor.OperationType;

/**
 * 计算帮助类
 *
 * @author qingsp
 * @date 2019-05-16
 */
public class CalcSupport {

    /**
     * 计算括号内的值
     */
    public void calcParenthesis(CalcCache calcCache) {
        CalcCache c = new CalcCache();

        double v2 = calcCache.popValue();
        double v1 = calcCache.popValue();
        char o = calcCache.popSign();
        c.pushValue(v2);
        c.pushSign(o);
        c.pushValue(v1);

        char ch;
        while (!calcCache.signsEmpty() && !OperationType.isLeftParenthesis(ch = calcCache.popSign())) {
            c.pushSign(ch);
            double v3 = calcCache.popValue();
            c.pushValue(v3);
        }
        calcCache.pushValue(calcTempResult(c));
    }

    /**
     * 计算优先级高的值
     * 这里计算乘号和除号对应的值
     */
    public void calcPriority(CalcCache calcCache) {
        CalcCache c = new CalcCache();

        double v2 = calcCache.popValue();
        char o = calcCache.popSign();
        double v1 = calcCache.popValue();
        c.pushValue(v2);
        c.pushSign(o);
        c.pushValue(v1);

        char ch = 0;

        while (!calcCache.signsEmpty() && (OperationType.isMul(ch = calcCache.popSign()) || OperationType.isDiv(ch))) {
            c.pushSign(ch);
            double v3 = calcCache.popValue();
            c.pushValue(v3);
        }
        if (!OperationType.isMul(ch)
                && !OperationType.isDiv(ch)
                && ch != 0) {
            calcCache.pushSign(ch);
        }

        calcCache.pushValue(calcTempResult(c));
    }

    /**
     * 计算临时结果
     *
     * @return
     */
    public double calcTempResult(CalcCache calcCache) {
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
            return calcTempResult(calcCache);
        }
    }

    /**
     * 计算最终结果
     *
     * @return
     */
    public double calcResult(CalcCache calcCache) {
        //打印每次的信息
        System.out.println(calcCache.toString());
        //获取栈大小
        int size = calcCache.valuesSize();
        if (size == 1) {
            return calcCache.removeLastValue();
        } else if (size == 2) {
            double v1 = calcCache.removeLastValue();
            char o = calcCache.removeLastSign();
            double v2 = calcCache.removeLastValue();
            return OperationFactory.createOperation(o).calc(v1, v2);
        } else {
            double v1 = calcCache.removeLastValue();
            char o = calcCache.removeLastSign();
            double v2 = calcCache.removeLastValue();
            double v = OperationFactory.createOperation(o).calc(v1, v2);
            calcCache.addLastValue(v);
            //递归
            return calcResult(calcCache);
        }
    }
}
