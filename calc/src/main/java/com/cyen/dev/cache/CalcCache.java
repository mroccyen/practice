package com.cyen.dev.cache;

import java.util.Stack;

/**
 * @author qingsp
 * @date: 2019-05-16
 */
public class CalcCache {
    /**
     * 用于存储值
     */
    private Stack<Double> values = new Stack<Double>();

    /**
     * 用于存储符号
     */
    private Stack<String> signs = new Stack<String>();

    public Stack<Double> getValues() {
        return values;
    }

    public Stack<String> getSigns() {
        return signs;
    }
}
