package com.tony.unit.stream;

import com.tony.unit.lambda.Person;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/21
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public class Stream2 {

    @Test
    public void test() {

        Person[] peoples = new Person[100];
        IntStream.range(0,100).forEach(i->peoples[i] = new Person(i,"name"+i));

        // toList
        List<Person> lists = Arrays.stream(peoples).collect(Collectors.toList());
        System.out.println(lists);

        //toSet
        Set<Person> sets = Arrays.stream(peoples).collect(Collectors.toSet());
        System.out.println(sets);

        //toMap
        Map<Integer,Person> maps = Arrays.stream(peoples).collect(Collectors.toMap(Person::getId,person -> person));
        System.out.println(maps);

        //toArray
        Person[] toArray = Arrays.stream(peoples).toArray(Person[]::new);
        System.out.println(Arrays.toString(toArray));


        //to指定集合类型
        HashSet<Person> hashSet = Arrays.stream(peoples).collect(Collectors.toCollection(HashSet::new));
        System.out.println(hashSet);

        //统计
        IntSummaryStatistics summaryStatistics = Arrays.stream(peoples).collect(Collectors.summarizingInt(Person::getId));
        System.out.println(summaryStatistics);
    }

    @Test
    public void test2() {

        Person[] peoples = new Person[20];
        IntStream.range(0,20).forEach(i->peoples[i] = new Person(i%10,"name"+i));

        //groupingBy
        Map<Integer,List<Person>> map = Arrays.stream(peoples).collect(Collectors.groupingBy(Person::getId));
        System.out.println(map);

        //groupingBy返回的key,value指定类型
        Map<String,Set<Person>> map1 = Arrays.stream(peoples).collect(Collectors.groupingBy(person->String.valueOf(person.getId()),Collectors.toSet()));
        System.out.println(map1);

        //如果结果只有两类, partitioningBy性能更优
        Map<Boolean,List<Person>> map2 = Arrays.stream(peoples).collect(Collectors.partitioningBy(x->x.getId() < 5));
        System.out.println(map2);


    }

    //aggregation
    @Test
    public void test3() {

        // counting
        Person[] peoples = new Person[20];
        IntStream.range(0,20).forEach(i->peoples[i] = new Person(i%10,"name"+i));
        Map<Integer,Long> map = Arrays.stream(peoples).collect(Collectors.groupingBy(Person::getId,Collectors.counting()));
        System.out.println(map); //{0=2, 1=2, 2=2, 3=2, 4=2, 5=2, 6=2, 7=2, 8=2, 9=2}

        //summingInt
        Map<Integer,Integer> map1 = Arrays.stream(peoples).collect(Collectors.groupingBy(Person::getId,Collectors.summingInt(Person::getId)));
        System.out.println(map1); //{0=0, 1=2, 2=4, 3=6, 4=8, 5=10, 6=12, 7=14, 8=16, 9=18}

        //先groupingBy, 再统计summaringInt
        Map<Integer, IntSummaryStatistics> collect = Arrays.stream(peoples).collect(Collectors.groupingBy(Person::getId, Collectors.summarizingInt(Person::getId)));
        System.out.println(collect); //{0=IntSummaryStatistics{count=2, sum=0, min=0, average=0.000000, max=0}, 1=IntSummaryStatistics{count=2, sum=2, min=1, average=1.000000, max=1}, 2=IntSummaryStatistics{count=2, sum=4, min=2, average=2.000000, max=2}, 3=IntSummaryStatistics{count=2, sum=6, min=3, average=3.000000, max=3}, 4=IntSummaryStatistics{count=2, sum=8, min=4, average=4.000000, max=4}, 5=IntSummaryStatistics{count=2, sum=10, min=5, average=5.000000, max=5}, 6=IntSummaryStatistics{count=2, sum=12, min=6, average=6.000000, max=6}, 7=IntSummaryStatistics{count=2, sum=14, min=7, average=7.000000, max=7}, 8=IntSummaryStatistics{count=2, sum=16, min=8, average=8.000000, max=8}, 9=IntSummaryStatistics{count=2, sum=18, min=9, average=9.000000, max=9}}

        //maxBy : 先分组,然后取每一组的max数据(最后结果每个分组只有一条数据)
        //minBy : 先分组, 然后取每一组的min数据(最后结果每个分组只有一条数据)
        Map<Integer, Optional<Person>> collect1 = Arrays.stream(peoples).collect(Collectors.groupingBy(Person::getId, Collectors.maxBy(Comparator.comparing(Person::getName))));
        System.out.println(collect1); //{0=Optional[Person(id=0, name=name10, phone=null)], 1=Optional[Person(id=1, name=name11, phone=null)], 2=Optional[Person(id=2, name=name2, phone=null)], 3=Optional[Person(id=3, name=name3, phone=null)], 4=Optional[Person(id=4, name=name4, phone=null)], 5=Optional[Person(id=5, name=name5, phone=null)], 6=Optional[Person(id=6, name=name6, phone=null)], 7=Optional[Person(id=7, name=name7, phone=null)], 8=Optional[Person(id=8, name=name8, phone=null)], 9=Optional[Person(id=9, name=name9, phone=null)]}

        //mapping : 先分组, 然后取每组中每个元素的指定属性,形成指定类型(toList或者toSet).
        Map<Integer, List<Integer>> collect2 = Arrays.stream(peoples).collect(Collectors.groupingBy(Person::getId, Collectors.mapping(Person::getId, Collectors.toList())));
        System.out.println(collect2); //{0=[0, 0], 1=[1, 1], 2=[2, 2], 3=[3, 3], 4=[4, 4], 5=[5, 5], 6=[6, 6], 7=[7, 7], 8=[8, 8], 9=[9, 9]}


    }

}
