import dao.BookDaoImpl2;
import dao.Bookdao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 周太宇 on 2017/10/23.
 */
public class test2 {
    public static void main(String[] args) {
        ApplicationContext context;
        context=new ClassPathXmlApplicationContext("applicationContext.xml");
        Bookdao bookdao =(Bookdao) context.getBean("bookDaoImpl2");
        //测试查询所有图书
        List<Book> books=new ArrayList<Book>();
        books =bookdao.findAll();
        Iterator<Book> iterator = books.iterator();
        while(iterator.hasNext()){
            System.out.println("Name:"+iterator.next().getTitle());
        }
        System.out.println("************************************");
        //修改图书价格
        int result =bookdao.updatePrice("1",38);
        System.out.println("updatePrice()方法一共影响"+result+"行");
        System.out.println("************************************");
        //测试查询所有图书
        books=new ArrayList<Book>();
        books =bookdao.findAll();
        iterator = books.iterator();
        while(iterator.hasNext()){
            System.out.println("Name:"+iterator.next().getTitle());
        }
        System.out.println("************************************");

    }
}
