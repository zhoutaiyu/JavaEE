package com.rollBook.controller;

import com.rollBook.povo.RecordVo;
import com.rollBook.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rollBook.po.Student;
import com.rollBook.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 周太宇
 * @date 2017年8月11日 下午11:11:07
 **/
@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private RecordService recordService;

	@RequestMapping("/student/{sno}")
	@ResponseBody
	private Student getStudentBySno(@PathVariable String sno) throws Exception {
		Student student = studentService.findStudentBySno(sno);
		return student;
	}

	/**’
	 * 查询该学生的违纪和加分情况，填充到前台
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showPresentation")
	public  String showPresentation(HttpSession session, HttpServletRequest request) throws Exception {
		List<RecordVo> recordVoList =recordService.findReBySessionAndTerm(session);
		// 填充到request域
		request.setAttribute("recordVoList",recordVoList);
		// 转发到jsp页面显示
		return "/showTermRecords";
	}
}
