/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 1991, 2014, Fruit and/or its affiliates. All rights reserved.
 * FRUIT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * Licensed to the Fruit Organization Foundation (FOF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The FOF licenses this file to You under the Fruit License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.cnblogs.com/liwenfei/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fruit.blueberry.web.controller;

import org.apache.commons.lang.StringUtils;
import org.fruit.blueberry.dto.RegisterForm;
import org.fruit.blueberry.dto.condition.UserCondition;
import org.fruit.blueberry.dto.constants.ProjectConstants;
import org.fruit.blueberry.exception.UserExistException;
import org.fruit.blueberry.model.User;
import org.fruit.blueberry.service.UserService;
import org.fruit.blueberry.util.TokenUtil;
import org.fruit.blueberry.util.WebUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

/**
 * A controller to deal with users' operations.
 * UserController of fruit-blueberry.
 * Created on 2014/12/20 14:14.
 *
 * @author Afee Lee
 * @version V1.0.1
 * @see
 * @since JDK1.8
 */
@Controller(value = "userController")
@RequestMapping(value = "/account")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "account/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model,
                        @RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password) {
        username = StringUtils.trim(username);
        password = StringUtils.trim(password);

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            model.addAttribute("errorMsg", "用户名或密码不能为空！");
            model.addAttribute("username", username);
            return "account/login";
        }

        User user = userService.login(username, password);

        if (user != null) {
            request.getSession().setAttribute("user", user);
            return "redirect:/home.html";
        } else {
            model.addAttribute("errorMsg", "用户名或密码错误！");
            model.addAttribute("username", username);
            return "account/login";
        }
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    protected String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.removeAttribute("user");
        }

        return "home/main";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    protected String register(HttpServletRequest request) {
        String token = TokenUtil.generateToken();
        request.getSession().setAttribute("token", token);
        return "account/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    protected String register(HttpServletRequest request,
                              @RequestParam(required = false, value = "token") String clientToken) {
        RegisterForm form = WebUtil.request2Bean(request, RegisterForm.class);
        String serverToken = (String) request.getSession().getAttribute("token");

        if (!TokenUtil.isTokenValid(clientToken, serverToken)) {
            request.setAttribute("errorMsg", "请勿重复提交！");
            request.setAttribute("form", form);
            return "account/register";
        }

        if (!form.validate()) {
            request.setAttribute("form", form);
            return "account/register";
        }

        User user = new User();
        WebUtil.copyBean(form, user);

        try {
            userService.register(user);
            request.getSession().removeAttribute("token");
            List successMsg = new LinkedList();
            successMsg.add("恭喜，注册成功！");
            successMsg.add("<a href='" + ProjectConstants.CONTEXT_PATH_VALUE + "/home.html'>返回主页</a>");
            successMsg.add("<a href='" + ProjectConstants.CONTEXT_PATH_VALUE + "/account/login.html'>立即登录</a>");
            request.setAttribute("successMsg", successMsg);
            return "redirect:/common/public/messages";
        } catch (UserExistException e) {
            form.getErrors().put("username", "用户名已存在！");
            request.setAttribute("form", form);
            return "account/register";
        } catch (Exception e) {
            e.printStackTrace();
            List errorMsg = new LinkedList();
            String msg = "服务器出现未知异常，请稍后重试！<br/>";
            msg += "您可以：<a href='" + ProjectConstants.CONTEXT_PATH_VALUE
                    + "/account/register.html'>重新注册</a>或";
            msg += "<a href='" + ProjectConstants.CONTEXT_PATH_VALUE + "/home.html'>返回主页</a>";
            errorMsg.add(msg);
            request.setAttribute("errorMsg", errorMsg);
            return "redirect:/common/public/messages";
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, UserCondition condition) {
        List<User> users = userService.find(condition);
        model.addAttribute("users", users);
        return "account/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@ModelAttribute("registerForm") RegisterForm registerForm) {//开启modelDriven
        //model.addAttribute(new User());//等同于model.addAttribute("user", new User()); key为存储变量的类型并且首字母小写
        return "account/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Validated RegisterForm registerForm, BindingResult bindingResult, HttpServletRequest request) {
        //BindingResult一定要紧跟Validated，否则报错
        if (bindingResult.hasErrors()) {
            request.setAttribute("registerForm", registerForm);
            return "account/add";
        }

        User user = new User();
        WebUtil.copyBean(registerForm, user);
        try {
            userService.register(user);
        } catch (UserExistException e) {
            request.setAttribute("errorMsg", "此用户已存在！");
            return "account/add";
        }

        return "redirect:/account/list.html";
    }

    @RequestMapping(value = "/{username}/info", method = RequestMethod.GET)
    public String show(@PathVariable String username, Model model) {
        UserCondition userCondition = new UserCondition();
        userCondition.setUsername(username);
        List<User> users = userService.find(userCondition);

        if (users.size() > 0) {
            model.addAttribute("userInfo", users.get(0));
        }
        return "account/info";
    }

    @RequestMapping(value = "/{username}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable String username) {
        userService.delete(username);
        return "redirect:/account/list.html";
    }
}
