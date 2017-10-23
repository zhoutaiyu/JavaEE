package com.book.service.impl;

import com.book.mapper.BooktaMapper;
import com.book.po.Bookta;
import com.book.po.BooktaExample;
import com.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.book.po.BooktaExample.Criteria;
import java.awt.print.Book;
import java.util.List;

/**
 * Created by 周太宇 on 2017/9/26.
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BooktaMapper booktaMapper;

    @Override
    public List<Bookta> findAll() {
        BooktaExample booktaExample =new BooktaExample();
        Criteria criteria=booktaExample.createCriteria();
        List<Bookta> list=booktaMapper.selectByExample(booktaExample);

        return null;
    }
}
