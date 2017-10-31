package com.rollBook.service;

import javax.servlet.http.HttpSession;

import com.rollBook.po.Admin;

/**
 * @author 周太宇
 * @date 2017年8月11日 下午10:58:48
 **/

public interface AdminService {
	// 通过用户名查找admin记录
	public Admin findAdmintByName(String name) throws Exception;

	// 修改密码,并返回信息
	public String updatePwd(String oldPwd, String newPwd, HttpSession session) throws Exception;

	// 验证
	public Admin authenticat(String name, String password) throws Exception;

	// 上传老师名单并添加老师信息
	public void uploadTecExcle(String url, HttpSession session) throws Exception;
}
