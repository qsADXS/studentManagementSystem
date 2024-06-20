package com.example.studentmanagementsystem.service.impl;

import com.example.studentmanagementsystem.dao.AdminMapper;
import com.example.studentmanagementsystem.pojo.Admin;
import com.example.studentmanagementsystem.service.inter.AdminServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServerImpl implements AdminServer {
    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin getAdminInfo(String id) {
        return adminMapper.selectStudentInfo(id);
    }

    @Override
    public Integer setAdminPhone(String id, String phone) {
        return adminMapper.updateAdminPhone(id, phone);
    }

    @Override
    public Integer insertAdmin(Admin admin) {
        adminMapper.insertAdmin(admin);
        return admin.getId();
    }
}
