package com.tony.unit.stream;

import com.tony.unit.lambda.Person;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/21
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public class Stream1 {

    @Test(expected = IllegalStateException.class)
    public void test() {
        Stream<String> stringStream = Stream.of("a","b","c","d");
        stringStream.forEach(System.out::print);
        stringStream.forEach(System.out::print);
    }

    /**
     * 所有打印均在----之后
     */
    @Test
    public void testDelay() {
        Stream<Person> personStream = Stream.of(new Person(1,"tony"),new Person(2,"curry"),new Person(3,"goudan"));

        Predicate<Person> personPredicate = person -> {
            System.out.println("do id filter");
            return person.getId() <=2;
        };

        Predicate<Person> personPredicate1 = person -> {
            System.out.println("do name fiter");
            return person.getName().equals("tony");
        };


        Stream<Person> personStream1 = personStream.filter(personPredicate).filter(personPredicate1);
        System.out.println("----");

        BinaryOperator<Person> binaryOperator = (person, person2) -> {
            person.setId(person.getId()+ person2.getId());
            person.setName(person.getName() + person2.getName());
            return person;
        };


        Person person = personStream1.reduce(binaryOperator).get();
        System.out.println(person);


    }

    @Test
    public void test3() {
        List<Person> list = Lists.list(new Person(1,"tony"),new Person(2,"curry"),new Person(3,"goudan"));

        Predicate<Person> personPredicate = person -> {
            System.out.println("do id filter");
            return person.getName().length() <=4;
        };

        Stream<Person> personStream1 = list.stream().filter(personPredicate);
        System.out.println("----");

        list.add(new Person(4,"hell"));
        System.out.println(personStream1.collect(Collectors.toList()));

    }

    @Test
    public void testCreate() {

        //Array创建Stream
        IntStream stream = Arrays.stream(new int[]{1, 2, 3, 4, 5});


        //通过集合创建流
        List<String> list = Arrays.asList("aa","bb");
        Stream<String> stream1 = list.stream();
        Stream<String> stream2 = list.parallelStream();

        //创建无限流
//        Stream.generate(()-> "number" + new Random().nextInt()).limit(100).forEach(System.out::println);

        Stream.iterate(0,x->x+1).limit(10).forEach(System.out::println);
        Stream.iterate("1",x -> String.valueOf(Integer.parseInt(x) + 1)).limit(10).forEach(System.out::println);

    }

    @Test
    public void testOperator() {

        List<Person> list = Arrays.asList(new Person(1,"tony"),new Person(2,"curry"), new Person(3,"thompson"));


        //map
        Stream<Integer> ids = list.stream().map(person -> person.getId());
        System.out.println("----");
        ids.forEach(System.out::println);

        //将目标流中的每一个元素拆解，并组合成一个流返回
        String [] arr1 = {"a1","b","c","d","e"};
        String [] arr2 = {"a1","a2","3","4","5"};

        Stream<String> stream = Stream.of(arr1,arr2).flatMap(arrays -> Arrays.stream(arrays)).distinct();
        stream.forEach(System.out::println);

        Stream.of("a","a2","a1","a3").sorted(Comparator.naturalOrder()).forEach(System.out::println);

    }

    @Test
    public void testOperator2() {
        String[] arr1 = {"a1","b1","c1","a2"};
        String[] arr2 = {"a2","b3","c2"};
        String[] arr3 = {"a3","b3","c3"};

        //limit
        Stream.iterate(1,x->x+1).limit(10).forEach(System.out::println);

        //skip
        Stream.iterate(1,x->x+1).skip(10).limit(10).forEach(System.out::println);

        //concat将两个Stream合并成一个Stream, distinct去重
        Stream.concat(Stream.of(arr1),Stream.of(arr2)).distinct().forEach(System.out::println);

    }

    @Test
    public void testOperator3() {
        String [] arr = {"b","ab","abc","abcd","abcde"};

        //max
        Stream.of(arr).max(Comparator.comparing(String::length)).ifPresent(System.out::println);
        //min
        Stream.of(arr).min(Comparator.comparing(String::length)).ifPresent(System.out::println);

        //count
        long count = Stream.of(arr).count();
        System.out.println(count);

        //findFirst
        System.out.println(Stream.of(arr).findFirst().get());

        //reduce
        System.out.println(Stream.of(arr).reduce((a,b)-> a + b).get());


    }

    //findAny , 并行计算效果明显
    @Test
    public void testOperator4() {
        String [] arr = {"b","ab","abc","abcd","abcde"};
        Stream.of(arr).parallel().filter(x->x.length()>3).findAny().ifPresent(System.out::println);

    }

    //AnyMatch, AllMatch
    @Test
    public void testAnyMatch() {
        String [] arr = {"b","ab","abc","abcd","abcde"};
        Boolean anyMatch = Stream.of(arr).anyMatch(x->x.startsWith("a"));
        System.out.println(anyMatch);
        Boolean allMatch = Stream.of(arr).allMatch(x->x.startsWith("a"));
        System.out.println(allMatch);

    }


}
