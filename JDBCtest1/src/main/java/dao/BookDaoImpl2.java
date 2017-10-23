package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import pojo.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 周太宇 on 2017/10/23.
 */

/**
 * 使用NamedParameterJdbcTemplate
 */
@Repository
public class BookDaoImpl2 implements Bookdao {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public int add(Book book) {
        return 0;
    }

    @Override
    public int delete(String isbn) {
        return 0;
    }

    @Override
    public int updatePrice(String isbn, double price) {
        //?改为了名字的方式，后面字段赋值时可以使用名字赋值，此时顺序可变化。
        String sql="update bookta set price=:price where isbn=:isbn";
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("price",price);
        map.put("isbn",isbn);
        return template.update(sql,map);
    }

    @Override
    public List<Book> findAll() {
        String sql="select * from bookta";
        return template.query(sql,new BookMapper());
    }

    @Override
    public int amount() {
        return 0;
    }
}
