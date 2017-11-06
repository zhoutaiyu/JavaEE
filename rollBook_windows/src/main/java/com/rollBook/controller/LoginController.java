package com.rollBook.controller;

import com.rollBook.po.Admin;
import com.rollBook.po.Student;
import com.rollBook.po.Teacher;
import com.rollBook.service.AdminService;
import com.rollBook.service.StudentService;
import com.rollBook.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * Title: LoginController
 * </p>
 * <p>
 * Description: 登陆和退出
 * </p>
 * <p>
 * Company: www.itcast.com
 * </p>
 *
 * @author 传智.燕青
 * @version 1.0
 * @date 2015-3-22下午4:43:26
 */
@Controller
public class LoginController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private AdminService adminService;

    // 用户登陆提交方法

    /**
     * <p>
     * Title: login
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param session
     * @param randomcode 输入的验证码
     * @param usercode   用户账号
     * @param password   用户密码
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    public String login(HttpSession session, HttpServletRequest request, String usercode,
                        String password, String select) throws Exception {

        // 校验验证码，防止恶性攻击
        //String randomcode,
        // 从session获取正确验证码
        //String validateCode = (String) session.getAttribute("validateCode");

        // 输入的验证和session中的验证进行对比,暂时不需要比
        // if (!randomcode.equals(validateCode)) {
        // request.setAttribute("msg","验证码输入错误");
        // return "/login";
        // }
        if (select.equals("stu")) {
            // 调用service校验用户账号和密码的正确性
            Student activeUser = studentService.authenticat(usercode, password);
            // 如果service校验通过，将用户身份记录到session
            session.setAttribute("activeUser", activeUser);
            // 如何是学生，设置一个全局属性，方便修改密码时分析
            session.setAttribute("identity", "stu");
        }
        if (select.equals("tec")) {
            Teacher activeUser = teacherService.authenticat(usercode, password);
            session.setAttribute("activeUser", activeUser);
            session.setAttribute("identity", "tec");
        }
        if (select.equals("admin")) {
            Admin activeUser = adminService.authenticat(usercode, password);
            session.setAttribute("activeUser", activeUser);
            session.setAttribute("identity", "adm");
        }
        // 重定向到商品查询页面
        return "/first";
    }

    // 用户退出
    @RequestMapping("/logout")
    public String logout(HttpSession session) throws Exception {

        // session失效
        session.invalidate();
        // 重定向到商品查询页面
        return "redirect:/first.action";

    }

}
