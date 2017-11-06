package com.rollBook.service.impl;

import com.rollBook.mapper.RecordMapper;
import com.rollBook.mapper.ScMapper;
import com.rollBook.po.*;
import com.rollBook.po.RecordExample.Criteria;
import com.rollBook.povo.RecordVo;
import com.rollBook.service.*;
import com.rollBook.utils.NumberToRsection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author 周太宇
 * @date 2017年8月11日 下午11:00:14
 **/
@Service
public class RecordServiceImpl implements RecordService {
	@Autowired
	private RecordMapper recordMapper;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private ScService scService;
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private ScMapper scMapper;
	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService courseService;
	//设置日期格式
	private SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd" );

	/**
	 * 根据课堂号和班级号查找违纪情况
	 * @param cname
	 * @param className
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<RecordVo> findReBycnameAndclassName(String cname, String className,HttpSession session) throws Exception{
		List<Student> students=studentService.findStusByClass(className);
		List<RecordVo> result=new ArrayList<RecordVo>();
		Score score = scoreService.selectByTid(session);
		List<RecordVo> results=new ArrayList<RecordVo>();
		for (int i=0;i<students.size();i++) {
			List<Record> records = findReBycnameAndSno(cname, students.get(i).getSno());
			if (records != null) {
				for(int j=0;j<records.size();j++) {
					RecordVo recordVo = new RecordVo();
					//设置学号
					recordVo.setSno(students.get(i).getSno());
					//设置姓名
					recordVo.setName(students.get(i).getName());
					//设置发生的事情的名字，由数据表里的序号转换而来
					recordVo.setThingName(this.findThingNameByNo(records.get(j).getThing()));
					//设置扣分
					int grade = findScoreByNo(records.get(j).getThing(), score);
					recordVo.setScore(grade);
					//设置时间
					recordVo.setTime(records.get(j).getTime());//日期方便比较
					//data包括第几周、星期几、课次等信息(字符串方便在前台显示)
					String date=sdf.format(records.get(j).getTime());
					date=date+" 第"+records.get(j).getRweek()+"周"+" "+NumberToRsection.todayString(records.get(j).getRday())+" "+ NumberToRsection.toRsectionString(records.get(j).getRsection());
					recordVo.setTheDate(date);
					result.add(recordVo);
				}

			}

		}
		//设置最新发生的为t,找出的记录为按时间顺序，由最新违纪到晚违纪。
		Date t =new Date();
		t.setTime(10000);
		int tIndex=-1;
		while(result.size()>0) {
			for (int i = 0; i < result.size(); i++) {
				if (t.before(result.get(i).getTime())) {
					t = result.get(i).getTime();
					tIndex = i;
				}
			}
			if (tIndex != -1) {
				results.add(result.get(tIndex));
				result.remove(tIndex);
				tIndex = -1;
			}
			t.setTime(10000);
		}

		return results;
	}
	/**
	 * 根据老师的id查找老师登记的加分与扣分记录。 isDel很重要：可以用来标志是否此记录是否已经不在正授课程中。以前的成绩不用查询到。
	 */
	@Override
	public List<Record> findReByTid(Long tid) throws Exception {
		RecordExample example = new RecordExample();
		Criteria criterion = example.createCriteria();
		criterion.andIsDelEqualTo(false);
		criterion.andTidEqualTo(tid);
		List<Record> list = recordMapper.selectByExample(example);
		return list;

	}

	/**
	 * 添加record记录,并加减分该学生的此科目的成绩。
	 */
	@Override
	public void addRecord(HttpSession session, Record record, String happen_time) throws Exception {
		Teacher tec = teacherService.findTecBySession(session);
		// 由于前台传来的sno绑定的为学号和姓名，所以得切割再存到数据库中
		String snoAndName = record.getSno();
		// 截取下标范围为0到12-1的字符
		String sno = snoAndName.substring(0, 12);
		// string转为date
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(happen_time);
		record.setTime(date);
		record.setSno(sno);
		record.setTid(tec.getId());
		record.setIsDel(false);
		record.setModTime(new Date());
		recordMapper.insert(record);
		// 加减分该同学的成绩
		// 1.先查询该学生此科的成绩单
		// 2.查询该老师的扣分制度
		// 3.对比记录扣除相应的分数
		// 4.修改数据库
		Sc sc = scService.findScBySnoAndCname(sno, record.getCname());
		Score score = scoreService.selectByTid(session);
		int grade = findScoreByNo(record.getThing(), score);
		// 加减局部得分
		Integer thing = record.getThing();
		// 1表示缺勤，2表示早退，3表示迟到，4表示玩手机,
		// 5表示提问，6表示作业差，7表示实验差
		if (thing.equals(1) || thing.equals(2) || thing.equals(3)) {
			sc.setAttendance(sc.getAttendance() + grade);
		}
		if (thing.equals(4) || thing.equals(5)) {
			sc.setPerformance(sc.getPerformance() + grade);
		}
		if (thing.equals(6)) {
			sc.setTask(sc.getTask() + grade);
		}
		if (thing.equals(7)) {
			sc.setExperiment(sc.getExperiment() + grade);
		}
		// 加减总得分
		if(sc.getTotal() + grade>100){
			sc.setTotal(100);
		}else if(sc.getTotal() + grade<0){
			sc.setTotal(0);
		}else{
			sc.setTotal(sc.getTotal() + grade);
		}
		sc.setModTime(new Date());
		scMapper.updateByPrimaryKey(sc);
	}

