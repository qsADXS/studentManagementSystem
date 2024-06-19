package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.common.ErrorEnum;
import com.example.studentmanagementsystem.component.DefinitionException;
import com.example.studentmanagementsystem.dto.AdminDTO;
import com.example.studentmanagementsystem.dto.StudentDTO;
import com.example.studentmanagementsystem.pojo.Admin;
import com.example.studentmanagementsystem.pojo.Student;
import com.example.studentmanagementsystem.service.impl.AdminServiceImpl;
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
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    @Autowired
    AdminServiceImpl adminService;

    @GetMapping("/info/{id}")
    public AdminDTO getAdminInfo(@PathVariable String id, HttpServletRequest request){
        Claims claims = JwtUtils.getClaims(request);
        int level = (int)claims.get("level");
        if(level < 3){
            log.error("权限不足");
            throw new DefinitionException(ErrorEnum.NO_PERMISSION);
        }
        Admin admin = adminService.getAdminInfo(id);
        if(admin == null){
            log.error("管理员不存在");
            throw new DefinitionException(ErrorEnum.ERROR);
        }
        log.info("管理员信息：{}",admin);
        return new AdminDTO(admin);
    }


}
