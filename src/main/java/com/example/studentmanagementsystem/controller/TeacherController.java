package com.example.studentmanagementsystem.controller;

import cn.hutool.core.convert.Convert;
import com.example.studentmanagementsystem.dto.TeacherDTO;
import com.example.studentmanagementsystem.pojo.Teacher;
import com.example.studentmanagementsystem.service.inter.TeacherServer;
import com.example.studentmanagementsystem.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/teacher")//表示这个controller的所有接口都是以/user开头的
@Slf4j
public class TeacherController {
    @Autowired
    TeacherServer teacherServer;

    @GetMapping("/info/{id}")
    public TeacherDTO getTeacherInfo(@PathVariable String id, HttpServletRequest request) {
        Claims claims = JwtUtils.getClaims(request);
        /*
         *这里的claims是一个map，里面存放了token中的信息
         *int level = (int)claims.get("level");
         *String id = (String)claims.get("id");
         */
        log.info("id={}", id);
        Teacher teacher = teacherServer.getTeacherInfo(id);
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(teacher.getId());
        teacherDTO.setAge(teacher.getAge());
        teacherDTO.setSex(teacher.getSex());
        teacherDTO.setName(teacher.getName());
        teacherDTO.setTitle(teacher.getTitle());
        teacherDTO.setCollege_id(teacher.getCollege_id());
        return teacherDTO;
    }

    @PostMapping("/grade/{crouseId}/{studentId}")
    public Object grade(@PathVariable String crouseId, @PathVariable String studentId, @RequestBody Map<String, Object> gradeData, HttpServletRequest request) {
        Claims claims = JwtUtils.getClaims(request);
        /*
         *这里的claims是一个map，里面存放了token中的信息
         *int level = (int)claims.get("level");
         *String id = (String)claims.get("id");
         */
        //获取当前登录老师的ID
        int level = (int)claims.get("level");
        String teacherId = (String)claims.get("id");
//        HttpSession session = request.getSession();
//        String teacherId = (String) session.getAttribute("Id");
//        Integer level = (Integer) session.getAttribute("level");

        //查询老师课程ID集合
        List<Long> courseIds = teacherServer.getCourseIds(teacherId);

        if (level==3){
            Integer result = teacherServer.setGrade(teacherId, crouseId, studentId, (Integer) gradeData.get("grade"));
        }else if (level==2){
            //判断如果是该老师的课程进行添加课程分数
            if (courseIds.contains(Convert.toLong(crouseId))) {
                Integer result = teacherServer.setGrade(teacherId, crouseId, studentId, (Integer) gradeData.get("grade"));
            }
        }


        Map<String, Object> map = new HashMap<>();
        map.put("message", "操作成功");
        return map;
    }

}
