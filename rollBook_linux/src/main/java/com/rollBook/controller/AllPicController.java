package com.rollBook.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rollBook.po.Student;
import com.rollBook.service.CourseService;
import com.rollBook.service.StudentService;

@Controller
public class AllPicController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private StudentService studentService;

	/**
	 * 跳转到照片缩略图界面
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAllPic")
	public String queryAllPic(HttpSession session, HttpServletRequest request) throws Exception {
		List<String> MyClass = courseService.findMyClass(session);
		System.out.println(MyClass);
		// 填充到request域
		request.setAttribute("MyClass", MyClass);
		// 转发到jsp页面显示
		return "/picsList";
	}

	/**
	 * 根据班级名称查询学生记录（只包括且只需要学号和图片地址和姓名） 如果使用了@requestBody,可以避过了json解析成对象
	 * 1.把json字符串解码 2.切割，原先是：class_Name=XXX 3.将参数传入
	 * 如果没有使用：直接前端往后端传递字符串就可以，不需要@requestBody，建议使用
	 * 
	 * @param class_Name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findPics", method = RequestMethod.POST)
	public @ResponseBody List<Student> findPics(String class_Name, HttpServletRequest request) throws Exception {
		List<Student> list = studentService.findPicsByClassSome(class_Name);
		return list;
	}
	
	@RequestMapping(value = "/findPic", method = RequestMethod.POST)
	public @ResponseBody Student findPic(String stu_sno) throws Exception {
		Student student = studentService.findStudentBySno(stu_sno);
		return student;
	}
}
