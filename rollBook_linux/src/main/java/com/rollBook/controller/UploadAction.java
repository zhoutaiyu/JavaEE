package com.rollBook.controller;

import com.rollBook.po.Course;
import com.rollBook.po.Student;
import com.rollBook.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
public class UploadAction {
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    AdminService adminService;
    @Autowired
    CourseService courseService;
    @Autowired
    ScService scService;
    @Autowired
    ProportionService proportionService;

    // 跳到上传图片的界面
    @RequestMapping("/uploadPic")
    public ModelAndView uploadPic(HttpSession session) throws Exception {
        Student student = (Student) session.getAttribute("activeUser");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("activeUser", student);
        // 指定逻辑视图名
        modelAndView.setViewName("uploadPic");
        return modelAndView;
    }

    // 跳转到上传excle界面
    @RequestMapping("/uploadExcle")
    public String uploadexcle(HttpSession session) throws Exception {
        //判断该老师是否已经填写成绩的评判标准
        if(proportionService.selectProById(session)==null){
            return "return";
        }
        return "/uploadExcle";
    }

    // 添加课程，并上传excle
    @RequestMapping("/uploadExcleSubmit")
    // public String editItemSubmit(Integer id,ItemsCustom itemsCustom,
    // ItemsQueryVo itemsQueryVo)throws Exception{
    public String uploadExcleSubmit(HttpSession session, Model model, HttpServletRequest request,
                                    // 上传excle
                                    MultipartFile excleFile) throws Exception {
        String msg = "";
        // 进行图片上传
        if (excleFile != null && excleFile.getOriginalFilename() != null
                && excleFile.getOriginalFilename().length() > 0) {
            //MultipartFile的getSize()方法返回文件字节数，excle不应该大于1024*50
            if (excleFile.getSize() > 1024 * 50) {
                msg = "抱歉，上传的excle不能大于50kb";
                request.setAttribute("msg", msg);
                return "/uploadExcle";
            }
            // 本地保存路径
            String filePath = "/usr/local/mydocker/apache-tomcat-9.0.1/webapps/rollResourse/excle/stuLogin";
            // 上传文件原始名称
            String originalFilename = excleFile.getOriginalFilename();
            // 新的文件名称
            String newFileName = UUID.randomUUID() + originalFilename;
            // 新文件
            File file = new File(filePath + newFileName);
            String url = filePath + newFileName;
            // 将内存中的文件写入磁盘
            excleFile.transferTo(file);
            // excle上传成功，添加upload记录和把学生信息录入系统
            teacherService.uploadExcle(url, session);
        }
        /**
         * enctype="multipart/form-data"
         * 形式除开文件数据只能通过下面的MultipartHttpServletRequest取
         */
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        String begin = multipartRequest.getParameter("beginDate");
        String end = multipartRequest.getParameter("endDate");
        String course_name = multipartRequest.getParameter("course_name");
        String class_name = multipartRequest.getParameter("class_name");
        if (course_name.isEmpty()) {
            request.setAttribute("msg", "课程名称不能为空");
            return "/uploadExcle";
        }
        if (class_name.isEmpty()) {
            request.setAttribute("msg", "班级名称不能为空");
            return "/uploadExcle";
        }
        if (begin.isEmpty()) {
            request.setAttribute("msg", "课程开始时间不能为空");
            return "/uploadExcle";
        }
        if (end.isEmpty()) {
            request.setAttribute("msg", "课程结束时间不能为空");
            return "/uploadExcle";
        }
        // string转为date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = formatter.parse(begin);
        Date endDate = formatter.parse(end);
        // 注入属性
        Course course = new Course();
        course.setBeginTime(beginDate);
        course.setEndTime(endDate);
        course.setCname(course_name);
        course.setClassName(class_name);
        courseService.addCourse(course, session);
        // 添加成功到course表之后，还有一步应该创建每位学生的成绩表
        scService.addSc(class_name, course_name, session);
        // 请求重定向
        request.setAttribute("msg", "添加成功");
        return "/uploadExcle";
        // 转发
        // return "forward:queryItems.action";
    }

