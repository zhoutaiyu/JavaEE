package com.rollBook.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rollBook.service.AdminService;
import com.rollBook.service.StudentService;
/**
 * 关于密码修改的action
 * @author 周太宇
 * @time 2017年8月19日
 */
import com.rollBook.service.TeacherService;

@Controller
public class updatePwdAction {
	@Autowired
	StudentService studentService;
	@Autowired
	TeacherService teacherService;
	@Autowired
	AdminService adminService;

	/**
	 * 跳转至修改密码页面
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ToupdatepwdPage")
	public String ToupdatepwdPage(Model model) throws Exception {

		return "/updatePwd";
	}

	@RequestMapping(value = "updatePwd", method = RequestMethod.POST)
	public String updatePwd(@RequestParam("oldPwd") String oldPwd, @RequestParam("newPwd") String newPwd,
			HttpSession session, HttpServletRequest request) throws Exception {
		String result = null;
		String  identity= (String) session.getAttribute("identity");
		// 如果是学生
		if ("stu".equalsIgnoreCase(identity)) {
			result = studentService.updatePwd(oldPwd, newPwd, session);
		}
		// 如果是老师
		if ("tec".equalsIgnoreCase(identity)) {
			result = teacherService.updatePwd(oldPwd, newPwd, session);
		}
		//如果是管理员
		if ("adm".equalsIgnoreCase(identity)) {
			result = adminService.updatePwd(oldPwd, newPwd, session);
		}
		// 回显
		request.setAttribute("result", result);
		request.setAttribute("oldPwd", oldPwd);
		request.setAttribute("newPwd", newPwd);
		return "/updatePwd";
	}

}
