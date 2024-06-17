package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.dto.LoginDTO;
import com.example.studentmanagementsystem.pojo.Student;
import com.example.studentmanagementsystem.service.impl.StudentServiceImpl;
import com.example.studentmanagementsystem.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    StudentServiceImpl studentService;
    @PostMapping("/login")
    public LoginDTO login(@RequestParam String id, @RequestParam String password, @RequestParam int level){
        log.info("login");
        if(level == 1) {
            log.info("学生登录");
            Student student = studentService.getStudentInfo(id);
            if(student.getPassword().equals(password)){
                log.info("登录成功");
                String token = JwtUtils.generateToken(id, level);
                LoginDTO loginDTO = new LoginDTO(token, id);
                return loginDTO;
            }else {
                //todo 登录失败操作，这里只是演示，应该要抛出一个自定义的错误，然后有个全局错误的配置会处理，但是还没写
                return null;
            }
        }else if(level == 2){
            log.info("教师登录");
            //todo 这里应该是教师的登录
        }else if(level == 3){
            log.info("管理员登录");
            //todo 这里应该是管理员的登录
        }
        else{
            //todo level错误
        }
        return null;
    }
}
