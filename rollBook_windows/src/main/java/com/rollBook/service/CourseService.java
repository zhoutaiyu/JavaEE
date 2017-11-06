package com.rollBook.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.rollBook.po.Course;

/**
 * @author 周太宇
 * @date 2017年8月11日 下午10:58:48
 **/

public interface CourseService {

	// 老师查找自己教授的班级
	public List<String> findMyClass(HttpSession session) throws Exception;

	// 添加课程,接收开始时间、结束时间、班级名称、课程名
	public void addCourse(Course course, HttpSession session) throws Exception;

	// 老师查找自己讲授的课程
	public List<String> findCourse(HttpSession session) throws Exception;
	
	// 老师根据课程名称查找所教的班级
	public List<String> findClassesByCname(HttpSession session, String cname) throws Exception;

	// 查找tid在course的记录
	public List<Course> findAll(HttpSession session) throws Exception;

	// 根据班级和课程名查找课程记录
	public Course findCourseByClassAndCname(String class_name,String cname) throws Exception;
}
