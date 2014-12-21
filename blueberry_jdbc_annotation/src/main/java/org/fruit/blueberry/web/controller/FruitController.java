package org.fruit.blueberry.web.controller;

import org.fruit.blueberry.service.FruitService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by Stephen on 10/07/2014.
 */
@Controller(value = "fruitController")
public class FruitController extends BaseController{
    @Resource
    private FruitService fruitService;

}
