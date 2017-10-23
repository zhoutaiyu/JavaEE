package edu.cn.dao;

import edu.cn.pojo.Book;

import java.util.List;

/**
 * Created by k01 on 2017/10/20.
 */
public interface BookDao {
    /**
     * 查询所有图书
     */
    public List<Book> findAll();

    /**
     * 添加图书
     * @param book
     */
    //public void add(Book book);

    /**
     * 按编号删除图书
     * @param isbn
     */
    //public void delete(String isbn);

    /**
     * 按编号修改图书价格
     *
     * @param isbn
     * @param price
     */
    public void update(String isbn, double price);

    /**
     * 测试sql注入
     *
     * @param type
     * @return
     */
    public List<Book> findByType(String type);
}
