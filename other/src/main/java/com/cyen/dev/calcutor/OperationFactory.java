package com.cyen.dev.calcutor;

import com.cyen.dev.calcutor.operation.AdditionOperation;
import com.cyen.dev.calcutor.operation.DivisionOperation;
import com.cyen.dev.calcutor.operation.MultiplicationOperation;
import com.cyen.dev.calcutor.operation.SubtractionOperation;

/**
 * @author qingsp
 * @date: 2019-05-16
 */
public class OperationFactory {

    private OperationFactory() {

    }

    public static Operation createOperation(char type) {
        if (OperationType.ADDITION.getSign() == type) {
            return new AdditionOperation();
        } else if (OperationType.SUBTRACTION.getSign() == type) {
            return new SubtractionOperation();
        } else if (OperationType.MULTIPLICATION.getSign() == type) {
            return new MultiplicationOperation();
        } else {
            return new DivisionOperation();
        }
    }
}
