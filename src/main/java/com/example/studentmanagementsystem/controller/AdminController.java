package com.example.studentmanagementsystem.controller;

import cn.hutool.json.JSONUtil;
import com.example.studentmanagementsystem.common.ErrorEnum;
import com.example.studentmanagementsystem.component.DefinitionException;
import com.example.studentmanagementsystem.dto.AdminDTO;
import com.example.studentmanagementsystem.pojo.*;
import com.example.studentmanagementsystem.service.impl.AdminServerImpl;
import com.example.studentmanagementsystem.service.impl.CollegeServerImpl;
import com.example.studentmanagementsystem.service.impl.MajorServerImpl;
import com.example.studentmanagementsystem.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    @Autowired
    AdminServerImpl adminService;
    @Autowired
    CollegeServerImpl collegeServer;
    @Autowired
    MajorServerImpl majorServer;

    @GetMapping("/info/{id}")
    public AdminDTO getAdminInfo(@PathVariable String id, HttpServletRequest request){
        verifyPermissions(request);
        Admin admin = adminService.getAdminInfo(Integer.valueOf(id));
        if(admin == null){
            log.error("管理员不存在");
            throw new DefinitionException(ErrorEnum.ERROR);
        }
        log.info("管理员信息：{}",admin);
        return new AdminDTO(admin);
    }

    @PutMapping("/user/phone")
    public void setAdminPhone(@RequestBody String json, HttpServletRequest request){
        Claims claims = JwtUtils.getClaims(request);
        verifyPermissions(request);
        Integer id = (Integer)claims.get("id");
        String phone = JSONUtil.parseObj(json).getStr("phone");
        Integer result = adminService.setAdminPhone(id, phone);
        if(result == 0){
            log.error("修改失败");
            throw new DefinitionException(ErrorEnum.ERROR);
        }
        log.info("修改成功");
    }

    @PostMapping("")
    public Map<String,Integer> addAdmin(@RequestBody String json, HttpServletRequest request){
        verifyPermissions(request);
        AdminDTO adminDTO = JSONUtil.toBean(json, AdminDTO.class, true);
        Admin admin = new Admin(adminDTO);
        Integer integer = adminService.insertAdmin(admin);
        return Map.of("id",integer);
    }

    @PostMapping("/college")
    public Map<String,Integer> addCollege(@RequestBody String json, HttpServletRequest request){
        verifyPermissions(request);
        College college = JSONUtil.toBean(json, College.class, true);
        Integer integer = collegeServer.insertCollege(college);
        return Map.of("id",integer);
    }

    @PutMapping("/college/{id}")
    public void updateCollegeInfo(@RequestBody String json, @PathVariable Integer id, HttpServletRequest request){
        verifyPermissions(request);
        College college = JSONUtil.toBean(json, College.class, true);
        college.setId(id);
        collegeServer.updateCollegeInfo(college);
    }

    @PostMapping("/major")
    public Map<String,Integer> addMajor(@RequestBody String json, HttpServletRequest request){
        verifyPermissions(request);
        Major major = JSONUtil.toBean(json, Major.class, true);
        Integer integer = majorServer.insertMajor(major);
        return Map.of("id",integer);
    }

    @PutMapping("/major/{id}")
    public void updateMajorInfo(@RequestBody String json, @PathVariable Integer id, HttpServletRequest request){
        verifyPermissions(request);
        Major major = JSONUtil.toBean(json, Major.class, true);
        major.setId(id);
        majorServer.updateMajorInfo(major);
    }


    @PutMapping("/password/{level}/{id}")
    public void updatePassword(@PathVariable Integer level, @PathVariable String id, HttpServletRequest request){
        verifyPermissions(request);
        Integer result = adminService.resetPassword(level, Integer.valueOf(id));
        if(result == 0){
            log.error("修改失败");
            throw new DefinitionException(ErrorEnum.ERROR);
        }
        log.info("修改成功");
    }

    @PostMapping("/course")
    public Map<String,Integer> addCourse(@RequestBody String json, HttpServletRequest request){
        verifyPermissions(request);
        Course course = JSONUtil.toBean(json, Course.class, true);
        Integer integer = adminService.insertCourse(course);
        return Map.of("id",integer);
    }

    @DeleteMapping("/course/{id}")
    public void deleteCourse(@PathVariable Integer id, HttpServletRequest request){
        verifyPermissions(request);
        Integer result = adminService.deleteCourse(id);
        if(result == 0){
            log.error("删除失败");
            throw new DefinitionException(ErrorEnum.ERROR);
        }
        log.info("删除成功");
    }
    @PostMapping("/teacher")
    public Map<String,Integer> addTeacher(@RequestBody String json, HttpServletRequest request){
        verifyPermissions(request);
        Teacher teacher = JSONUtil.toBean(json, Teacher.class, true);
        Integer integer = adminService.insertTeacher(teacher);
        return Map.of("id",integer);
    }
    @PostMapping("/student")
    public Map<String,Integer> addStudent(@RequestBody String json, HttpServletRequest request){
        verifyPermissions(request);
        Student student = JSONUtil.toBean(json, Student.class, true);
        Integer integer = adminService.insertStudent(student);
        return Map.of("id",integer);
    }

    @PutMapping("/teacher/{id}")
    public void updateTeacherTitle(@RequestBody String json, @PathVariable Integer id, HttpServletRequest request){
        verifyPermissions(request);
        Teacher teacher = JSONUtil.toBean(json, Teacher.class, true);
        teacher.setId(id);
        Integer result = adminService.updateTeacherTitle(teacher);
        if(result == 0){
            log.error("修改失败");
            throw new DefinitionException(ErrorEnum.ERROR);
        }
        log.info("修改成功");
    }

    @GetMapping("/students")
    public Map<String, Object> getStudentList(@RequestParam Integer page, @RequestParam Integer limit, HttpServletRequest request){
        verifyPermissions(request);
        if(page == null || limit == null || page < 1 || limit < 1){
            log.error("参数错误");
            throw new DefinitionException(ErrorEnum.ERROR);
        }
        return Map.of("data",adminService.getStudentList(page, limit));
    }

    @GetMapping("/teachers")
    public Map<String, Object> getTeacherList(@RequestParam Integer page, @RequestParam Integer limit, HttpServletRequest request){
        verifyPermissions(request);
        if(page == null || limit == null || page < 1 || limit < 1){
            log.error("参数错误");
            throw new DefinitionException(ErrorEnum.ERROR);
        }
        return Map.of("data",adminService.getTeacherList(page, limit));
    }






    private void verifyPermissions(HttpServletRequest request){
        Claims claims = JwtUtils.getClaims(request);
        int level = (int) claims.get("level");
        if(level < 3){
            log.error("权限不足");
            throw new DefinitionException(ErrorEnum.NO_PERMISSION);
        }
    }



}
