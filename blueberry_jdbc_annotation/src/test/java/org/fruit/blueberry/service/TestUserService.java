package org.fruit.blueberry.service;

import org.fruit.blueberry.exception.UserExistException;
import org.fruit.blueberry.model.User;
import org.fruit.blueberry.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Stephen on 9/1/2014.
 */
public class TestUserService {
    @Test
    public void testRegister() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
        UserService userService = context.getBean("userService", UserServiceImpl.class);
        User user = new User();
        user.setUsername("afei");
        user.setPassword("abc");
        try {
            userService.register(user);
        } catch (UserExistException e) {
            e.printStackTrace();
        }
    }

}
