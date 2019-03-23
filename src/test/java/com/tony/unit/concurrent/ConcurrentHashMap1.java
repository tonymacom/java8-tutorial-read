package com.tony.unit.concurrent;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/20
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public class ConcurrentHashMap1 {

    // forEach操作, 第一个参数为并行数, 若要在单线程里循环操作,该值设置为Long.MAX_VALUE
    @Test
    public void test(){
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("foo","bar");
        map.putIfAbsent("han","solo");
        map.putIfAbsent("r2","d2");
        map.putIfAbsent("c3","p0");
        IntStream.range(0,1000).forEach(i-> map.putIfAbsent("c"+i,"ABC"));


        map.forEach(1, (key, value) -> System.out.printf("key: %s; value: %s; thread: %s\n", key, value, Thread.currentThread().getName()));
        System.out.println(map.mappingCount());
    }

    //search操作, searchValue操作.
    @Test
    public void testSearch(){

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("foo", "bar");
        map.putIfAbsent("han", "solo");
        map.putIfAbsent("r2", "d2");
        map.putIfAbsent("c3", "p0");

        System.out.println("\nsearch()\n");

        String result1 = map.search(1,(key,value) ->{
            System.out.println(Thread.currentThread().getName());
            if (key.equals("foo") && value.equals("bar")) {
                return "foobar";
            }
            return null;
        });

        System.out.println(result1);

        String result2 = map.searchValues(1, value->{
            System.out.println(Thread.currentThread().getName());
            if(value.length()>3){
                return value;
            }
            return null;
        });
        System.out.println(result2);


    }

    //reduce操作, 先按照key=value输出字符串, 再通过逗号将字符串拼接.
    @Test
    public void testReduce(){

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("foo", "bar");
        map.putIfAbsent("han", "solo");
        map.putIfAbsent("r2", "d2");
        map.putIfAbsent("c3", "p0");

        String reduced = map.reduce(1, (key, value) -> key + "=" + value, (s1, s2) -> s1 + "," + s2);
        System.out.println(reduced);

    }


}
