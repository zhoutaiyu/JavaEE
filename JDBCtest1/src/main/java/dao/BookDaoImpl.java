package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import pojo.Book;

import java.util.List;

/**
 * Created by 周太宇 on 2017/10/19.
 */
@Repository
public class BookDaoImpl implements Bookdao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Book book) {
        String sql = "insert into bookta values(?,?,?,?)";
        return jdbcTemplate.update(sql, book.getIsbn(), book.getTitle(), book.getType(), book.getPrice());
    }

    @Override
    public int delete(String isbn) {
        String sql = "delete from bookta where isbn=?";
        return jdbcTemplate.update(sql, isbn);
    }

    @Override
    public int updatePrice(String isbn, double price) {
        String sql = "update bookta set price=? where isbn=?";
        return jdbcTemplate.update(sql, price, isbn);
    }

    @Override
    public List<Book> findAll() {
        String sql = "select * from bookta";
        return jdbcTemplate.query(sql, new BookMapper());
    }

    @Override
    public int amount() {
        String sql = "select count(*) from bookta";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
