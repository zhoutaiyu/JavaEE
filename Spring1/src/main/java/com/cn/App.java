package com.cn;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("knight.xml");
        Knight knight = context.getBean(Knight.class);//取只有一种实现类的对象
        //Knight knight=(Knight) context.getBean("knight");//按id取对象实例
        knight.embarkOnQuest();
    }
}
