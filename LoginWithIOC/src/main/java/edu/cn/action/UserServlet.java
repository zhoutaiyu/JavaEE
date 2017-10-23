package edu.cn.action;

import edu.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 周太宇 on 2017/10/12.
 */

public class UserServlet extends HttpServlet {
    @Autowired
    private UserService userService;
    private WebApplicationContext springContext;

    /**
     * 原始servlet不支持spring管理bean,需要手动初始化容器
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        springContext= WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
        final AutowireCapableBeanFactory beanFactory=springContext.getAutowireCapableBeanFactory();
        beanFactory.autowireBean(this);
    }

    /**
     * 如果用户名存在，跳转到百度
     * 若不正确，跳转到网易
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        if (userService.login(username)){
            //网址前需要http://
            resp.sendRedirect("http://www.baidu.com");
        }
        else
            resp.sendRedirect("http://www.163.com");
    }
}
