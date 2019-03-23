package com.tony.unit.lambda;

import org.junit.Test;

import java.util.HashMap;
import java.util.function.BiConsumer;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/21
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public class Lambda5 {


    @Test
    public void test() {

        HashMap<String, Integer> dummyValues = new HashMap<>();

        dummyValues.put("One",1);
        dummyValues.put("Two",2);
        dummyValues.put("Three",3);

        dummyValues.forEach((key,value)-> System.out.println(key + "," + value));


    }


}
