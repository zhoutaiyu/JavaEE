package com.alibaba.dubbo;

/**
 * Created by 周太宇 on 2018/3/14.
 */
public interface HelloService {
    /**
     * 参数为名字
     *
     * @param name
     * @return
     */
    public String say(String name);
}
