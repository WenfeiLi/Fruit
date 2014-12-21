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

import org.fruit.blueberry.util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * A controller to deal with requests from home page.
 * HomeController of fruit-blueberry.
 * Created on 2014/12/20 14:20.
 *
 * @author Afee Lee
 * @version V1.0.1
 * @see
 * @since JDK1.8
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController extends BaseController {

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    protected String home(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();

        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if ("lastAccessTime".equals(cookies[i].getName())) {
                long lastAccessTime = Long.parseLong(cookies[i].getValue());
                String date = DateUtil.formatDate(new Date(lastAccessTime), DateUtil.TIME_PATTERN);
                request.setAttribute("userLastAccessTime", date);
            }
        }

        Cookie cookie = new Cookie("lastAccessTime", System.currentTimeMillis() + "");
        cookie.setMaxAge(30 * 24 * 3600);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
        return "home/main";
    }
}
