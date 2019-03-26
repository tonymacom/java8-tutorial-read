package com.tony.unit.junit;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/23
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public interface AccountManager {

    public Account findAccountForUser(Integer user_id);

    default void updateAccount(Account account){}

}
