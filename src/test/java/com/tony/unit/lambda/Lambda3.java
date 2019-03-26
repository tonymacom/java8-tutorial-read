package com.tony.unit.lambda;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/15
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public class Lambda3 {

    @FunctionalInterface
    interface Fun{
        void foo();
    }

    @Test
    public void testConsumer(){

        List<Person> list = new ArrayList<>();

        Consumer<Person> consumer = person -> {
          System.out.println("++++++++");
          if(person.getName() == "李四"){
              list.add(person);
        }};

        consumer = consumer.andThen(person -> {list.removeIf(person1 -> person1.getId()<24);
            System.out.println("-------");
        });

        Stream.of(
                new Person(21,"张三"),
                new Person(22,"李四"),
                new Person(23,"王五"),
                new Person(24,"李四"),
                new Person(25,"李四"),
                new Person(26,"王麻子")

        ).forEach(consumer);

        System.out.println(list);

    }


    //Predicate 类
    @Test
    public void testPredicate() {

        Predicate<Integer> predicate1 = integer -> integer + 1 == 3;
        Predicate<Integer> predicate2 = integer -> integer * 2 == 4;

        List<Integer> list   = Lists.list(0,1,2);
        List<Integer> and = list.stream().filter(predicate1.and(predicate2)).collect(Collectors.toList());
        System.out.println(and); // [2]

        List<Integer> or = list.stream().filter(predicate1.or(predicate2)).collect(Collectors.toList());
        System.out.println(or); // [2]

        List<Integer> negate = list.stream().filter(predicate1.negate()).collect(Collectors.toList());
        System.out.println(negate);// [0,1]

    }

    //Function类
    @Test
    public void testFunction() {

        Function<String ,Person> function = name -> new Person(2,name);

        //compose输出是Stirng,输出也是String.
        function = function.compose(name -> name+ "_before");

        //andThen输出是person对象, 输出也是person对象
        function = function.andThen(person -> {
            person.setName(person.getName() + "_after");
            return person;
        });

        HashMap map = new HashMap<String,Person>();
        map.put("a", new Person(1, "tony"));
        Object person = map.computeIfAbsent("a",function);
        System.out.println(person);

        Object person2 = map.computeIfAbsent("curry",function);
        System.out.println(person2);

    }

    @Test
    public void testFunction2() {

        Function<String ,Person> function = name -> new Person(2,name);
        HashMap map = new HashMap<String,Person>();
        Object person2 = map.computeIfAbsent("curry",function);
        System.out.println(person2);

    }

    //Suppier类
    @Test
    public void testSupplier() {

        Supplier<Person> supplier = ()->{
            Person person = new Person(1,"tony");
            return person;
        };

        Object o = new ArrayList<>().stream().findFirst().orElseGet(supplier);
        System.out.println(o);

    }

    //UnaryOperator
    @Test
    public void testUnaryOperator(){

        UnaryOperator<Person> personRepalcer = person1 -> {
            person1.setName("All Replace");
            return person1;
        };

        List list = Lists.list(new Person(1,"tony"),new Person(2,"curry"));
        list.replaceAll(personRepalcer);

        System.out.println(list);

    }

    // BiConsumer
    @Test
    public void testBiConsumer(){

        BiConsumer<Person,Person> biConsumer = (person, person2) -> System.out.println(person.getId() + "_" + person2.getId());
        biConsumer.accept(new Person(1,"tony"),new Person(2,"curry"));

    }

    //Optional - empty
    @Test(expected = NoSuchElementException.class)
    public void testOptional() {
        Optional<Person> person = Optional.empty();
        System.out.println(person.get());
    }


    //Optional - of
    @Test(expected = NullPointerException.class)
    public void testOptional2(){
        String str = null;
        Optional.of(str);
    }

    //Optional - orElse
    @Test
    public void testOptional3() {

        Person person = null;
        String username = Optional.ofNullable(person).map(Person::getName).orElse("TONY");
        assertEquals("when person is null","TONY",username);

        person = new Person(1, "tony");
        username = Optional.ofNullable(person).map(Person::getName).orElse("TONY");
        assertEquals("person is not null", username, "tony");



    }

    //Optional - flatMap
    @Test
    public void testOptional4() {

        Person person = new Person(1, "tony");
        //Optional嵌套
        String phone = Optional.ofNullable(person).map(Person::getPhone).map(Optional::get).orElse("no phone");
        assertEquals("Optional<String> phone is null", "no phone", phone);

        person.setPhone(Optional.of("110"));
        phone = Optional.ofNullable(person).map(Person::getPhone).map(Optional::get).orElse("no phone");
        assertEquals("Optional<String> phone is not null", "110", phone);


        //使用flatMap来代替Optional嵌套直接返回一个扁平化的流,但值必须存在 ,否则会报NullPointerException
        phone = Optional.ofNullable(person).flatMap(Person::getPhone).orElse("no phone");
        assertEquals("Optional<String> phone is not null", "110", phone);

    }

    //Optional - flatMap null
    @Test(expected = NullPointerException.class)
    public void testOptional5() {

        Person person = new Person(1, "tony");

        String phone = Optional.ofNullable(person).flatMap(Person::getPhone).orElse("no phone");
    }

    //Optional - filter, ifPresent
    @Test
    public void testOptional6() {

        Optional<Person> person = Optional.of(new Person(1, "tony"));
        person.filter(person1 -> person1.getId() > 1).ifPresent( p -> System.out.println(p.getId()));
    }

    //Optional - get, orElse, orElseGet
    @Test
    public void testOptional7() {

        Optional<Person> person = Optional.of(new Person(1, "tony"));
        System.out.println(person.get());

        Person person1 = new Person(1,"tony");
        Integer id = Optional.ofNullable(person1).map(Person::getId).orElse(22);
        assertEquals("person1 is not null", 1 , (Object)id);

        //虽然匹配到了phone有值,但依然会执行orElse中的方法
        person1 = new Person(1,"tony", Optional.of("120"));
        String phone = Optional.ofNullable(person1).map(Person::getPhone).orElse(getPhone()).get();
        assertEquals("person1 is null", "120", phone);
        //只有没有匹配到的时候才会执行orElseGet里面的语句, 如果匹配到了phone的值, 则不会执行orElseGet里的方法
        String phone2 = Optional.ofNullable(person1).map(Person::getPhone).orElseGet(()->getPhone()).get();
        assertEquals("person1 is null", "120", phone2);

    }

    private Optional<String> getPhone() {
        System.out.println("do this");
        return Optional.of("110");

    }

    //Optional - orElseThrow
    @Test(expected = NullPointerException.class)
    public void testOptional8() {

        Person person = new Person(1, "tony");

        String phone  = Optional.ofNullable(person).map(Person::getPhone).orElseThrow(NullPointerException::new).get();

    }

    @Test
    public void testCallable() throws Exception{

        Callable callable = UUID::randomUUID;
        Future<UUID> a = Executors.newFixedThreadPool(1).submit(callable);
        System.out.println(a.get());
    }


}
