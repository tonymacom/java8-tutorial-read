package com.tony.unit.lambda;

import org.junit.Test;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/23
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public class ShareDemo {

    @Test
    public void testConsumer() {

        Stream.of("A","B").forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

//        Optional.of(null).ifPresent(str-> System.out.println(str));

    }



}
