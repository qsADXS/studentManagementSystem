package com.example.studentmanagementsystem.service.inter;

import com.example.studentmanagementsystem.pojo.College;

public interface CollegeServer {
    Integer insertCollege(College college);
    College getCollegeInfo(Integer id);
    void updateCollegeInfo(College college);
}
