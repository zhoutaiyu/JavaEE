package com.rollBook.service;

import javax.servlet.http.HttpSession;

import com.rollBook.po.Proportion;

/**
 * @author 周太宇
 * @date 2017年8月11日 下午10:58:48
 **/

public interface ProportionService {

	// 目标：查找老师设置的课程的五项组成的比例
	public Proportion selectProById(HttpSession session) throws Exception;

	// 目标：更新比例
	public void updateProById(HttpSession session, Proportion proportion) throws Exception;
}
