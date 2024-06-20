package com.example.studentmanagementsystem.service.impl;

import com.example.studentmanagementsystem.dao.MajorMapper;
import com.example.studentmanagementsystem.pojo.College;
import com.example.studentmanagementsystem.pojo.Major;
import com.example.studentmanagementsystem.service.inter.MajorServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MajorServerImpl implements MajorServer {
    @Autowired
    MajorMapper majorMapper;
    @Override
    public Integer insertMajor(Major major) {
        majorMapper.insertMajor(major);
        return major.getId();
    }

    @Override
    public Major getMajorInfo(Integer id) {
        return majorMapper.selectMajorInfo(id);
    }

    @Override
    public void updateMajorInfo(Major major) {
        majorMapper.updateMajorInfo(major);
    }
}
