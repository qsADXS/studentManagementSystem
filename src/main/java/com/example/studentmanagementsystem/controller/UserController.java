package com.example.studentmanagementsystem.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.studentmanagementsystem.common.ErrorEnum;
import com.example.studentmanagementsystem.component.DefinitionException;
import com.example.studentmanagementsystem.dto.LoginDTO;
import com.example.studentmanagementsystem.pojo.Admin;
import com.example.studentmanagementsystem.pojo.Major;
import com.example.studentmanagementsystem.pojo.Student;
import com.example.studentmanagementsystem.pojo.Teacher;
import com.example.studentmanagementsystem.service.impl.AdminServerImpl;
import com.example.studentmanagementsystem.service.impl.StudentServerImpl;
import com.example.studentmanagementsystem.service.impl.TeacherServerImpl;
import com.example.studentmanagementsystem.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")//表示这个controller的所有接口都是以/user开头的
@Slf4j
public class UserController {
    @Autowired
    StudentServerImpl studentService;
    @Autowired
    AdminServerImpl adminService;
    @Autowired
    TeacherServerImpl teacherService;
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
        password = SecureUtil.md5(password);

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
            Admin admin = adminService.getAdminInfo(Integer.valueOf(id));

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


    @PutMapping("/password")
    public Object password(@RequestBody Map<String,Object> json, HttpServletRequest request){
        Claims claims = JwtUtils.getClaims(request);
        int level = (int)claims.get("level");
        Integer id = Convert.toInt(claims.get("id")) ;

        String password = json.get("password").toString();
        String newPassword = json.get("newPassword").toString();
        if(newPassword == null || newPassword.equals("")){
            throw new DefinitionException(ErrorEnum.ERROR);
        }
        // 密码修改逻辑
        if (level==1){
            studentService.updatePassword(id, password,newPassword);
        }else if (level==2){
            teacherService.updatePassword(id, password,newPassword);
        }else if (level==3){
            adminService.updatePassword(id, password,newPassword);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("message", "操作成功");
        return map;
    }

    @PutMapping("")
    public void update(@RequestBody String json, HttpServletRequest request) {
        Claims claims = JwtUtils.getClaims(request);
        int level = (int) claims.get("level");
        Integer id = Convert.toInt(claims.get("id"));
        try {
            if (level == 3) {
                Admin admin = JSONUtil.toBean(json, Admin.class, true);
                admin.setId(id);
                adminService.updateInfo(admin);
            } else if (level == 2) {
                Teacher teacher = JSONUtil.toBean(json, Teacher.class, true);
                teacher.setId(id);
                teacherService.updateInfo(teacher);
            } else if (level == 1) {
                Student student = JSONUtil.toBean(json, Student.class, true);
                student.setId(id);
                studentService.updateInfo(student);
                System.out.println(123);
            }else {
                throw new DefinitionException(ErrorEnum.ERROR);
            }
        }catch (Exception e) {
            log.error("更新失败", e);
            throw new DefinitionException(ErrorEnum.ERROR);
        }

    }
}
