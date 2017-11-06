package com.rollBook.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rollBook.exception.CustomException;
import com.rollBook.mapper.StudentMapper;
import com.rollBook.po.Record;
import com.rollBook.po.Sc;
import com.rollBook.po.Student;
import com.rollBook.po.StudentExample;
import com.rollBook.po.StudentExample.Criteria;
import com.rollBook.povo.StudentVo;
import com.rollBook.service.RecordService;
import com.rollBook.service.StudentService;

/**
 * @author 周太宇
 * @date 2017年8月11日 下午11:00:14
 **/
@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private RecordService recordService;

	@Override
	public Student findStudentBySno(String sno) {
		// 创建查询条件
		StudentExample example = new StudentExample();
		Criteria criteria = example.createCriteria();
		criteria.andSnoEqualTo(sno);
		List<Student> list = studentMapper.selectByExample(example);
		Student stu = null;
		// 判断list中是否为空
		if (list != null && list.size() > 0) {
			stu = list.get(0);
			return stu;
		}
		return stu;
	}

	@Override
	public Student authenticat(String usercode, String password) throws Exception {

		/**
		 * 认证过程： 根据用户身份（账号）查询数据库，如果查询不到用户不存在 对输入的密码 和数据库密码 进行比对，如果一致，认证通过
		 */
		// 根据用户账号查询数据库
		Student activeUser = this.findStudentBySno(usercode);

		if (activeUser == null) {
			throw new CustomException("用户账号不存在");
		}
		if (!activeUser.getPassword().equals(password)) {
			// 抛出异常
			throw new CustomException("用户名或密码错误");
		}
		// 认证通过，返回用户身份信息
		return activeUser;
	}

	@Override
	public void uploadPic(String sno, String FileUrl) throws Exception {
		Student stu = this.findStudentBySno(sno);
		stu.setImage(FileUrl);
		studentMapper.updateByPrimaryKey(stu);
	}

	/**
	 * 判断原密码是否正确，如果正确，把新输入的密码作为最终密码
	 */
	@Override
	public String updatePwd(String oldPwd, String newPwd, HttpSession session) throws Exception {
		Student student = (Student) session.getAttribute("activeUser");
		if (!student.getPassword().equals(oldPwd)) {
			return "原密码输入不正确，修改失败";
		}
		// 修改密码
		student.setPassword(newPwd);
		studentMapper.updateByPrimaryKeySelective(student);
		// 把session域中的activeUser覆盖，如果再次修改密码，不会读取到脏数据。
		session.setAttribute("activeUser", student);
		return "修改成功";
	}

	/**
	 * 步骤：（1）根据班级名称查询出学生集合 （2）清空每个对象除开学号和图片地址以外的信息，在ajax上传输数据量变小 （3）返回
	 */
	@Override
	public List<Student> findPicsByClassSome(String class_name) throws Exception {
		List<Student> lists=findStusByClass(class_name);
		// 实际传输list
		List<Student> list = new ArrayList<Student>();
		for (int i = 0; i < lists.size(); i++) {
			Student student = new Student();
			student.setSno(lists.get(i).getSno());
			student.setImage(lists.get(i).getImage());
			student.setName(lists.get(i).getName());
			list.add(student);
		}
		return list;
	}
	/**
	 * 根据sc表的sno查找student表信息。
	 * 根据sno和cname查找recode记录
	 */
	@Override
	public List<StudentVo> render(List<Sc> scs, String cname) throws Exception {
		List<StudentVo> list=new ArrayList<StudentVo>();
		Long count=(long) 1;//序号
		for(int i=0;i<scs.size();i++){
			StudentVo studentVo =new StudentVo();//创建，后面填充属性
			String sno = scs.get(i).getSno();
			Student student = findStudentBySno(sno);
			List<Record> records = recordService.findReBycnameAndSno(cname, sno);
			//学生基本信息
			studentVo.setId(count++);
			studentVo.setSno(sno);
			studentVo.setName(student.getName());
			studentVo.setSex(student.getSex());
			studentVo.setClassName(student.getClassName());
			//该学生的加扣分记录
			studentVo.setRecords(records);
			//该学生的成绩
			studentVo.setSc(scs.get(i));
			list.add(studentVo);
		}
		return list;
	}

	/**
	 * 根据班级查找学号
	 * @param class_name
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Student> findStusByClass(String class_name) throws Exception {
		StudentExample example = new StudentExample();
		Criteria criteria = example.createCriteria();
		criteria.andClassNameEqualTo(class_name);
		List<Student> lists = studentMapper.selectByExample(example);
		return lists;
	}
}
