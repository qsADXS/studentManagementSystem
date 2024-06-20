package com.example.studentmanagementsystem.controller;

import cn.hutool.json.JSONUtil;
import com.example.studentmanagementsystem.common.ErrorEnum;
import com.example.studentmanagementsystem.component.DefinitionException;
import com.example.studentmanagementsystem.dto.AdminDTO;
import com.example.studentmanagementsystem.pojo.Admin;
import com.example.studentmanagementsystem.service.impl.AdminServerImpl;
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

    @PutMapping("/user/phone")
    public void setAdminPhone(@RequestBody String json, HttpServletRequest request){
        Claims claims = JwtUtils.getClaims(request);
        int level = (int)claims.get("level");
        if(level < 3){
            log.error("权限不足");
            throw new DefinitionException(ErrorEnum.NO_PERMISSION);
        }
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
        int level = (int)JwtUtils.getClaims(request).get("level");
        if(level < 3){
            log.error("权限不足");
            throw new DefinitionException(ErrorEnum.NO_PERMISSION);
        }
        AdminDTO adminDTO = JSONUtil.toBean(json, AdminDTO.class, true);
        Admin admin = new Admin(adminDTO);
        Integer integer = adminService.insertAdmin(admin);
        return Map.of("id",integer);
    }

}
