package edu.cn.action;

import edu.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * Created by FZH on 2017/10/12.
 */
@Controller
public class UserServlet extends javax.servlet.http.HttpServlet {
    @Autowired
    private UserService userService;
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String username=request.getParameter("username");
        if(userService.login(username))
            response.sendRedirect("http://wwww.baidu.com");
        else
            response.sendRedirect("http://wwww.163.com");

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