	/**
	 * 通过序号（比如缺勤的序号）查找该序号对应的分值。
	 */
	@Override
	public int findScoreByNo(int no, Score score) throws Exception {
		switch (no) {
		// 1表示缺勤，2表示早退，3表示迟到，4表示玩手机,
		// 5表示提问，6表示作业差，7表示实验差
		case 1:
			// 缺勤
			return -score.getAbsent();

		case 2:
			// 早退
			return -score.getEarly();

		case 3:
			// 迟到
			return -score.getLate();

		case 4:
			// 玩手机
			return -score.getPlay();

		case 5:
			// 提问
			return score.getQuiz();

		case 6:
			// 作业差
			return -score.getAssignment();

		case 7:
			// 实验差
			return -score.getExperiment();

		}
		return 0;
	}

	/**
	 * // 通过序号（比如缺勤的序号）查找该序号对应的事件名。
	 * @param no
	 * @return
	 * @throws Exception
	 */
	@Override
	public String findThingNameByNo(int no) throws Exception {
		switch (no) {
			// 1表示缺勤，2表示早退，3表示迟到，4表示玩手机,
			// 5表示提问，6表示作业差，7表示实验差
			case 1:
				// 缺勤
				return "缺勤";

			case 2:
				// 早退
				return "早退";

			case 3:
				// 迟到
				return "迟到";

			case 4:
				// 玩手机
				return "玩手机";

			case 5:
				// 提问
				return "提问";

			case 6:
				// 作业差
				return "作业差";

			case 7:
				// 实验差
				return "实验差";

		}
		return null;
	}
	/**
	 * 通过cname和sno查找record记录
	 */
	@Override
	public List<Record> findReBycnameAndSno(String cname, String sno) throws Exception {
		RecordExample example = new RecordExample();
		Criteria criterion = example.createCriteria();
		criterion.andIsDelEqualTo(false);
		criterion.andCnameEqualTo(cname);
		criterion.andSnoEqualTo(sno);
		List<Record> list = recordMapper.selectByExample(example);
		return list;
	}

	/**
	 * 目标：返回该学生当前学期的违纪和加分情况
	 * 思路：查看今天属于夏季学期还是冬季学期->查找该学期下未被删除的记录
	 * ->添加到recordVo中->返回
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<RecordVo> findReBySessionAndTerm(HttpSession session) throws Exception{
		List<RecordVo> recordVoList =new ArrayList<RecordVo>();
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
		Student student=(Student) session.getAttribute("activeUser");
		Date now=new Date();
		//得到系统年份
		Calendar a=Calendar.getInstance();
		int year=a.get(Calendar.YEAR);
		//定义夏季学期的开始和结束
		String summerBegin=year+"-03-01";
		String summerEnd=year+"-08-20";
		Date summer1 = sdf.parse( summerBegin);
		Date summer2 = sdf.parse( summerEnd );
		//定义冬季学期的开始与结束
		String winterBegin=year+"-08-21";
		String winterEnd=(year+1)+"-02-22";
		Date winter1 = sdf.parse( winterBegin );
		Date winter2 = sdf.parse( winterEnd );
		//定义要查询的时间段
		Date begin=new Date();
		Date end =new Date();
		if(now.after(summer1)&&now.before(summer2)){
			begin=summer1;
			end=summer2;
		}else{
			begin=winter1;
			end=winter2;
		}
		//查询该session下当前学期的违纪记录
		RecordExample example = new RecordExample();
		Criteria criterion = example.createCriteria();
		criterion.andSnoEqualTo(student.getSno());
		criterion.andIsDelEqualTo(false);
		criterion.andTimeBetween(begin,end);
		List<Record> list = recordMapper.selectByExample(example);
		for(int i=0;i<list.size();i++){
			Record record=list.get(i);
			RecordVo recordVo =new RecordVo();
			//填充
			recordVo.setCname(record.getCname());
			recordVo.setThingName(findThingNameByNo(record.getThing()));
			//返回course，等于返回填写记录老师的tid
			Course course=courseService.findCourseByClassAndCname(student.getClassName(),record.getCname());
			Score score=scoreService.selectByTid(course.getTid());
			recordVo.setScore(findScoreByNo(record.getThing(),score));
			//data包括第几周、星期几、课次等信息
			String date=sdf.format(record.getTime());
			date=date+" 第"+record.getRweek()+"周"+" "+NumberToRsection.todayString(record.getRday())+" "+ NumberToRsection.toRsectionString(record.getRsection());
			recordVo.setTime(record.getTime());
			recordVo.setTheDate(date);
			recordVoList.add(recordVo);
		}
		return recordVoList;
	}
}
