package com.rollBook.povo;

import java.io.Serializable;
import java.util.List;

import com.rollBook.po.Record;
import com.rollBook.po.Sc;
import com.rollBook.po.Student;

/**
 * 
 * @author 周太宇
 * @time 2017年9月4日
 */
public class StudentVo extends Student implements Serializable {
	private List<Record> records;
	private Sc sc;
	private int no;//学生的显示序号，学号越大显示越后。

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Sc getSc() {
		return sc;
	}

	public void setSc(Sc sc) {
		this.sc = sc;
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

}