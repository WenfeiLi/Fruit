package org.fruit.blueberry.dao.jdbc;

import org.fruit.blueberry.dto.condition.UserCondition;
import org.fruit.blueberry.model.User;

import java.util.List;

/**
 * Created by AFei on 2014/7/26.
 */
public interface UserDao extends JdbcDao {
    long save(User user);

    boolean isUserExist(String username);

    User find(String username, String password);

    List<User> findByCondition(UserCondition condition);

    int delete(String username);

    int update(User user);
}
