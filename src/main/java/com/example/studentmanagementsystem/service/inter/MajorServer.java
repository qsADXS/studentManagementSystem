package com.example.studentmanagementsystem.service.inter;

import com.example.studentmanagementsystem.pojo.Major;

public interface MajorServer {
    Integer insertMajor(Major major);
    Major getMajorInfo(Integer id);
    void updateMajorInfo(Major major);
}
