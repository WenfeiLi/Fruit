package org.fruit.blueberry.dao.jdbc.impl;

import org.fruit.blueberry.dao.jdbc.JdbcDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Stephen on 10/02/2014.
 */
@Repository(value = "baseDao")
public class JdbcDaoSupport implements JdbcDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

}
