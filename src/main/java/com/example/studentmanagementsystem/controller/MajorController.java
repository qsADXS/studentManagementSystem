package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.common.ErrorEnum;
import com.example.studentmanagementsystem.component.DefinitionException;
import com.example.studentmanagementsystem.pojo.College;
import com.example.studentmanagementsystem.pojo.Major;
import com.example.studentmanagementsystem.service.impl.CollegeServerImpl;
import com.example.studentmanagementsystem.service.impl.MajorServerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/major")
@Slf4j
public class MajorController {
    @Autowired
    MajorServerImpl majorServer;

    @GetMapping("/{id}")
    public Map<String,Object> getMajorInfo(@PathVariable Integer id){
        Major major = majorServer.getMajorInfo(id);
        if(major == null){
            log.error("专业不存在");
            throw new DefinitionException(ErrorEnum.ERROR);
        }
        return Map.of("id",major.getId(),"name",major.getName(),"college_id",major.getCollege_id());
    }
}
