package com.rollBook.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.rollBook.po.Record;
import com.rollBook.po.Score;
import com.rollBook.povo.RecordVo;

/**
 * @author 周太宇
 * @date 2017年8月30日 下午7:38:10
 **/

public interface RecordService {

	// 通过tid查找record记录
	public List<Record> findReByTid(Long tid) throws Exception;

	// 通过序号（比如缺勤的序号）查找该序号对应的分值。
	public int findScoreByNo(int no, Score score) throws Exception;

	// 通过序号（比如缺勤的序号）查找该序号对应的事件名。
	public String findThingNameByNo(int no) throws Exception;

	// 添加record记录
	public void addRecord(HttpSession session, Record record, String happen_time) throws Exception;

	// 通过cname和sno查找record记录
	public List<Record> findReBycnameAndSno(String cname, String sno) throws Exception;

	// 通过cname和className查找record记录
	public List<RecordVo> findReBycnameAndclassName(String cname, String className,HttpSession session) throws Exception;
	// 通过学生session获取该学生这一学期的违纪或加分情况
	public List<RecordVo> findReBySessionAndTerm(HttpSession session) throws Exception;

}
