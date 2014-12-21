package org.fruit.blueberry.service.impl;

import org.fruit.blueberry.dao.jdbc.UserDao;
import org.fruit.blueberry.dto.condition.UserCondition;
import org.fruit.blueberry.exception.UserExistException;
import org.fruit.blueberry.model.User;
import org.fruit.blueberry.service.UserService;
import org.fruit.blueberry.util.EncryptionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by AFei on 2014/7/26.
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public long register(User user) throws UserExistException {
        boolean isExist = userDao.isUserExist(user.getUsername());

        if (isExist) {
            throw new UserExistException();
        }

        user.setPassword(EncryptionUtil.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public User login(String username, String password) {
        password = EncryptionUtil.encode(password);
        return userDao.find(username, password);
    }

    @Override
    public List<User> find(UserCondition condition) {
        if (condition != null) {
            return userDao.findByCondition(condition);
        } else {
            return null;
        }
    }

    @Override
    public int delete(String username) {
        return userDao.delete(username);
    }
}
