package com.cn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
       // Circle circle = context.getBean(Circle.class);
       // circle.draw();
        ApplicationContext context;
        context =new ClassPathXmlApplicationContext("applicationContext.xml");
        Circle circle=context.getBean(Circle.class);
        circle.draw();
    }
}
