package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.common.ErrorEnum;
import com.example.studentmanagementsystem.component.DefinitionException;
import com.example.studentmanagementsystem.pojo.College;
import com.example.studentmanagementsystem.service.impl.CollegeServerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/college")
@Slf4j
public class CollegeController {
    @Autowired
    CollegeServerImpl collegeServer;

    @GetMapping("/{id}")
    public Map<String,Object> getCollegeInfo(@PathVariable Integer id){
        College college = collegeServer.getCollegeInfo(id);
        if(college == null){
            log.error("学院不存在");
            throw new DefinitionException(ErrorEnum.ERROR);
        }
        log.info("学院信息：{}",college);
        return Map.of("id",college.getId(),"name",college.getName());
    }




}
