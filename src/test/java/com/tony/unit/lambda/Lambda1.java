package com.tony.unit.lambda;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/15
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public class Lambda1 {

    @Test
    public void test(){
        List<Person> persons = Lists.newArrayList(
                new Person(2,"curry"),
                new Person(1,"arvin"),
                new Person(3,"tony")
        );

        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });

        Collections.sort(persons,(Person o1,Person o2) -> {return o1.getId().compareTo(o2.getId());});

        Collections.sort(persons,(Person o1, Person o2) -> o1.getId().compareTo(o2.getId()));

        Collections.sort(persons,(o1,o2)->{return o1.getId().compareTo(o2.getId());});

        Collections.sort(persons,(o1, o2) -> o1.getId().compareTo(o2.getId()));

        Collections.sort(persons,Comparator.comparing(Person::getId));

        System.out.println(persons);

        List<String> names2 = Arrays.asList("peter", null, "anna", "mike", "xenia");
        names2.sort(Comparator.nullsLast(String::compareTo));
        System.out.println(names2);
        names2.sort(Comparator.nullsFirst(String::compareTo));
        System.out.println(names2);

        List<String> name3 = Arrays.asList("peter", "anna", "mike", "xenia");
        Optional.ofNullable(name3).ifPresent(list->list.sort(Comparator.naturalOrder()));
        System.out.println(name3);


    }

    @Test
    public void testNull(){
        List<Person> persons = Lists.newArrayList(
                new Person(2,"curry"),
                new Person(1,"arvin"),
                new Person(3,"tony"),
                null
        );

        persons.sort(Comparator.nullsLast(Comparator.comparing(Person::getId)));

        System.out.println(persons);

    }

    /**
     * ifPresent 只有在目标值不为空的情况下才会被执行.
     */
    @Test
    public void testIfPresent(){
        List<String> name3 = null;

        name3 = Arrays.asList("peter", "anna", "mike", "xenia");
        Optional.ofNullable(name3).ifPresent(nameList->nameList.sort(Comparator.naturalOrder()));

        System.out.println(name3);

    }


}
