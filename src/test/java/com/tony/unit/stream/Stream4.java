package com.tony.unit.stream;

import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/21
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public class Stream4 {


    public void peek1(int x){
        System.out.println("peek1:" +Thread.currentThread().getName() + "-->" + x);
    }

    public void peek2(int x){
        System.out.println("peek2:" +Thread.currentThread().getName() + "-->" + x);
    }

    public void peek3(int x){
        System.out.println("peek3:" +Thread.currentThread().getName() + "-->" + x);
    }

    @Test
    public void test() {

        Stream<Integer> stream = IntStream.rangeClosed(1,100).boxed();
        stream.peek(this::peek1).filter(x -> x > 5)
                .peek(this::peek2).filter(x -> x<8)
                .peek(this::peek3)
                .forEach(System.out::print);
    }

    @Test
    public void testParallel(){
        Stream<Integer> stream = IntStream.rangeClosed(1,100).boxed();
        stream.parallel().peek(this::peek1).filter(x -> x>5)
                .peek(this::peek2).filter(x -> x<20)
                .peek(this::peek3).forEachOrdered(System.out::println);
    }

    //100000000个random数据中计算普通stream与parallel并行流的处理性能.
    @Test
    public void testParallel2() {

        Random random = new Random();

        List<Integer> list = Stream.generate(() -> random.nextInt(100)).limit(100000000).collect(Collectors.toList());

        long time1 = System.currentTimeMillis();
        list.stream().filter(x -> x>10 ).filter( x -> x< 80).count();
        long time2 = System.currentTimeMillis();
        list.stream().parallel().filter(x -> x>10 ).filter( x -> x< 80).count();
        long time3 = System.currentTimeMillis();
        System.out.println("time2-time1:"+ (time2-time1)); //time2-time1:961
        System.out.println("time3-time2:"+ (time3-time2)); //time3-time2:394



    }

    //100000000个random数据中计算普通stream与parallel并行流的distinct性能.
    @Test
    public void testParallel3() {
        Random random = new Random();

        List<Integer> list = Stream.generate(() -> random.nextInt(100)).limit(100000000).collect(Collectors.toList());

        long time1 = System.currentTimeMillis();
        list.stream().filter(x -> x>10 ).filter( x -> x< 800).distinct().collect(Collectors.toList());
        long time2 = System.currentTimeMillis();
        list.stream().parallel().filter(x -> x>10 ).filter( x -> x< 800).distinct().collect(Collectors.toList());
        long time3 = System.currentTimeMillis();
        System.out.println("time2-time1:"+ (time2-time1)); //time2-time1:925
        System.out.println("time3-time2:"+ (time3-time2)); //time3-time2:634
    }

    //100000000个random数据中计算普通stream与parallel并行流的sort性能.
    @Test
    public void testParallel4() {
        Random random = new Random();

        List<Integer> list = Stream.generate(() -> random.nextInt(100)).limit(100000000).collect(Collectors.toList());

        long time1 = System.currentTimeMillis();
        list.stream().filter(x -> x>10 ).filter( x -> x< 80).sorted().collect(Collectors.toList());
        long time2 = System.currentTimeMillis();
        list.stream().parallel().filter(x -> x>10 ).filter( x -> x< 80).sorted().collect(Collectors.toList());
        long time3 = System.currentTimeMillis();
        System.out.println("time2-time1:"+ (time2-time1)); //time2-time1:57026
        System.out.println("time3-time2:"+ (time3-time2)); //time3-time2:15010
    }

}
