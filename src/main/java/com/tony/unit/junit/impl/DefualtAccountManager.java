package com.tony.unit.junit.impl;

import com.tony.unit.junit.Account;
import com.tony.unit.junit.AccountManager;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/26
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public class DefualtAccountManager implements AccountManager {
    @Override
    public Account findAccountForUser(Integer user_id) {
        ResourceBundle bundle = PropertyResourceBundle.getBundle("technical");
        String sql = bundle.getString("FIND_ACCOUNT_FOR_USER");
        // do something
        return null;
    }
}
