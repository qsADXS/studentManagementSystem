package com.example.studentmanagementsystem.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.studentmanagementsystem.common.ErrorEnum;
import com.example.studentmanagementsystem.component.DefinitionException;
import com.example.studentmanagementsystem.dto.LoginDTO;
import com.example.studentmanagementsystem.pojo.Student;
import com.example.studentmanagementsystem.service.impl.StudentServiceImpl;
import com.example.studentmanagementsystem.util.JwtUtils;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")//表示这个controller的所有接口都是以/user开头的
@Slf4j
public class UserController {
    @Autowired
    StudentServiceImpl studentService;
    //表示一个post请求
    @PostMapping("/login/{level}")//表示这个接口的路径是/user/login/{level}，其中的level是一个参数
    public LoginDTO login(@PathVariable int level,@RequestBody String json){
        //↑ 这里的参数是从前端传过来的，@RequestParam表示这个参数是从url中获取的，PathVariable表示这个参数是从路径中获取的，RequestBody表示这个参数是从请求体中获取的
        log.info("login");
        //JSONObject是第三方工具hutool的类，用于解析json
        JSONObject jsonObject = JSONUtil.parseObj(json);
        String id = jsonObject.getStr("id");
        String password = jsonObject.getStr("password");
        if(level == 1) {
            log.info("学生登录");
            Student student = studentService.getStudentInfo(id);
            if(student.getPassword().equals(password)){
                log.info("登录成功");
                String token = JwtUtils.generateToken(id, level);
                LoginDTO loginDTO = new LoginDTO(token, student.getName());
                return loginDTO;
            }else {
                //密码或账号错误，抛出异常
                throw new DefinitionException(ErrorEnum.NO_AUTH);
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
