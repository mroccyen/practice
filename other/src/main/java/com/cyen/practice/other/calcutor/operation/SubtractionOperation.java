package com.cyen.practice.other.calcutor.operation;

import com.cyen.practice.other.calcutor.Operation;

/**
 * @author qingsp
 * @date: 2019-05-16
 */
public class SubtractionOperation implements Operation {
    @Override
    public double calc(double v1, double v2) {
        return v1 - v2;
    }
}
