package com.alibaba.dubbo.impl;

import com.alibaba.dubbo.HelloService;

/**
 * Created by 周太宇 on 2018/3/14.
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String say(String name) {
        return "Hello," + name;
    }
}
