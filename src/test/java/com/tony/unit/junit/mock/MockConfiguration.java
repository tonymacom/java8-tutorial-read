package com.tony.unit.junit.mock;

import com.tony.unit.junit.IConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author www.yamibuy.com
 * @desc :
 * @date 2019/3/26
 * <b>版权所有：</b>版权所有(C) 2018，www.yamibuy.com<br>
 */
public class MockConfiguration implements IConfiguration {

    protected Map<String,String> sql = new HashMap<>();

    public void setSql(String key,String sql) {
        this.sql.put(key,sql);
    }

    @Override
    public String getSql(String key) {
        return sql.get(key);
    }

}
