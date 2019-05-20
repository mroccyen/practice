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
    private Stack<Double> values;

    /**
     * 用于存储符号
     */
    private Stack<Character> signs;

    /**
     * 优先级，0：代表+和-的级别，1：代表*和/的优先级
     */
    private int priority;

    public CalcCache() {
        this.values = new Stack<>();
        this.signs = new Stack<>();
        this.priority = -1;
    }

    public Stack<Double> getValues() {
        return values;
    }

    public Stack<Character> getSigns() {
        return signs;
    }
}
