package com.example.hdlitest.leetcode.stack;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;

/**
 *
 * 逆波兰表达式求值
 *
 *一般不考
 * @author luyi
 * @date 2023/7/2 14:49
 */
public class EvalRPN_150 {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String a:tokens){
            Calculator calculator = Calculator.getCalculator(a);
            if (calculator != null){
                IntBinaryOperator operator = calculator.getOperator();
                Integer right = stack.pop();
                Integer left = stack.pop();
                int i = operator.applyAsInt(left, right);
                stack.push(i);
            }else {
                stack.push(Integer.parseInt(a));
            }
        }
        return stack.peek();
    }


    enum Calculator{
        ADD("+",(a,b)-> a+b),
        SUBTRACT("-",(a,b)-> a-b),
        MULTIPLICATION("*",(a,b)-> a*b),
        DIVISION("/",(a,b)-> a/b);

        private String symbol;

        IntBinaryOperator operator;

        Calculator(String symbol, IntBinaryOperator operator) {
            this.symbol = symbol;
            this.operator = operator;
        }

        public String getSymbol() {
            return symbol;
        }

        public IntBinaryOperator getOperator() {
            return operator;
        }

        public static Calculator getCalculator(String symbol){
            if (symbol == null || symbol.equals("")){
                return null;
            }
            return Arrays.stream(values()).filter(c->c.getSymbol().equals(symbol)).findFirst().orElse(null);
        }
    }

}
