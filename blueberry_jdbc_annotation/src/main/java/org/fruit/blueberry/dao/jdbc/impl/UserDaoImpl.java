package org.fruit.blueberry.dao.jdbc.impl;

import org.apache.commons.lang.StringUtils;
import org.fruit.blueberry.dao.jdbc.UserDao;
import org.fruit.blueberry.dto.condition.UserCondition;
import org.fruit.blueberry.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 * Created by AFei on 2014/7/21.
 */
@Repository(value = "userDao")
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {
    @Override
    public long save(User user) {
        String sql = "INSERT INTO user(username,password,nickname,birthday,email,create_time) VALUES(?,?,?,?,?,?)";
        return JdbcHelper.save(getJdbcTemplate(), sql,
                user.getUsername(), user.getPassword(),
                user.getNickname(), user.getBirthday(),
                user.getEmail(), user.getCreateTime());
    }

    @Override
    public User find(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        Object[] params = new Object[]{username, password};
        int[] types = new int[]{Types.VARCHAR, Types.VARCHAR};
        List<User> users = getJdbcTemplate().query(sql, params, types, new UserMapper());
        User user = null;

        if (users != null && users.size() > 0) {
            user = users.get(0);
        }

        return user;
    }

    @Override
    public boolean isUserExist(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        Object[] params = new Object[]{username};
        int[] types = new int[]{Types.VARCHAR};
        List<User> users = getJdbcTemplate().query(sql, params, types, new UserMapper());

        if (users != null && users.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<User> findByCondition(UserCondition condition) {
        StringBuilder sql = new StringBuilder("SELECT * FROM user WHERE 1=1");

        if (condition.getId() != null) {
            sql.append(" AND id=").append(condition.getId());
        }

        if (StringUtils.isNotEmpty(condition.getUsername())) {
            sql.append(" AND username='").append(condition.getUsername()).append("'");
        }

        if (StringUtils.isNotEmpty(condition.getNickname())) {
            sql.append(" AND nickname='").append(condition.getNickname()).append("'");
        }

        if (StringUtils.isNotEmpty(condition.getBirthday())) {
            sql.append(" AND birthday='").append(condition.getBirthday()).append("'");
        }

        if (StringUtils.isNotEmpty(condition.getEmail())) {
            sql.append(" AND email='").append(condition.getEmail()).append("'");
        }

        List<User> users = getJdbcTemplate().query(sql.toString(), new UserMapper());
        return users;
    }

    @Override
    public int delete(String username) {
        String sql = "DELETE FROM user WHERE username=?";
        return JdbcHelper.executeSql(getJdbcTemplate(), sql, username);
    }

    @Override
    public int update(User user) {
        String sql = "UPDATE user SET nickname=?,birthday=?,email=?,update_time=? WHERE username=?";
        return JdbcHelper.executeSql(getJdbcTemplate(), sql,
                user.getNickname(), user.getBirthday(), user.getEmail(), user.getUpdateTime(), user.getUsername());
    }

    private class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setNickname(rs.getString("nickname"));
            user.setPassword(rs.getString("password"));
            user.setBirthday(rs.getDate("birthday"));
            user.setEmail(rs.getString("email"));
            return user;
        }
    }
}
