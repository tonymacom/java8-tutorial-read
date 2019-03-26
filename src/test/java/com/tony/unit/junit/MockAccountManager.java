package com.tony.unit.junit;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/23
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public class MockAccountManager implements AccountManager{

    Map<Integer, Account> accountMap = new HashMap<>();

    public void addCount(Integer user_id, Account account) {
        accountMap.put(user_id, account);
    }


    @Override
    public Account findAccountForUser(Integer user_id) {

        return accountMap.get(user_id);
    }

    @Override
    public void updateAccount(Account account) {

        //do something
    }
}