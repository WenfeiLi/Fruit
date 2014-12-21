package org.fruit.blueberry.service;

import org.fruit.blueberry.dto.condition.UserCondition;
import org.fruit.blueberry.exception.UserExistException;
import org.fruit.blueberry.model.User;

import java.util.List;

/**
 * Created by AFei on 2014/7/26.
 */
public interface UserService {
    long register(User user) throws UserExistException;

    User login(String username, String password);

    List<User> find(UserCondition condition);

    int delete(String username);
}
