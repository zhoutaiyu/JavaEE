package edu.cn;

import edu.cn.dao.BookDao;
import edu.cn.dao.BookDaoImpl;
import edu.cn.pojo.Book;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        BookDao bookDao = new BookDaoImpl();
        //测试findAll()方法
        List<Book> bookList = bookDao.findAll();

        for (int i = 0; i < bookList.size(); i++) {
            System.out.println(bookList.get(i).getTitle() + "价格为" + bookList.get(i).getPrice());
        }
        System.out.println("******************************************************");
        //测试updatePrice()方法
        bookDao.update("1", 32);
        System.out.println("修改完成，现在的书籍信息为：");
        bookList = bookDao.findAll();
        for (int i = 0; i < bookList.size(); i++) {
            System.out.println(bookList.get(i).getTitle() + "价格为" + bookList.get(i).getPrice());
        }
        System.out.println("******************************************************");
        System.out.println("测试sql注入");
        //测试sql注入，数据库中其实没有type="爱情"依然能查询数据库
        //select * from bookta where type='爱情' or '1=1'--'其他的限制条件';
        bookList=bookDao.findByType("爱情' or '1=1'--'");
        for (int i = 0; i < bookList.size(); i++) {
            System.out.println(bookList.get(i).getTitle() + "价格为" + bookList.get(i).getPrice());
        }


    }
}
