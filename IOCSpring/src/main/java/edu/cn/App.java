package edu.cn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context;
        context =new ClassPathXmlApplicationContext("applicationContext.xml");
        Circle circle=context.getBean(Circle.class);
        circle.draw();
    }
}
