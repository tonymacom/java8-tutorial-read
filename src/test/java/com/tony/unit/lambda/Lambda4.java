package com.tony.unit.lambda;

import org.junit.Test;

import java.util.function.Function;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/20
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public class Lambda4 {

    int outerNum;

    @Test
    public void test() {


        Function<Integer, String> function = from-> {
            outerNum = 12;
            return String.valueOf(from);
        };

        System.out.println(function.apply(11));
        System.out.println(outerNum);

    }




}
