package com.rollBook.service.impl;

import com.rollBook.mapper.ScMapper;
import com.rollBook.po.*;
import com.rollBook.po.ScExample.Criteria;
import com.rollBook.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 周太宇
 * @date 2017年8月30日 下午7:41:41
 **/
@Service
public class ScServiceImpl implements ScService {
    @Autowired
    private ScMapper scMapper;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ProportionService proportionService;
    @Autowired
    private RecordService recordService;

    /**
     * 步骤： 1.根据班级名成查出student集合 2.构建sc表 填入课程名、学号....
     */
    @Override
    public void addSc(String className, String courseName, HttpSession session) throws Exception {
        Teacher tec = teacherService.findTecBySession(session);
        Score DateScore = scoreService.selectByTid(session);
        Proportion DatePro = proportionService.selectProById(session);
        List<Student> stuList = studentService.findPicsByClassSome(className);
        List<Sc> batchList=new ArrayList<Sc>();
        for (int i = 0; i < stuList.size(); i++) {
            Sc sc = new Sc();
            sc.setTid(tec.getId());
            sc.setCname(courseName);
            sc.setSno(stuList.get(i).getSno());
            sc.setCreateTime(new Date());
            sc.setIsDel(false);
            sc.setModTime(new Date());
            // 设置原始得分，按比例分，满分与原始得分的差值在课堂表现得分中体现。
            sc.setAttendance(DatePro.getAttendance());
            sc.setExperiment(DatePro.getExperiment());
            sc.setTask(DatePro.getTask());
            sc.setOther(DatePro.getOther());
            sc.setPerformance(DatePro.getPerformance() - (100 - DateScore.getTotal()));
            sc.setTotal(DateScore.getTotal());// 查询数据库得到。
            batchList.add(sc);
        }
        scMapper.insertbatch(batchList);
    }

    /**
     * 目标：修改所有学生的原始得分，按新的原始得分扣减分。
     */
    @Override
    public void updateScTotal(int selectRadio, HttpSession session) throws Exception {
        //判断加分加在哪里
        int fPerformance;
        int fother;
        if (selectRadio == 1) {
            fPerformance = 1;
            fother = 0;
        } else {
            fPerformance = 0;
            fother = 1;
        }
        Teacher teacher = teacherService.findTecBySession(session);
        List<Sc> sclist = this.findScByTid(teacher.getId());
        Score dateScore = scoreService.selectByTid(session);
        List<Record> recordlist = recordService.findReByTid(teacher.getId());
        Proportion datePro = proportionService.selectProById(session);
        Sc sc = new Sc();
        int result;
        for (int i = 0; i < sclist.size(); i++) {
            result = 0;
            // 设置各项比分的初始值
            int attendance = datePro.getAttendance();
            int experiment = datePro.getExperiment();
            int task = datePro.getTask();
            int other = 0;
            int performance = 0;
            if (fPerformance == 1) {
                //提问加在课堂表现上
                other = datePro.getOther();
                performance = 0;
            } else {
                //提问加在其他表现上
                other = 0;
                performance = datePro.getPerformance();
            }
            for (int j = 0; j < recordlist.size(); j++) {
                Record record = new Record();
                record = recordlist.get(j);
                if (record.getCname().equals(sclist.get(i).getCname()) && record.getSno().equals(sclist.get(i).getSno())) {
                    switch (record.getThing()) {
                        // 1表示缺勤，2表示早退，3表示迟到，4表示玩手机,
                        // 5表示提问，6表示作业差，7表示实验差
                        case 1:
                            // 缺勤
                            attendance = attendance - dateScore.getAbsent();
                            break;
                        case 2:
                            // 早退
                            attendance = attendance - dateScore.getEarly();
                            break;
                        case 3:
                            // 迟到
                            attendance = attendance - dateScore.getLate();
                            break;
                        case 4:
                            // 玩手机
                            performance = performance - dateScore.getPlay();
                            break;
                        case 5:
                            // 提问,若提问计入课堂表现，则课堂表现得分，反则其他成绩得分
                            //很妙啊
                            performance = performance + dateScore.getQuiz() * fPerformance;
                            other = other + dateScore.getQuiz() * fother;
                            break;
                        case 6:
                            // 作业差
                            task = task - dateScore.getAssignment();
                            break;
                        case 7:
                            // 实验差
                            experiment = experiment - dateScore.getExperiment();
                            break;
                    }
                }

            }
            // 计算结果
            result = attendance + experiment + task + other + performance;
            //预防大数或小数出现
            if (result > 100) {
                result = 100;
            }
            if (result < 0) {
                result = 0;
            }
            sc.setId(sclist.get(i).getId());
            sc.setModTime(new Date());
            sc.setAttendance(attendance);
            sc.setExperiment(experiment);
            sc.setTask(task);
            sc.setOther(other);
            sc.setPerformance(performance);
            sc.setTotal(result);
            scMapper.updateByPrimaryKeySelective(sc);
        }

    }

    /**
     * 通过tid返回sc表中所有的同学数据
     */
    @Override
    public List<Sc> findScByTid(Long tid) throws Exception {
        ScExample example = new ScExample();
        Criteria criteria = example.createCriteria();
        criteria.andTidEqualTo(tid);
        List<Sc> list = scMapper.selectByExample(example);
        return list;
    }

    /**
     * 通过Sc的id修改成绩
     */
    @Override
    public void updateByScId(Sc sc) throws Exception {
        // 更新新的model中不为空的字段,updateByPrimaryKeySelective
        // 会将为空的字段在数据库中置为NULL。updateByPrimaryKey
        scMapper.updateByPrimaryKeySelective(sc);
    }

    /**
     * 通过学生学号和课程名查找sc里面的一条记录
     */
    @Override
    public Sc findScBySnoAndCname(String sno, String cname) throws Exception {
        ScExample example = new ScExample();
        Criteria criteria = example.createCriteria();
        criteria.andSnoEqualTo(sno);
        criteria.andCnameEqualTo(cname);
        List<Sc> list = scMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 通过tid和课程名返回sc表中所有的同学数据
     */
    @Override
    public List<Sc> findScByTidAndCname(String courseName, HttpSession session) throws Exception {
        Teacher teacher = teacherService.findTecBySession(session);
        ScExample example = new ScExample();

        Criteria criteria = example.createCriteria();
        criteria.andTidEqualTo(teacher.getId());
        criteria.andCnameEqualTo(courseName);
        List<Sc> list = scMapper.selectByExample(example);
        return list;
    }

}
