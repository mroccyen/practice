package com.cyen.dev;

import com.cyen.dev.operation.AdditionOperation;
import com.cyen.dev.operation.DivisionOperation;
import com.cyen.dev.operation.MultiplicationOperation;
import com.cyen.dev.operation.SubtractionOperation;

/**
 * @author qingsp
 * @date: 2019-05-16
 */
public class OperationFactory {

    private OperationFactory() {

    }

    public static Operation createOperation(String type) {
        if (OperationType.ADDITION.getSign().equals(type)) {
            return new AdditionOperation();
        } else if (OperationType.SUBTRACTION.getSign().equals(type)) {
            return new SubtractionOperation();
        } else if (OperationType.MULTIPLICATION.getSign().equals(type)) {
            return new MultiplicationOperation();
        } else {
            return new DivisionOperation();
        }
    }
}
