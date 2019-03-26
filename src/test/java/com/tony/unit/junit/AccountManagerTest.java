package com.tony.unit.junit;

import com.tony.unit.junit.impl.DefaultAccountManagerFixed;
import com.tony.unit.junit.mock.MockConfiguration;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/26
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public class AccountManagerTest {


    @Test
    public void testFindAccountForUser() {
        MockConfiguration mockAccountManager = new MockConfiguration();
        mockAccountManager.setSql("FIND_ACCOUNT_FOR_USER","select * from ...");

        DefaultAccountManagerFixed defaultAccountManagerFixed = new DefaultAccountManagerFixed();
        defaultAccountManagerFixed.setConfiguration(mockAccountManager);
        Account account = defaultAccountManagerFixed.findAccountForUser(1121);
        // asserts
    }

}