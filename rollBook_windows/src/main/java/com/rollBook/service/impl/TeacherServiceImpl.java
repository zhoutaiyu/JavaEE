package com.rollBook.service.impl;

import com.rollBook.exception.CustomException;
import com.rollBook.mapper.StudentMapper;
import com.rollBook.mapper.TeacherMapper;
import com.rollBook.mapper.UploadMapper;
import com.rollBook.po.Student;
import com.rollBook.po.Teacher;
import com.rollBook.po.TeacherExample;
import com.rollBook.po.TeacherExample.Criteria;
import com.rollBook.po.Upload;
import com.rollBook.service.StudentService;
import com.rollBook.service.TeacherService;
import com.rollBook.utils.excleToPojo.ExcleToPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 周太宇
 * @date 2017年8月11日 下午11:00:14
 **/
@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherMapper teacherMapper;
	@Autowired
	private UploadMapper uploadMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private StudentService studentService;

	@Override
	public Teacher findTecByName(String name) {
		// 创建查询条件
		TeacherExample example = new TeacherExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		criteria.andIsDelEqualTo(false);
		List<Teacher> list = teacherMapper.selectByExample(example);
		Teacher teacher = null;
		// 判断list中是否为空
		if (list != null && list.size() > 0) {
			teacher = list.get(0);
			return teacher;
		}
		return teacher;
	}

	/**
	 * 根据wid查找teacher
	 *
	 * @param wid
	 * @return
	 */
	@Override
	public Teacher findTecByWid(Long wid) {
		// 创建查询条件
		TeacherExample example = new TeacherExample();
		Criteria criteria = example.createCriteria();
		criteria.andWidEqualTo(wid);
		List<Teacher> list = teacherMapper.selectByExample(example);
		Teacher teacher = null;
		// 判断list中是否为空
		if (list != null && list.size() > 0) {
			teacher = list.get(0);
			return teacher;
		}
		return teacher;
	}

	/**
	 * 根据id查询老师信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Teacher findTecByid(Long id) throws Exception {
		return teacherMapper.selectByPrimaryKey(id);
	}

	@Override
	public Teacher authenticat(String usercode, String password) throws Exception {

		/**
		 * 认证过程： 根据用户身份（账号）查询数据库，如果查询不到用户不存在 对输入的密码 和数据库密码 进行比对，如果一致，认证通过
		 */
		// 根据用户账号查询数据库
		Teacher activeUser = this.findTecByName(usercode);

		if (activeUser == null) {
			// 抛出异常
			throw new CustomException("用户账号不存在");
		}

		if (!activeUser.getPassword().equals(password)) {
			// 抛出异常
			throw new CustomException("用户名或密码错误");
		}

		// 认证通过，返回用户身份信息
		return activeUser;
	}

	/**
	 * 判断原密码是否正确，如果正确，把新输入的密码作为最终密码
	 */
	@Override
	public String updatePwd(String oldPwd, String newPwd, HttpSession session) throws Exception {
		Teacher teacher = (Teacher) session.getAttribute("activeUser");
		if (!teacher.getPassword().equals(oldPwd)) {
			return "原密码输入不正确，修改失败";
		}
		// 修改密码
		teacher.setPassword(newPwd);
		teacherMapper.updateByPrimaryKeySelective(teacher);
		// 把session域中的activeUser覆盖，如果再次修改密码，不会读取到脏数据。
		session.setAttribute("activeUser", teacher);
		return "修改成功";

	}

	/**
	 * 上传点名册，记录文件url,并添加学生信息 上传同一个班级表二次，第二次会替代第一次学号相同的数据
	 */
	@Override
	public void uploadExcle(String url, HttpSession session) throws Exception {
		//方便执行批处理，初始化参数
		List<Student> studentsList=new ArrayList<Student>();
		Upload upload = new Upload();
		Teacher teacher = (Teacher) session.getAttribute("activeUser");
		upload.setTid(teacher.getId());
		upload.setTitle(url);
		upload.setCreateTime(new Date());
		upload.setModTime(new Date());
		uploadMapper.insert(upload);
		ExcleToPojo excleToPojo = new ExcleToPojo();
		List<Student> students = excleToPojo.excleToExcle(url);
		for (int i = 0; i < students.size(); i++) {
			Student student = studentService.findStudentBySno(students.get(i).getSno());
			// 如果不为空，为避免前面上传的数据个别属性有错，重新设置。为空，则添加
			if (student != null) {
				student.setName(students.get(i).getName());
				student.setSex(students.get(i).getSex());
				student.setClassName(students.get(i).getClassName());
				// 第一次用到修改日期。
				student.setModTime(new Date());
				studentMapper.updateByPrimaryKeySelective(student);
			} else {
				studentsList.add(students.get(i));
			}
		}
		studentMapper.insertbatch(studentsList);
	}

	/**
	 * 查询所有的教师信息，不包括删除的，isdel=true
	 *
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Teacher> selectAllTeacher() throws Exception {
		// 创建查询条件
		TeacherExample example = new TeacherExample();
		Criteria criteria = example.createCriteria();
		criteria.andIsDelEqualTo(false);
		List<Teacher> list = teacherMapper.selectByExample(example);
		return list;
	}

	/**
	 * 根据主键tid修改老师信息
	 *
	 * @param teacher
	 * @throws Exception
	 */
	@Override
	public void updateTea(Teacher teacher) throws Exception {
		teacher.setModTime(new Date());
		teacherMapper.updateByPrimaryKeySelective(teacher);
	}

	/**
	 * 根据工号删除老师信息,isDel置为true，实还存在数据库中
	 *
	 * @param wid
	 * @throws Exception
	 */
	@Override
	public void deleteTeaByWid(Long wid) throws Exception {
		Teacher teacher = findTecByWid(wid);
		teacher.setIsDel(true);
		teacher.setModTime(new Date());
		teacherMapper.updateByPrimaryKeySelective(teacher);
	}

	/**
	 * 删除老师信息通过id
	 * @param id
	 * @throws Exception
	 */
	@Override
	public void deleteTeaByid(Long id) throws Exception {
		teacherMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据传过来的部分老师信息查找老师信息,若没有条件查询所有老师信息
	 *
	 * @param teacher
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Teacher> findTecByCondition(Teacher teacher) throws Exception {
		// 创建查询条件
		TeacherExample example = new TeacherExample();
		Criteria criteria = example.createCriteria();
		if (teacher.getName() != null&&teacher.getName() !="") {
			criteria.andNameEqualTo(teacher.getName());
		}
		if (teacher.getWid() != null) {
			criteria.andWidEqualTo(teacher.getWid());
		}
		criteria.andIsDelEqualTo(false);
		List<Teacher> list = teacherMapper.selectByExample(example);
		return list;
	}


	/**
	 * 通过session返回老师信息
	 */
	@Override
	public Teacher findTecBySession(HttpSession session) throws Exception {
		Teacher teacher = (Teacher) session.getAttribute("activeUser");
		return teacher;
	}

	/**
	 * // 通过tid查找teacher记录
	 */
	@Override
	public Teacher findTeacherByTid(Long id) throws Exception {
		Teacher teacher = teacherMapper.selectByPrimaryKey(id);
		return teacher;
	}

}
