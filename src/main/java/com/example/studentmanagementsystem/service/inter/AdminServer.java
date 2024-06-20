package com.example.studentmanagementsystem.service.inter;

import com.example.studentmanagementsystem.pojo.Admin;


public interface AdminServer {
    Admin getAdminInfo(String id);
    Integer setAdminPhone(String id, String phone);
    Integer insertAdmin(Admin admin);
}
