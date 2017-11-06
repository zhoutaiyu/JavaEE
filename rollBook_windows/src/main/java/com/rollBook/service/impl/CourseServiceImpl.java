package com.rollBook.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rollBook.mapper.CourseMapper;
import com.rollBook.po.Course;
import com.rollBook.po.CourseExample;
import com.rollBook.po.CourseExample.Criteria;
import com.rollBook.po.Teacher;

import com.rollBook.service.CourseService;
import com.rollBook.service.TeacherService;

/**
 * @author 周太宇
 * @date 2017年8月11日 下午11:00:14
 **/
@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseMapper courseMapper;
	@Autowired
	private TeacherService teacherService;

	/**
	 * 查询course表查询该session身份下的老师教授过的班级
	 */
	@Override
	public List<String> findMyClass(HttpSession session) throws Exception {
		List<Course> list = findAll(session);
		List<String> classes = new ArrayList<String>();
		// 判断list中是否为空,不为空则把班级名加到classes中
		if (list != null && list.size() > 0) {
			for (Course c : list) {
				// 去重
				if (!classes.contains(c.getClassName())) {
					classes.add(c.getClassName());
				}
			}
		}
		// 然后返回
		return classes;
	}

	/**
	 * 添加课程到课程表
	 */
	@Override
	public void addCourse(Course course, HttpSession session) throws Exception {
		// 提取到teacher
		Teacher teacher = (Teacher) session.getAttribute("activeUser");
		course.setTid(teacher.getId());
		course.setCreateTime(new Date());
		course.setIsDel(false);
		course.setModTime(new Date());
		course.setStatus(true);// 设置课程状态
		// 执行
		courseMapper.insert(course);
	}

	@Override
	public List<String> findCourse(HttpSession session) throws Exception {
		List<Course> list = findAll(session);
		List<String> courseNames = new ArrayList<String>();
		// 判断list中是否为空,不为空则把班级名加到classes中
		if (list != null && list.size() > 0) {
			for (Course c : list) {
				// 去重
				if (!courseNames.contains(c.getCname())) {
					courseNames.add(c.getCname());
				}
			}
		}
		// 然后返回
		return courseNames;
	}

	/**
	 * 查找tid在course的记录
	 */
	@Override
	public List<Course> findAll(HttpSession session) throws Exception {
		Teacher tec = teacherService.findTecBySession(session);
		CourseExample example = new CourseExample();
		Criteria criteria = example.createCriteria();
		criteria.andTidEqualTo(tec.getId());
		List<Course> list = courseMapper.selectByExample(example);
		return list;
	}

	/**
	 * 根据班级和课程名查找课程记录
	 * @param class_name
	 * @param cname
	 * @return
	 * @throws Exception
	 */
	@Override
	public Course findCourseByClassAndCname(String class_name, String cname) throws Exception {
		CourseExample example = new CourseExample();
		Criteria criteria = example.createCriteria();
		criteria.andIsDelEqualTo(false);
		criteria.andClassNameEqualTo(class_name);
		criteria.andCnameEqualTo(cname);
		List<Course> list = courseMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 老师根据课程名称查找所教的班级 最终返回无重复的班级名称
	 */
	@Override
	public List<String> findClassesByCname(HttpSession session, String cname) throws Exception {
		Teacher tec = teacherService.findTecBySession(session);
		CourseExample example = new CourseExample();
		Criteria criteria = example.createCriteria();
		criteria.andTidEqualTo(tec.getId());
		criteria.andCnameEqualTo(cname);
		List<Course> list = courseMapper.selectByExample(example);
		List<String> rList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			if (!rList.contains(list.get(i).getClassName())) {
				rList.add(list.get(i).getClassName());
			}
		}
		return rList;
	}

}
