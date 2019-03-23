package com.tony.unit.lambda;

import org.junit.Test;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/15
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public class Lambda2 {


    @FunctionalInterface
    public interface Converter<F,T>{
        T convert(F from);
    }

    class SomeThing{
        String startsWith(String e){
            return String.valueOf(e.charAt(0));
        }
    }

    interface PersonFactory<P extends Person> {
        P create(Integer id, String lastName);
    }

    @Test
    public void test() {
        Converter<String,Integer> converter = from -> Integer.valueOf(from);
        Integer convert1 = converter.convert("123");
        System.out.println(convert1);

        Converter<String, Integer> converter2 = Integer::valueOf;
        Integer convert2 = converter2.convert("345");
        System.out.println(convert2);

        // 将SomeThing 对象定义在lambda表达式里面
        SomeThing something1 = new SomeThing();
        Converter<String,String> converter3 = from -> something1.startsWith(from);
        String convert3 = converter3.convert("ABC");
        System.out.println(convert3);

        //将SomeThing 对象定义在外面
        SomeThing something = new SomeThing();
        Converter<String,String> converter4 = something::startsWith;

        String convert4 = converter4.convert("ABC");
        System.out.println(convert4);

        // 引用构造函数.
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create(1,"tony");
        System.out.println(person.toString());

    }


}
