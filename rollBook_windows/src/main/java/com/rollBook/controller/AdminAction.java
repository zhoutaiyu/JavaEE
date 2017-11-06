package com.rollBook.controller;

import com.rollBook.po.Teacher;
import com.rollBook.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminAction {
    @Autowired
    private TeacherService teacherService;

    /**
     * 查询所有老师信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectTea")
    public String selectTea(HttpServletRequest request) throws Exception {
        List<Teacher> teacherList = teacherService.selectAllTeacher();
        request.setAttribute("tlist", teacherList);
        return "showTeachers";
    }

    /**
     * 根据组合条件查询，跳转到填写查询条件页面
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectTeaByCondition")
    public String selectTeaByCondition(HttpServletRequest request) throws Exception {
        return "/showCondition";
    }

    /**
     * 条件查询后将信息填充到编辑jsp
     *
     * @param request
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/conditionSubmit")
    public String conditionSubmit(HttpServletRequest request,Teacher teacher) throws Exception {
        List<Teacher> tlist = teacherService.findTecByCondition(teacher);
        request.setAttribute("tlist", tlist);
        return "showTeachers";
    }

    /**
     * 跳转到要修改的老师的信息页面
     *
     * @param request
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editTea", method = RequestMethod.GET)
    public String editTea(HttpServletRequest request, Long id) throws Exception {
        Teacher teacher = teacherService.findTecByid(id);
        request.setAttribute("tea", teacher);
        return "/editTec";
    }

    /**
     * 根据工号删除老师信息
     *
     * @param request
     * @param wid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteTea", method = RequestMethod.GET)
    public String deleteTea(HttpServletRequest request, Long wid) throws Exception {
        teacherService.deleteTeaByWid(wid);
        request.setAttribute("msg", "删除成功");
        return "/selectTea";
    }

    /**
     * 批量删除
     *
     * @param request
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteTecs", method = RequestMethod.POST)
    public String deleteTecs(HttpServletRequest request,String[] id ) throws Exception {
        for(int i=0;i<id.length;i++){
            Long id1 =Long.parseLong(id[i]);
            teacherService.deleteTeaByid(id1);
        }
        request.setAttribute("msg", "删除成功");
        return "/selectTea";
    }

    /**
     * 提交修改数据到数据库，完成修改
     *
     * @param request
     * @param teacher
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editTeaSubmit", method = RequestMethod.POST)
    public String editTeaSubmit(HttpServletRequest request, Teacher teacher) throws Exception {
        teacherService.updateTea(teacher);
        request.setAttribute("tea", teacher);
        request.setAttribute("msg", "修改成功");
        return "/editTec";
    }
}
