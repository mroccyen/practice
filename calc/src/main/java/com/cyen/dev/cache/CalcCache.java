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
        this.values = new Stack<Double>();
        this.signs = new Stack<Character>();
        this.priority = -1;
    }

    public int valuesSize() {
        return this.values.size();
    }

    public boolean signsEmpty() {
        return this.signs.empty();
    }

    public Double pushValue(Double v) {
        return this.values.push(v);
    }

    public Double popValue() {
        return this.values.pop();
    }

    public Character pushSign(Character c) {
        return this.signs.push(c);
    }

    public Character popSign() {
        return this.signs.pop();
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "栈值{" +
                "values=" + values +
                ", signs=" + signs +
                '}';
    }

    /**
     * 检查优先级是否符合计算
     *
     * @param currentPriority 当前优先级
     * @return
     */
    public boolean checkPriority(int currentPriority) {
        if (priority == -1) {
            return false;
        }
        return this.priority == 1 && currentPriority == 0;
    }
}