    // 添加老师名单
    @RequestMapping("/uploadTecSubmit")

    public String uploadTecSubmit(HttpSession session, Model model, HttpServletRequest request, MultipartFile excleFile)
            throws Exception {
        //定义返回信息
        String msg = "";
        // 进行上传
        if (excleFile != null && excleFile.getOriginalFilename() != null
                && excleFile.getOriginalFilename().length() > 0) {
            // 上传文件原始名称
            String originalFilename = excleFile.getOriginalFilename();
            //判断是否为有效的文件后缀
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")); // 文件后辍.
            if (!suffix.equals(".xls") && !suffix.equals(".xlsx")) {
                msg = "不支持的文件类型，请导入excle表格";
                request.setAttribute("msg", msg);
                return "/uploadTec";
            }
            //MultipartFile的getSize()方法返回文件字节数，excle不应该大于1024*100
            if (excleFile.getSize() > 1024 * 100) {
                msg = "抱歉，上传的excle不能大于100kb";
                request.setAttribute("msg", msg);
                return "/uploadTec";
            }
            // 本地保存路径
            String filePath = "/usr/local/mydocker/apache-tomcat-9.0.1/webapps/rollResourse/excle/tecLogin/";

            // 新的文件名称
            String newFileName = UUID.randomUUID() + originalFilename;
            // 新文件
            File file = new File(filePath + newFileName);
            String url = filePath + newFileName;
            // 将内存中的文件写入磁盘
            excleFile.transferTo(file);
            // excle上传成功，添加upload记录和把学生信息录入系统
            adminService.uploadTecExcle(url, session);
        }
        msg = "添加名单成功，初始密码都为1234";
        request.setAttribute("msg", msg);
        return "/uploadTec";
    }

    // 上传图片提交
    @RequestMapping("/editstudentSubmit")
    // public String editItemSubmit(Integer id,ItemsCustom itemsCustom,
    // ItemsQueryVo itemsQueryVo)throws Exception{
    public String editImageSubmit(HttpSession session, Model model, HttpServletRequest request,
                                  // 上传图片
                                  MultipartFile pictureFile) throws Exception {
        //返回前台的msg
        String msg = "";
        // 进行图片上传
        if (pictureFile != null && pictureFile.getOriginalFilename() != null
                && pictureFile.getOriginalFilename().length() > 0) {
            // 本地保存路径
            String filePath = "/usr/local/mydocker/apache-tomcat-9.0.1/webapps/rollResourse/images/";
            // 上传文件原始名称
            String originalFilename = pictureFile.getOriginalFilename();
            //判断是否为有效的文件后缀
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")); // 文件后辍.
            if (!suffix.equalsIgnoreCase(".jpg") && !suffix.equalsIgnoreCase(".JPEG") && !suffix.equalsIgnoreCase(".png")) {
                msg = "不支持的文件类型，只支持jpg、jpeg、png三种格式";
                request.setAttribute("msg", msg);
                return "/uploadPic";
            }
            //MultipartFile的getSize()方法返回文件字节数，图片不应该大于1024*60
            if (pictureFile.getSize() > 1024 * 60) {
                msg = "上传的照片不能大于60kb";
                request.setAttribute("msg", msg);
                return "/uploadPic";
            }
            // 新的图片名称
            String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            // 新文件
            File file = new File(filePath + newFileName);

            // 将内存中的文件写入磁盘
            pictureFile.transferTo(file);
            Student student = (Student) session.getAttribute("activeUser");
            // 图片上传成功，将新图片地址写入数据库
            studentService.uploadPic(student.getSno(), newFileName);
            // 修改session的img地址
            student.setImage(newFileName);
            session.setAttribute("activeUser", student);
        }
        // 调用service接口更新商品信息
        // 提交后回到修改页面
        // return "editItem";
        // 请求重定向
        msg = "成功上传照片";
        request.setAttribute("msg", msg);
        return "/uploadPic";
        // 转发
        // return "forward:queryItems.action";
    }

    // 跳转到上传老师excle界面
    @RequestMapping("/uploadTec")
    public String uploadTec(HttpSession session) throws Exception {
        return "/uploadTec";
    }
}
