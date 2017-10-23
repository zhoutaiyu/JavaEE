package edu.cn.dao;


import edu.cn.pojo.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 周太宇 on 2017/10/22.
 */
public class BookDaoImpl implements BookDao {
    private Connection con;
    private PreparedStatement statement;
    private ResultSet rs;
    private String sql;
    //测试sql注入
    private Statement s;

    /**
     * 查询所有图书
     * 步骤：1.获取连接
     * 2.写sql语句
     * 3.获取preparedstatement语句对象
     * 4.执行，获得结果集
     * xxxxx取记录赋值对象
     * 5.关闭资源
     */
    public List<Book> findAll() {
        con = LinkConnection.getConnection();
        sql = "select * from bookta";
        try {
            statement = con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Statement");
        }
        try {
            //执行不允许带参数
            rs = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ResultSet获取失败，可能是参数赋值出现了问题");
        }
        List<Book> books = new ArrayList<Book>();
        try {
            while (rs.next()) {
                Book book = new Book();
                book.setIsbn(rs.getString(1));
                book.setTitle(rs.getString(2));
                book.setType(rs.getString(3));
                book.setPrice(rs.getDouble(4));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CloseResources.close(con, statement, rs);
        return books;
    }

    /**
     * 更新图书价格
     *
     * @param isbn
     * @param price
     */

    public void update(String isbn, double price) {
        con = LinkConnection.getConnection();
        sql = "update bookta set price=? where isbn=?";
        try {
            statement = con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Statement");
        }
        try {
            statement.setDouble(1, price);
            statement.setString(2, isbn);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("可能是preparedStatement参数赋值类型不对应");
        }
        CloseResources.close(con, statement, rs);
    }

    public List<Book> findByType(String type) {
        con = LinkConnection.getConnection();
        sql = "select * from bookta where type='"+type+"'";
        try {
            s = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Statement");
        }
        try {
            rs = s.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ResultSet获取失败，可能是参数赋值出现了问题");
        }
        List<Book> books = new ArrayList<Book>();
        try {
            while (rs.next()) {
                Book book = new Book();
                book.setIsbn(rs.getString(1));
                book.setTitle(rs.getString(2));
                book.setType(rs.getString(3));
                book.setPrice(rs.getDouble(4));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CloseResources.close(con, s, rs);
        return books;
    }
}
