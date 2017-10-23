package com.book.controller;

import com.book.po.Bookta;
import com.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 周太宇 on 2017/9/26.
 */
@Controller
public class BookAction {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/selectAll")
    public @ResponseBody
    List<Bookta> selectAll(HttpServletRequest request) throws Exception {
        List<Bookta> teacherList = bookService.findAll();
        return teacherList;
    }

    @RequestMapping(value = "/first")
    public String first(HttpServletRequest request) throws Exception {
        return "BookList";
    }
}
