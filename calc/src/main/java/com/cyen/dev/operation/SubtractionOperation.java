package com.cyen.dev.operation;

import com.cyen.dev.Operation;

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
