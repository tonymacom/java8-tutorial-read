package com.tony.unit.stream;

import org.junit.Test;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/21
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public class Stream3 {

    //初始化原始类型流
    @Test
    public void test() {

        // 区间从0到9
        IntStream.range(0, 10).forEach(System.out::print);
        System.out.println();

        // 区间0到10
        IntStream.rangeClosed(0, 10).forEach(System.out::print);

    }

    //流类型与原始类型流转换
    @Test
    public void test2() {

        DoubleStream doubleStream = DoubleStream.of(0.1,0.2,0.3,0.8);
        // DoubleStream转Stream<Double>
        Stream<Double> stream = doubleStream.boxed();

        //Stream<Double> 转DoubleStream
        doubleStream = stream.mapToDouble(Double::new);

    }




}
