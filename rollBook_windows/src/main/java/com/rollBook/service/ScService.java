package com.rollBook.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.rollBook.po.Sc;

/**
 * @author 周太宇
 * @date 2017年8月30日 下午7:38:10
 **/

public interface ScService {
	// 为学生添加原始成绩表，即无扣分情况，此操作在老师添加课程后。
	public void addSc(String className, String courseName, HttpSession session) throws Exception;

	// 修改原始成绩，如果同学不存在扣减分则修改。加分有两种情况，一是加在课堂表现，二是其他
	public void updateScTotal(int selectRadio, HttpSession session) throws Exception;

	// 通过tid返回sc表中所有的同学数据
	public List<Sc> findScByTid(Long tid) throws Exception;

	// 通过tid和课程名返回sc表中所有的同学数据
	public List<Sc> findScByTidAndCname(String courseName, HttpSession session) throws Exception;

	// 通过Sc的id修改成绩
	public void updateByScId(Sc newSc) throws Exception;

	// 通过学生学号和课程名查找sc里面的一条记录
	public Sc findScBySnoAndCname(String sno, String cname) throws Exception;
}
