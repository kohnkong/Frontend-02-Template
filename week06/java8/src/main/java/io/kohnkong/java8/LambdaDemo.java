package io.kohnkong.java8;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月21日 10:00
 */
public class LambdaDemo<T extends Serializable & Comparable & Collection> {
    public static void main(String[] args) {
        LambdaDemo demo = new LambdaDemo();

        MathOperation op = new MathOperation() {
            @Override
            public Integer operation(int a, int b) {
                return 1;
            }
        };
        System.out.println(op.operation(1, 2));
        MathOperation op1 = (a, b) -> 1;
        System.out.println(op1.operation(1, 4));

        MathOperation op2 = new MathOperation<Integer>() {
            @Override
            public Integer operation(int a, int b) {
                return a + b;
            }
        };

        System.out.println(op2.operation(2, 3));

        MathOperation addition = (int a, int b) -> a + b;


    }

    interface MathOperation<T> {
        T operation(int a, int b);
    }
}
