package org.fruit.blueberry.dao.jdbc.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * Created by Stephen on 10/02/2014.
 */
public class JdbcHelper {
    /**
     * execute a insert sql.
     *
     * @param sql
     * @return the key of the record inserted into the db.
     */
    public static long save(JdbcTemplate jdbcTemplate, String sql) {
        Object params = null;
        return save(jdbcTemplate, sql, params);
    }

    public static long save(JdbcTemplate jdbcTemplate, String sql, Object... values) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String tempSql = sql;
        final Object[] tempValues = values;
        PreparedStatementCreator creator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(tempSql, Statement.RETURN_GENERATED_KEYS);

                if (tempValues != null) {
                    for (int i = 1; i <= tempValues.length; i++) {
                        ps.setObject(i, tempValues[i - 1]);
                    }
                }

                return ps;
            }
        };

        jdbcTemplate.update(creator, keyHolder);
        return keyHolder.getKey().longValue();
    }

    /**
     * Issue a single SQL update operation (such as an insert, update or delete statement).
     *
     * @param sql
     * @return the number of rows affected
     */
    public static int executeSql(JdbcTemplate jdbcTemplate, String sql) {
        return jdbcTemplate.update(sql);
    }

    /**
     * Issue a single SQL update operation (such as an insert, update or delete statement)
     * via a prepared statement, binding the given arguments.
     *
     * @param sql
     * @param values
     * @return the number of rows affected
     */
    public static int executeSql(JdbcTemplate jdbcTemplate, String sql, Object... values) {
        return jdbcTemplate.update(sql, values);
    }

    public static List<Map<String, Object>> findBySql(JdbcTemplate jdbcTemplate, String sql, Object... values) {
        return jdbcTemplate.queryForList(sql, values);
    }

    public static <T> List<T> findBySql(JdbcTemplate jdbcTemplate, String sql, Class<T> type) {
        return jdbcTemplate.queryForList(sql, type);
    }

    public static <T> List<T> findBySql(JdbcTemplate jdbcTemplate, String sql, Class<T> type, Object... values) {
        return jdbcTemplate.queryForList(sql, type, values);
    }

    public static int getTotalCountBySql(JdbcTemplate jdbcTemplate, String sqlSuffix, Object... values) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*)" + sqlSuffix, Integer.class, values);
    }
}
