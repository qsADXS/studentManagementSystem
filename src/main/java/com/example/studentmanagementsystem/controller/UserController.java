package com.example.studentmanagementsystem.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.studentmanagementsystem.common.ErrorEnum;
import com.example.studentmanagementsystem.component.DefinitionException;
import com.example.studentmanagementsystem.dto.LoginDTO;
import com.example.studentmanagementsystem.pojo.Admin;
import com.example.studentmanagementsystem.pojo.Student;
import com.example.studentmanagementsystem.pojo.Teacher;
import com.example.studentmanagementsystem.service.impl.AdminServerImpl;
import com.example.studentmanagementsystem.service.impl.StudentServerImpl;
import com.example.studentmanagementsystem.service.impl.TeacherServiceImpl;
import com.example.studentmanagementsystem.util.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")//表示这个controller的所有接口都是以/user开头的
@Slf4j
public class UserController {
    @Autowired
    StudentServerImpl studentService;
    @Autowired
    AdminServerImpl adminService;
    @Autowired
    TeacherServiceImpl teacherService;
    //表示一个post请求
    @PostMapping("/login/{level}")//表示这个接口的路径是/user/login/{level}，其中的level是一个参数
    public LoginDTO login(@PathVariable int level,@RequestBody String json, HttpServletRequest request){
        //↑ 这里的参数是从前端传过来的，@RequestParam表示这个参数是从url中获取的，PathVariable表示这个参数是从路径中获取的，RequestBody表示这个参数是从请求体中获取的
        log.info("login");
        //JSONObject是第三方工具hutool的类，用于解析json
        JSONObject jsonObject = JSONUtil.parseObj(json);
        String id = jsonObject.getStr("id");
        String password = jsonObject.getStr("password");
        String name = null;

        if(id == null || password == null){
            //参数错误，抛出异常
            throw new DefinitionException(ErrorEnum.ERROR);
        }

        if(level == 1) {
            log.info("学生登录");
            Student student = studentService.getStudentInfo(id);
            if( student != null && student.getPassword().equals(password)){
                name = student.getName();
                HttpSession session = request.getSession();
                session.setAttribute("Name", name);
                session.setAttribute("Id", student.getId());
            }else {
                //密码或账号错误，抛出异常
                throw new DefinitionException(ErrorEnum.NO_AUTH);
            }
        }else if(level == 2){
            log.info("教师登录");
            Teacher teacher = teacherService.getTeacherInfo(id);
            if( teacher != null && teacher.getPassword().equals(password)){
                name = teacher.getName();
                HttpSession session = request.getSession();
                session.setAttribute("Name", name);
                session.setAttribute("Id", teacher.getId());
            }else {
                //密码或账号错误，抛出异常
                throw new DefinitionException(ErrorEnum.NO_AUTH);
            }
        }else if(level == 3){
            log.info("管理员登录");
            Admin admin = adminService.getAdminInfo(id);

            if(admin != null && admin.getPassword().equals(password)){
                name = admin.getName();
            }else {
                //密码或账号错误，抛出异常
                throw new DefinitionException(ErrorEnum.NO_AUTH);
            }
        }
        else{
            throw new DefinitionException(ErrorEnum.ERROR);
        }


        log.info("登录成功");
        String token = JwtUtils.generateToken(id, level);
        return new LoginDTO(token, name);
    }
}
