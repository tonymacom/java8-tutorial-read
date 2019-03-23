package com.tony.unit;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/21
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public class Test {

    public void test(Bird bird) {
        System.out.println(bird.getName() + "能够飞 " + bird.fly() + "米");
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.test(new Bird() {

            public int fly() {
                return 10000;
            }

            public String getName() {
                return "大雁";
            }
        });
    }
}