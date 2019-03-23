package com.tony.unit.concurrent;

import com.tony.unit.lambda.Person;
import com.tony.unit.lambda.Person2;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.*;
import java.util.stream.IntStream;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/15
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public class Atomic {

    @Test
    public void testThreadIncre() {

        AtomicInteger sequence = new AtomicInteger(0);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        IntStream.range(0,1000).forEach(i -> executorService.submit(sequence::incrementAndGet)); // 创建1000个线程, 排队从executorService获取线程

        try {
            executorService.shutdown();
            executorService.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(sequence.get()); // 1000

    }

    @Test
    public void testArrayThreadSet() {
        int [] values = new int[] {1,2,3,4,5,6,7,8,9};
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(values);

        IntStream.range(0,1000).forEach(i -> executorService.submit(()->atomicIntegerArray.addAndGet(0,1)));

        try {
            executorService.shutdown();
            executorService.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(values)); // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        System.out.println(atomicIntegerArray.toString()); // [1001, 2, 3, 4, 5, 6, 7, 8, 9]

    }

    @Test
    public void testReference(){

        AtomicReference<Person> reference = new AtomicReference<>();

        Person person1 = new Person(1,"tony");
        reference.set(person1);

        IntStream.range(0,1000).forEach(i->reference.getAndSet(new Person(i,"arvin")));

        System.out.println(reference.get().toString()); //Person(id=999, name=arvin, phone=null)

    }


    @Test
    public void testUpdater(){

        AtomicIntegerFieldUpdater<Person2> updater = AtomicIntegerFieldUpdater.newUpdater(Person2.class, "id");

        Person2 person = new Person2();
        updater.incrementAndGet(person);
        System.out.println(updater.get(person));


    }

    @Test
    public void testLongAdder() {

        LongAdder longAdder = new LongAdder();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        IntStream.range(0,1000).forEach(i->executorService.submit(longAdder::increment));

        try {
            executorService.shutdown();
            executorService.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(longAdder.longValue());

    }



}
