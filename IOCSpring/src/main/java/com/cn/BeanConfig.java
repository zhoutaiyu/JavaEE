package com.cn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 周太宇 on 2017/10/9.
 */
@Configuration
public class BeanConfig {
    @Bean
    public Point getPoint() {
        return new Point(2, 3);
    }

    @Bean
    public Circle getCircle() {
        return new Circle(3, getPoint());
    }
}
