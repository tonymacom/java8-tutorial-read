package com.tony.unit.junit;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/23
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public class AccountService {


    private AccountManager accountManager;

    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;

    }

    public void transfer(Integer sendUserId, Integer beneficiaryUserId,Double amount){
        Account accountForUser = this.accountManager.findAccountForUser(sendUserId);
        Account accountForUser1 = this.accountManager.findAccountForUser(beneficiaryUserId);

        accountForUser.debit(amount);
        accountForUser1.credit(amount);

        this.accountManager.updateAccount(accountForUser);
        this.accountManager.updateAccount(accountForUser1);
    }



}
