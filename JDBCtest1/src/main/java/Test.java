import dao.BookDaoImpl;
import dao.Bookdao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 周太宇 on 2017/10/19.
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext context;
        context=new ClassPathXmlApplicationContext("applicationContext.xml");
        Bookdao bookdao =(Bookdao) context.getBean("bookDaoImpl");
        //测试添加书籍
        Book book1=new Book("2","钢铁上怎样炼成的","励志",29);
        System.out.println("add()方法一共影响"+bookdao.add(book1)+"行");
        System.out.println("************************************");
        //测试查询所有图书
        List<Book> books=new ArrayList<Book>();
        books =bookdao.findAll();
        Iterator<Book> iterator = books.iterator();
        while(iterator.hasNext()){
           System.out.println("Name:"+iterator.next().getTitle());
        }
        System.out.println("************************************");
        //查看图书数量
        System.out.println("图书数量一共有"+bookdao.amount()+"本");
        System.out.println("************************************");
        //修改图书价格
        book1.setPrice(32);
        int result =bookdao.updatePrice(book1.getIsbn(),book1.getPrice());
        System.out.println("updatePrice()方法一共影响"+result+"行");
        System.out.println("************************************");
        //删除图书
        int r=bookdao.delete(book1.getIsbn());
        System.out.println("delete()方法一共影响"+r+"行");
        System.out.println("************************************");
    }
}
