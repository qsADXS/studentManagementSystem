package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.dto.StudentDTO;
import com.example.studentmanagementsystem.pojo.Student;
import com.example.studentmanagementsystem.service.impl.StudentServiceImpl;
import com.example.studentmanagementsystem.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")//表示这个controller的所有接口都是以/user开头的
@Slf4j
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;

    @GetMapping("/info/{id}")
    public StudentDTO getStudentInfo(@PathVariable String id, HttpServletRequest request){
        Claims claims = JwtUtils.getClaims(request);
        //todo 根据权限等级做不同判断

        log.info("id={}",id);
        Student student = studentService.getStudentInfo(id);
        return new StudentDTO(student);
    }



}
