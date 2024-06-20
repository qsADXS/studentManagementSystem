package com.example.studentmanagementsystem.controller;

import cn.hutool.core.convert.Convert;
import com.example.studentmanagementsystem.dto.StudentDTO;
import com.example.studentmanagementsystem.pojo.Student;
import com.example.studentmanagementsystem.service.impl.StudentServerImpl;
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
@RequestMapping("/student")//表示这个controller的所有接口都是以/user开头的
@Slf4j
public class StudentController {
    @Autowired
    StudentServerImpl studentService;

    @GetMapping("/info/{id}")
    public StudentDTO getStudentInfo(@PathVariable String id, HttpServletRequest request){
        Claims claims = JwtUtils.getClaims(request);
        /*
        *这里的claims是一个map，里面存放了token中的信息
        *int level = (int)claims.get("level");
        *String id = (String)claims.get("id");
        */
        //todo 根据权限等级做不同判断
        log.info("id={}",id);
        Student student = studentService.getStudentInfo(id);
        return new StudentDTO(student);
    }

    @PostMapping("/course/{id}")
    public Map<String, Object> grade(@PathVariable Integer id, HttpServletRequest request) {
        Claims claims = JwtUtils.getClaims(request);
        /*
         *这里的claims是一个map，里面存放了token中的信息
         *int level = (int)claims.get("level");
         *String id = (String)claims.get("id");
         */
        //获取当前登录学生的ID
        HttpSession session = request.getSession();
        String studentId = (String) session.getAttribute("Id");

        Integer i = studentService.addCourse(Convert.toLong(studentId), Convert.toLong(id));
        Map<String, Object> map = new HashMap<>();
        map.put("message","操作成功");
        return map;
    }
    @DeleteMapping("/course/{id}")
    public Map<String, Object> delCourse(@PathVariable Integer id, HttpServletRequest request) {
        Claims claims = JwtUtils.getClaims(request);
        /*
         *这里的claims是一个map，里面存放了token中的信息
         *int level = (int)claims.get("level");
         *String id = (String)claims.get("id");
         */
        //获取当前登录学生的ID
        HttpSession session = request.getSession();
        String studentId = (String) session.getAttribute("Id");
        Integer i = studentService.delCourse(Convert.toLong(studentId), Convert.toLong(id));
        Map<String, Object> map = new HashMap<>();
        map.put("message","操作成功");
        return map;
    }
    @GetMapping("/grade/{id}")
    public Map<String, Integer> getGrade(@PathVariable Integer id, HttpServletRequest request) {
        Claims claims = JwtUtils.getClaims(request);
        /*
         *这里的claims是一个map，里面存放了token中的信息
         *int level = (int)claims.get("level");
         *String id = (String)claims.get("id");
         */
        //获取当前登录学生的ID
        HttpSession session = request.getSession();
        String studentId = (String) session.getAttribute("Id");
        Integer grade= studentService.getCourseGrade(Convert.toLong(studentId), Convert.toLong(id));
        Map<String, Integer> map = new HashMap<>();
        map.put("grade",grade);
        return map;
    }


}
