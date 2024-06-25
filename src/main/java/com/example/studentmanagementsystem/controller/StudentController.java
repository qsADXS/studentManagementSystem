package com.example.studentmanagementsystem.controller;

import cn.hutool.core.convert.Convert;
import com.example.studentmanagementsystem.common.ErrorEnum;
import com.example.studentmanagementsystem.component.DefinitionException;
import com.example.studentmanagementsystem.dto.CourseDTO;
import com.example.studentmanagementsystem.dto.StudentDTO;
import com.example.studentmanagementsystem.pojo.Student;

import com.example.studentmanagementsystem.service.impl.StudentServerImpl;
import com.example.studentmanagementsystem.service.inter.TeacherServer;
import com.example.studentmanagementsystem.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {
    @Autowired
    StudentServerImpl studentService;
    @Autowired
    TeacherServer teacherServer;

    @GetMapping("/info/{id}")
    public StudentDTO getStudentInfo(@PathVariable String id, HttpServletRequest request){
        Claims claims = JwtUtils.getClaims(request);
        /*
         *这里的claims是一个map，里面存放了token中的信息
         *int level = (int)claims.get("level");
         *String id = (String)claims.get("id");
         */
        int level = (int)claims.get("level");
        String userId = (String)claims.get("id");
//        HttpSession session = request.getSession();
//        String Id = (String) session.getAttribute("Id");
//        Integer level = (Integer) session.getAttribute("level");
        if(level == 1){
            if(!Objects.equals(userId, id)){
                throw new DefinitionException(ErrorEnum.NO_PERMISSION);
            }
        }
        Student student = studentService.getStudentInfo(id);
        if(student == null){
            throw new DefinitionException(ErrorEnum.ERROR);
        }
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
        String studentId = (String)claims.get("id");
//        HttpSession session = request.getSession();
//        String studentId = (String) session.getAttribute("Id");

        studentService.addCourse(Convert.toLong(studentId), Convert.toLong(id));
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
//        HttpSession session = request.getSession();
//        String studentId = (String) session.getAttribute("Id");
        String studentId = (String)claims.get("id");
        Integer i = studentService.delCourse(Convert.toLong(studentId), Convert.toLong(id));
        Map<String, Object> map = new HashMap<>();
        map.put("message","操作成功");
        return map;
    }
    @GetMapping("/grade/{id}")
    public Map<String, Object> getGrade(@PathVariable Integer id, HttpServletRequest request) {
        Claims claims = JwtUtils.getClaims(request);
        /*
         *这里的claims是一个map，里面存放了token中的信息
         *int level = (int)claims.get("level");
         *String id = (String)claims.get("id");
         */
        //获取当前登录学生的ID
        int level = (int)claims.get("level");
        String Id = (String)claims.get("id");
//        HttpSession session = request.getSession();
//        String Id = (String) session.getAttribute("Id");
//        Integer level = (Integer) session.getAttribute("level");
        Map<String, Object> map = new HashMap<>();
        if (level==3){
            List<Map<String,Object>> maps= studentService.getAllCourseGrade(Convert.toLong(id));
            map.put("grades",maps);
        }else if (level==2){
            List<Long> courseIds = teacherServer.getCourseIds(Id);
//            List<Map<String,Object>> maps= studentService.getTeacherCourseGrade(courseIds, Convert.toLong(id));
            List<Map<String,Object>> maps= studentService.getAllCourseGrade(Convert.toLong(id));
            map.put("grades",maps);
        }else if (level==1){
            Integer grade= studentService.getCourseGrade(Convert.toLong(Id), Convert.toLong(id));
            map.put("grade",grade);
        }

        return map;
    }

    @GetMapping("/course/{id}")
    public Map<String,List<CourseDTO>> getAllStudentInfo(@PathVariable Integer id,HttpServletRequest request){
        Claims claims = JwtUtils.getClaims(request);
        int level = (int)claims.get("level");
        if(level == 1){
            Integer userId = (Integer)claims.get("id");
            if(!Objects.equals(userId, id)){
                throw new DefinitionException(ErrorEnum.NO_PERMISSION);
            }
        }
        studentService.getAllCourse(id);
        return Map.of("data",studentService.getAllCourse(id));
    }



}
