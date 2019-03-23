package com.tony.unit.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/15
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private Integer id;
    private String name;

    private Optional<String> phone;

    public Person(Integer id,String name){
        this.id = id;
        this.name = name;

    }

}
