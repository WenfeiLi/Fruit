package org.fruit.blueberry.dao;

import org.fruit.blueberry.dao.jdbc.FruitDao;
import org.fruit.blueberry.dao.jdbc.impl.FruitDaoImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Stephen on 9/1/2014.
 */
public class TestFruitDao {
    @Test
    public void testGetBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
        FruitDao fruitDao1 = context.getBean("fruitDao", FruitDaoImpl.class);
//        Fruit fruit = fruitDao1.find(Fruit.class, 1L);
//        System.out.println(JsonUtil.toJson(fruit));
    }

}
