package com.rollBook.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rollBook.exception.CustomException;
import com.rollBook.mapper.AdminMapper;
import com.rollBook.mapper.TeacherMapper;
import com.rollBook.mapper.UploadMapper;
import com.rollBook.po.Admin;
import com.rollBook.po.AdminExample;
import com.rollBook.po.AdminExample.Criteria;
import com.rollBook.po.Teacher;
import com.rollBook.po.Upload;
import com.rollBook.service.AdminService;
import com.rollBook.service.TeacherService;
import com.rollBook.utils.excleToPojo.ExcleToPojo;

/**
 * @author 周太宇
 * @date 2017年8月11日 下午11:00:14
 **/
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private UploadMapper uploadMapper;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private TeacherMapper teacherMapper;

	@Override
	public Admin findAdmintByName(String name) {
		// 创建查询条件
		AdminExample example = new AdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<Admin> list = adminMapper.selectByExample(example);
		// 判断list中是否为空
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 验证身份
	 */
	@Override
	public Admin authenticat(String usercode, String password) throws Exception {

		/**
		 * 认证过程： 根据用户身份（账号）查询数据库，如果查询不到用户不存在 对输入的密码 和数据库密码 进行比对，如果一致，认证通过
		 */
		// 根据用户账号查询数据库
		Admin activeUser = this.findAdmintByName(usercode);

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
	 * 上传记录老师信息的excle，记录文件url,并添加老师信息
	 */
	@Override
	public void uploadTecExcle(String url, HttpSession session) throws Exception {
		Upload upload = new Upload();
		// Admin admin = (Admin) session.getAttribute("activeUser");
		upload.setTid((long) 0);// 0特指管理员
		upload.setTitle(url);
		upload.setCreateTime(new Date());
		upload.setModTime(new Date());
		uploadMapper.insert(upload);
		ExcleToPojo excleToPojo = new ExcleToPojo();
		List<Teacher> teachers = excleToPojo.tecExcleToExcle(url);
		for (int i = 0; i < teachers.size(); i++) {
			// Teacher teacher = new Teacher();
			Teacher teacher2 = teacherService.findTecByWid(teachers.get(i).getWid());
			// 如果不为空，重新设置名字。为空，则添加
			if (teacher2 != null) {
				teacher2.setName(teachers.get(i).getName());
				// 第一次用到修改日期。
				teacher2.setModTime(new Date());
				//对名字和日期做修改，其他不修改，selective方法
				teacherMapper.updateByPrimaryKeySelective(teacher2);
			} else {
				teacherMapper.insert(teachers.get(i));
			}
		}

	}

	/**
	 * 判断原密码是否正确，如果正确，把新输入的密码作为最终密码
	 */
	@Override
	public String updatePwd(String oldPwd, String newPwd, HttpSession session) throws Exception {
		Admin admin = (Admin) session.getAttribute("activeUser");
		if (!admin.getPassword().equals(oldPwd)) {
			return "原密码输入不正确，修改失败";
		}
		// 修改密码
		admin.setPassword(newPwd);
		admin.setModTime(new Date());
		adminMapper.updateByPrimaryKeySelective(admin);
		// 把session域中的activeUser覆盖，如果再次修改密码，不会读取到脏数据。
		session.setAttribute("activeUser", admin);
		return "修改成功";
	}

	// @Override
	// public String updatePwd(String oldPwd, String newPwd, HttpSession
	// session) throws Exception {
	// Teacher teacher = (Teacher) session.getAttribute("activeUser");
	// if (!teacher.getPassword().equals(oldPwd)) {
	// return "原密码输入不正确，修改失败";
	// }
	// // 修改密码
	// teacher.setPassword(newPwd);
	// teacherMapper.updateByPrimaryKeySelective(teacher);
	// // 把session域中的activeUser覆盖，如果再次修改密码，不会读取到脏数据。
	// session.setAttribute("activeUser", teacher);
	// return "修改成功";
	//
	// }

}
