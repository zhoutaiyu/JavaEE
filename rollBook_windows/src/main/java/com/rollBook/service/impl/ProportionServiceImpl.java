package com.rollBook.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rollBook.mapper.ProportionMapper;
import com.rollBook.po.Proportion;
import com.rollBook.po.ProportionExample;
import com.rollBook.po.ProportionExample.Criteria;
import com.rollBook.po.Teacher;
import com.rollBook.service.ProportionService;

/**
 * @author 周太宇
 * @date 2017年8月11日 下午11:00:14
 **/
@Service
public class ProportionServiceImpl implements ProportionService {
	@Autowired
	private ProportionMapper proportionMapper;

	/**
	 * 根据老师id查找成绩比例记录
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Proportion selectProportionByTid(Long id) throws Exception {
		ProportionExample example = new ProportionExample();
		Criteria criteria = example.createCriteria();
		criteria.andTidEqualTo(id);
		List<Proportion> list = proportionMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 目标：查找老师设置的课程的五项组成的比例
	 */
	@Override
	public Proportion selectProById(HttpSession session) throws Exception {
		Teacher teacher = (Teacher) session.getAttribute("activeUser");
		Proportion DateProportion = this.selectProportionByTid(teacher.getId());
		return DateProportion;
	}

	@Override
	public void updateProById(HttpSession session, Proportion proportion) throws Exception {
		Teacher teacher = (Teacher) session.getAttribute("activeUser");
		Proportion result = new Proportion();
		Proportion DateProportion = this.selectProportionByTid(teacher.getId());
		if (DateProportion != null) {
			DateProportion.setModTime(new Date());
			DateProportion.setAttendance(proportion.getAttendance());
			DateProportion.setPerformance(proportion.getPerformance());
			DateProportion.setTask(proportion.getTask());
			DateProportion.setExperiment(proportion.getExperiment());
			DateProportion.setOther(proportion.getOther());
			DateProportion.setSradio(proportion.getSradio());
			proportionMapper.updateByPrimaryKeySelective(DateProportion);
		} else {
			result.setTid(teacher.getId());
			result.setCreateTime(new Date());
			result.setModTime(new Date());
			result.setAttendance(proportion.getAttendance());
			result.setPerformance(proportion.getPerformance());
			result.setTask(proportion.getTask());
			result.setExperiment(proportion.getExperiment());
			result.setOther(proportion.getOther());
			result.setIsDel(false);
			result.setSradio(proportion.getSradio());
			proportionMapper.insert(result);
		}

	}

}
