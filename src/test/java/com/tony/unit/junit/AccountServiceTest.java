package com.tony.unit.junit;

import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/23
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public class AccountServiceTest {

    @Test
    public void testTransfer() {
        MockAccountManager accountManager = new MockAccountManager();
        accountManager.addCount(1,new Account(11111,1000D));
        accountManager.addCount(2,new Account(22222,1000D));
        AccountService service = new AccountService();
        service.setAccountManager(accountManager);

        service.transfer(1, 2, 500D);

        assertEquals("user 1 balance is 500", 500D, accountManager.findAccountForUser(1).getBalance(),0 );
        assertEquals("user 1 balance is 1500", 1500D, accountManager.findAccountForUser(2).getBalance(),0 );
    }


    @Test
    public void testTransfer2() {

        AccountManager mockAccountManager = createMock("mockAccountManager", AccountManager.class);


        Account senderAccount = new Account(11111, 1000D);
        Account beneficiaryAccount = new Account(22222, 1000D);

        // 由于transfer方法中调用了updateAccount方法, 所以必须先声明预期
        // 当方法返回类型为void时, 我们就在mock objects上调用该方法即表示声明预期.
        mockAccountManager.updateAccount(senderAccount);
        mockAccountManager.updateAccount(beneficiaryAccount);

        // 使用expect和andReturn方法来声明返回值不是void的方法预期.
        expect( mockAccountManager.findAccountForUser(11111)).andReturn(senderAccount);
        expect(mockAccountManager.findAccountForUser(22222)).andReturn(beneficiaryAccount);

        //声明完方法预期之后, 要使用replay方法来声明mock的object
        replay(mockAccountManager);

        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountManager);
        accountService.transfer(11111,22222,500D);

        assertEquals(500D,senderAccount.getBalance(),0);
        assertEquals(1500D,beneficiaryAccount.getBalance(),0);

        //验证mock的object是否被调用, 若没有被调用, 会抛出异常java.lang.AssertionError: Expectation failure on verify:
        verify(mockAccountManager);
    }


}