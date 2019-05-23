package com.cyen.dev.cache;

import java.util.LinkedList;

/**
 * @author qingsp
 * @date: 2019-05-16
 */
public class CalcCache {
    /**
     * 用于存储值
     */
    private LinkedList<Double> values;

    /**
     * 用于存储符号
     */
    private LinkedList<Character> signs;

    /**
     * 优先级，0：代表+和-的级别，1：代表*和/的优先级
     */
    private int priority;

    public CalcCache() {
        this.values = new LinkedList<Double>();
        this.signs = new LinkedList<Character>();
        this.priority = -1;
    }

    public int valuesSize() {
        return this.values.size();
    }

    public boolean signsEmpty() {
        return this.signs.size() == 0;
    }

    public void pushValue(Double v) {
        this.values.push(v);
    }

    public Double popValue() {
        return this.values.pop();
    }

    public void addLastValue(Double v) {
        this.values.addLast(v);
    }

    public Double removeLastValue() {
        return this.values.removeLast();
    }

    public void pushSign(Character c) {
        this.signs.push(c);
    }

    public Character popSign() {
        return this.signs.pop();
    }

    public Character removeLastSign() {
        return this.signs.removeLast();
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "栈 {" +
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
