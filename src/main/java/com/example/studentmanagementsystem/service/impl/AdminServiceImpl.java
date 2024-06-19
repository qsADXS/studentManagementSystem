package com.example.studentmanagementsystem.service.impl;

import com.example.studentmanagementsystem.dao.AdminMapper;
import com.example.studentmanagementsystem.pojo.Admin;
import com.example.studentmanagementsystem.service.inter.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin getAdminInfo(String id) {
        return adminMapper.selectStudentInfo(id);
    }
}
