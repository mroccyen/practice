package com.cyen.dev.calcutor;

/**
 * @author qingsp
 * @date: 2019-05-16
 */
public interface Operation {
    /**
     * 计算函数，需要子类进行重写
     *
     * @param v1 第一个值
     * @param v2 第二个值
     * @return 计算结果
     */
    double calc(double v1, double v2);
}
