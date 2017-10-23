package dao;


import org.springframework.jdbc.core.RowMapper;
import pojo.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by FZH on 2017/10/13.
 */
public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setIsbn(resultSet.getString("isbn"));
        book.setTitle(resultSet.getString("title"));
        book.setType(resultSet.getString("type"));
        book.setPrice(resultSet.getDouble("price"));
        return book;
    }
}
