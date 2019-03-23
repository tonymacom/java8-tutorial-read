package com.tony.unit;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/21
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public abstract class Bird {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract int fly();
}

