package com.rollBook.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.rollBook.po.Sc;
import com.rollBook.po.Student;
import com.rollBook.povo.StudentVo;

/**
 * @author 周太宇
 * @date 2017年8月11日 下午10:58:48
 **/

public interface StudentService {
	// 根据学号查找student记录
	public Student findStudentBySno(String sno) throws Exception;

	// 验证身份
	public Student authenticat(String usercode, String password) throws Exception;

	// 上传学生图片
	public void uploadPic(String sno, String FileUrl) throws Exception;

	// 按班级查找照片的路径对应的学号（封装到Student对象中）
	public List<Student> findPicsByClassSome(String class_name) throws Exception;

	// 按班级查找students
	public List<Student> findStusByClass(String class_name) throws Exception;

	// 修改密码,返回成功或错误信息
	public String updatePwd(String oldPwd, String newPwd, HttpSession session) throws Exception;
	
	// 渲染StudentVo，包括填充学生student表和record表和sc表。
	public List<StudentVo> render(List<Sc> scs, String cname) throws Exception;
}
