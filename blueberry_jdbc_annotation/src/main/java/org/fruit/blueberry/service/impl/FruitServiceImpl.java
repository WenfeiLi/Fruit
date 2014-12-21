package org.fruit.blueberry.service.impl;

import org.fruit.blueberry.dao.jdbc.FruitDao;
import org.fruit.blueberry.service.FruitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Stephen on 10/07/2014.
 */
@Service(value = "fruitService")
public class FruitServiceImpl implements FruitService {
    @Resource
    private FruitDao fruitDao;

}
