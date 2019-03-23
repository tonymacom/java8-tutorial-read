package com.tony.unit.lambda;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/15
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public class Integerface {

    interface Formula{
        double calculate(int a);

        default Double sqrt(int a){ return Math.sqrt(positive(a));}

        static int positive(int a) { return a>0 ? a : 0;}
    }

    @Test
    public void testFormula(){
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a);
            }
        };
        System.out.println(formula.calculate(100));
        System.out.println(formula.sqrt(-23));
        System.out.println(Formula.positive(-4));


        System.out.println(Math.sqrt(100));
    }


}
