package com.example.studentmanagementsystem.service.impl;

import com.example.studentmanagementsystem.dao.CollegeMapper;
import com.example.studentmanagementsystem.dao.TeacherMapper;
import com.example.studentmanagementsystem.pojo.College;
import com.example.studentmanagementsystem.service.inter.CollegeServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CollegeServerImpl implements CollegeServer {
    @Autowired
    CollegeMapper collegeMapper;
    @Override
    @Transactional
    public Integer insertCollege(College college) {
        collegeMapper.insertCollege(college);
        return college.getId();
    }

    @Override
    public College getCollegeInfo(Integer id) {
        return collegeMapper.selectCollegeInfo(id);
    }

    @Override
    public void updateCollegeInfo(College college) {
        collegeMapper.updateCollegeInfo(college);
    }
}
