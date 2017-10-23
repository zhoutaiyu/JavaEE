package dao;

import pojo.Book;

import java.util.List;


/**
 * Created by 周太宇 on 2017/10/19.
 */
public interface Bookdao {
    public int add(Book book);
    public int delete(String isbn);
    public int updatePrice(String isbn,double price);
    public List<Book> findAll();
    public int amount();
}
