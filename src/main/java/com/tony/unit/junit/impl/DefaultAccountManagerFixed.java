package com.tony.unit.junit.impl;

import com.tony.unit.junit.Account;
import com.tony.unit.junit.AccountManager;
import com.tony.unit.junit.IConfiguration;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/26
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public class DefaultAccountManagerFixed implements AccountManager {

    @Autowired
    @Setter
    private IConfiguration configuration;

    @Override
    public Account findAccountForUser(Integer user_id) {
        String sql = configuration.getSql("FIND_ACCOUNT_FOR_USER");
        //do something
        return null;
    }
}
