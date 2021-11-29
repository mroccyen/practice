package com.cyen.dev.calcutor.operation;

import com.cyen.dev.calcutor.Operation;

/**
 * @author qingsp
 * @date: 2019-05-16
 */
public class MultiplicationOperation implements Operation {
    @Override
    public double calc(double v1, double v2) {
        return v1 * v2;
    }
}
