package com.rollBook.service;

import com.rollBook.po.Teacher;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 周太宇
 * @date 2017年8月11日 下午10:58:48
 **/

public interface TeacherService {
    // 通过姓名查找teacher记录
    public Teacher findTecByName(String name) throws Exception;

    // 通过工号wid查找teacher记录
    public Teacher findTecByWid(Long wid) throws Exception;

    // 通过id查找teacher记录
    public Teacher findTecByid(Long id) throws Exception;

    // 通过tid查找teacher记录
    public Teacher findTeacherByTid(Long id) throws Exception;

    // 通过session返回老师信息
    public Teacher findTecBySession(HttpSession session) throws Exception;

    // 验证身份
    public Teacher authenticat(String usercode, String password) throws Exception;

    // 修改密码,并返回信息
    public String updatePwd(String oldPwd, String newPwd, HttpSession session) throws Exception;

    // 上传点名册并添加学生信息
    public void uploadExcle(String url, HttpSession session) throws Exception;

    // 查找所有老师信息，方便管理员管理
    public List<Teacher> selectAllTeacher() throws Exception;

    //根据主键tid修改老师信息
    public void updateTea(Teacher teacher) throws Exception;

    //根据wid删除老师信息
    public void deleteTeaByWid(Long wid) throws Exception;
    //根据id删除老师信息
    public void deleteTeaByid(Long id) throws Exception;

    //通过条件查询老师信息
    public List<Teacher> findTecByCondition(Teacher teacher) throws Exception;
}
