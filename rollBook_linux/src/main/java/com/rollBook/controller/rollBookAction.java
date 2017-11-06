package com.rollBook.controller;

import com.rollBook.po.Record;
import com.rollBook.po.Sc;
import com.rollBook.po.Student;
import com.rollBook.povo.RecordVo;
import com.rollBook.povo.StudentVo;
import com.rollBook.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class rollBookAction {
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    CourseService courseService;
    @Autowired
    RecordService recordService;
    @Autowired
    ScService scService;
    @Autowired
    ProportionService proportionService;

    /**
     * 跳转页面为点名册页面，包含点名册页面（包含班级学生的该科目的成绩）和添加记录界面（传递课程名，之后使用ajax）。
     *
     * @param session
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/rollBook")
    public String rollBook(HttpSession session, HttpServletRequest request) throws Exception {
        //判断该老师是否已经填写成绩的评判标准
        if (proportionService.selectProById(session) == null) {
            return "return";
        }
        List<String> MyCname = courseService.findCourse(session);
        // 填充到request域
        request.setAttribute("MyCname", MyCname);
        // 转发到jsp页面显示
        return "/roll";
    }

    /**
     * 根据tid和课程名查找所教的班级
     *
     * @param session
     * @param request
     * @param cName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findClassesByCname", method = RequestMethod.POST)
    public @ResponseBody
    List<String> findClassesByCourse(HttpSession session, HttpServletRequest request, String cName)
            throws Exception {
        List<String> classes = courseService.findClassesByCname(session, cName);
        // 转发到前台
        return classes;
    }

    /**
     * 判断字段有无空，有就返回
     *
     * @param session
     * @param record
     * @param happen_time
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/addRecord")
    public String addRecord(HttpSession session, Record record, String class_Name, String happen_time,
                            HttpServletRequest request) throws Exception {
        if ("请选择：".equals(record.getCname()) || record.getCname().isEmpty()) {
            request.setAttribute("msg", "请填写课程名。");
            List<String> MyCname = courseService.findCourse(session);
            // 填充到request域
            request.setAttribute("MyCname", MyCname);
            return "/roll";
        }
        if ("请选择：".equals(class_Name) || class_Name.isEmpty()) {
            request.setAttribute("msg", "请选择班级。");
            List<String> MyCname = courseService.findCourse(session);
            // 填充到request域
            request.setAttribute("MyCname", MyCname);
            return "/roll";
        }
        if ("请选择：".equals(record.getSno()) || record.getSno().isEmpty()) {
            request.setAttribute("msg", "请选择学生。");
            List<String> MyCname = courseService.findCourse(session);
            // 填充到request域
            request.setAttribute("MyCname", MyCname);
            return "/roll";
        }
        if (happen_time.isEmpty()) {
            request.setAttribute("msg", "请选择时间。");
            List<String> MyCname = courseService.findCourse(session);
            // 填充到request域
            request.setAttribute("MyCname", MyCname);
            return "/roll";
        }
        if ("请选择：".equals(record.getThing()) || record.getThing().equals("")) {
            request.setAttribute("msg", "请选择事件。");
            List<String> MyCname = courseService.findCourse(session);
            // 填充到request域
            request.setAttribute("MyCname", MyCname);
            return "/roll";
        }
        if ("请选择：".equals(record.getRweek()) || record.getRweek().equals("")) {
            request.setAttribute("msg", "请选择周次。");
            List<String> MyCname = courseService.findCourse(session);
            // 填充到request域
            request.setAttribute("MyCname", MyCname);
            return "/roll";
        }
        if ("请选择：".equals(record.getRday()) || record.getRday().equals("")) {
            request.setAttribute("msg", "请选择星期几。");
            List<String> MyCname = courseService.findCourse(session);
            // 填充到request域
            request.setAttribute("MyCname", MyCname);
            return "/roll";
        }
        if ("请选择：".equals(record.getRsection()) || record.getRsection().equals("")) {
            request.setAttribute("msg", "请选择节次。");
            List<String> MyCname = courseService.findCourse(session);
            // 填充到request域
            request.setAttribute("MyCname", MyCname);
            return "/roll";
        }
        recordService.addRecord(session, record, happen_time);
        // 转发到jsp页面显示
        request.setAttribute("msg", "添加成功。");
        List<String> MyCname = courseService.findCourse(session);
        // 填充到request域
        request.setAttribute("MyCname", MyCname);
        return "/roll";
    }

    @RequestMapping(value = "/findRoll", method = RequestMethod.POST)
    public @ResponseBody
    List<StudentVo> findRoll(String className, String cName, HttpServletRequest request,
                             HttpSession session) throws Exception {
        // 获取该老师教的这门课的所有成绩表
        List<Sc> scs = scService.findScByTidAndCname(cName, session);
        // 渲染StudentVo，包括填充学生student表和record表和sc表。
        List<StudentVo> list = studentService.render(scs, cName);
        //去掉多余的创建时间、修改时间等无用数据
        List<StudentVo> CleanList = new ArrayList<StudentVo>();
        for (int i = 0; i < list.size(); i++) {
            StudentVo a = list.get(i);
            //密码在前台没有用，传递就是增加危险
            a.setPassword(null);
            //图片传递会导致运行效率低下
            a.setImage(null);
            a.setCreateTime(null);
            a.setModTime(null);
            a.setIsDel(null);
            CleanList.add(a);
        }
        return CleanList;
    }

    /**
     * 此页面将会引导进入SelectEvents.jsp
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/beforeSelectEvents")
    public String beforeSelectEvents(HttpSession session, HttpServletRequest request) throws Exception {
        //判断该老师是否已经填写成绩的评判标准
        if (proportionService.selectProById(session) == null) {
            return "return";
        }
        List<String> MyCname = courseService.findCourse(session);
        // 填充到request域
        request.setAttribute("MyCname", MyCname);
        // 转发到jsp页面显示
        return "/SelectEvents";
    }

    /**
     * @param cName
     * @param className
     * @param request
     * @param session
     * @return
     * @throws Exception
     */
    //selectEvents
    @RequestMapping(value = "/selectEvents", method = RequestMethod.POST)
    public @ResponseBody
    List<RecordVo> selectEvents(String cName, String className, HttpServletRequest request,
                                HttpSession session) throws Exception {
        List<RecordVo> lists = recordService.findReBycnameAndclassName(cName, className, session);
        //去掉多余的创建时间、修改时间等无用数据
        List<RecordVo> CleanList = new ArrayList<RecordVo>();
        for (int i = 0; i < lists.size(); i++) {
            RecordVo a = lists.get(i);
            a.setModTime(null);
            a.setIsDel(null);
            CleanList.add(a);
        }
        return CleanList;
    }

    /**
     * 老师添加违纪记录时
     * 将传送的list净化再传递，特别是用户密码
     *
     * @param class_Name
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findStudentWithoutImage", method = RequestMethod.POST)
    public @ResponseBody
    List<Student> findStudentWithoutImage(String class_Name, HttpServletRequest request) throws Exception {
        List<Student> list = studentService.findPicsByClassSome(class_Name);
        //去掉多余的创建时间、修改时间等无用数据,在这里前台只需要学号和姓名
        List<Student> CleanList = new ArrayList<Student>();
        for (int i = 0; i < list.size(); i++) {
            Student s = new Student();
            s.setSno(list.get(i).getSno());
            s.setName(list.get(i).getName());
            CleanList.add(s);
        }
        return CleanList;
    }
}
