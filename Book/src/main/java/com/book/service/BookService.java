package com.book.service;

import com.book.po.Bookta;

import java.awt.print.Book;
import java.util.List;

/**
 * Created by 周太宇 on 2017/9/26.
 */
public interface BookService {
    public List<Bookta> findAll();
}
