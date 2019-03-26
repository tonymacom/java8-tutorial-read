package com.tony.unit.junit;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/23
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
@Data
@AllArgsConstructor
public class Account {

    private Integer account_id;
    private Double balance;



    public void debit(Double amount){
        this.balance -= amount;
    }

    public void credit(Double amount) {
        this.balance += amount;
    }


}
