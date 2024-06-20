package com.example.studentmanagementsystem.controller;

import cn.hutool.json.JSONUtil;
import com.example.studentmanagementsystem.common.ErrorEnum;
import com.example.studentmanagementsystem.component.DefinitionException;
import com.example.studentmanagementsystem.dto.AdminDTO;
import com.example.studentmanagementsystem.pojo.Admin;
import com.example.studentmanagementsystem.pojo.College;
import com.example.studentmanagementsystem.pojo.Major;
import com.example.studentmanagementsystem.service.impl.AdminServerImpl;
import com.example.studentmanagementsystem.service.impl.CollegeServerImpl;
import com.example.studentmanagementsystem.service.impl.MajorServerImpl;
import com.example.studentmanagementsystem.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
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
        Admin admin = adminService.getAdminInfo(id);
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
        String id = (String)claims.get("id");
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


    private void verifyPermissions(HttpServletRequest request){
        Claims claims = JwtUtils.getClaims(request);
        int level = (int) claims.get("level");
        if(level < 3){
            log.error("权限不足");
            throw new DefinitionException(ErrorEnum.NO_PERMISSION);
        }
    }

}
