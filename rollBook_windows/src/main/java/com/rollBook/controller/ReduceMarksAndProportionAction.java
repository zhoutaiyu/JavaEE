package com.rollBook.controller;

import com.rollBook.po.Proportion;
import com.rollBook.po.Score;
import com.rollBook.povo.ScoreAndProVo;
import com.rollBook.service.ProportionService;
import com.rollBook.service.ScService;
import com.rollBook.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ReduceMarksAndProportionAction {
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private ProportionService proportionService;
	@Autowired
	private ScService scService;

	/**
	 * 假设每位老师对所教的所有课的严格程度一致，根据session获得tid，来获得渲染的源数据（即扣分和成绩构成比例）
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/setReduceMarks")
	public ModelAndView SetReduceMarks(HttpSession session) throws Exception {
		Score scoreObject = scoreService.selectByTid(session);
		Proportion proportionObject = proportionService.selectProById(session);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("scoreObject", scoreObject);
		modelAndView.addObject("proportionObject", proportionObject);
		// 指定逻辑视图名
		modelAndView.setViewName("editReduceMarks");
		return modelAndView;
	}

	@RequestMapping("/ReduceMarksSubmit")
	public String ReduceMarksSubmit(HttpSession session, HttpServletRequest request, ScoreAndProVo ScoreAndProVo)
			throws Exception {
		//验证比例加起来是否等于100%
		Proportion p= ScoreAndProVo.getProportion();
		if(p.getAttendance()+p.getPerformance()+p.getOther()+p.getExperiment()+p.getTask()!=100){
			request.setAttribute("msg", "比例总和应该为1，请重新输入。");
			Score scoreObject = scoreService.selectByTid(session);
			Proportion proportionObject = proportionService.selectProById(session);
			request.setAttribute("scoreObject",scoreObject);
			request.setAttribute("proportionObject", proportionObject);
			return "editReduceMarks";
		}
		scoreService.updateReduceMarks(ScoreAndProVo,session);
		proportionService.updateProById(session, ScoreAndProVo.getProportion());
		scService.updateScTotal(p.getSradio(), session);
		request.setAttribute("scoreObject", ScoreAndProVo.getScore());
		request.setAttribute("proportionObject", ScoreAndProVo.getProportion());
		request.setAttribute("msg", "修改成功");
		return "editReduceMarks";
	}

}
