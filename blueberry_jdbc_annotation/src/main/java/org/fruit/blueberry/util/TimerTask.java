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
package org.fruit.blueberry.util;

import org.apache.commons.pool.ObjectPool;

/**
 * Commons here.
 * TimerTask of fruit-blueberry.
 * Created on 2014/12/20 21:28.
 *
 * @author Afee Lee
 * @version V1.0.1
 * @see
 * @since JDK1.8
 */
public class TimerTask extends Thread{
    private ObjectPool pool;
    private long sleepTime;

    public TimerTask(ObjectPool pool, long sleepTime){
        this.pool = pool;
        this.sleepTime = sleepTime;
    }

    public void run(){
        while (true){
            try {
                sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                pool.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.gc();
        }
    }
}
