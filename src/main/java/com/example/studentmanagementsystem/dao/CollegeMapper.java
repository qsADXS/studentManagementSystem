package com.example.studentmanagementsystem.dao;

import com.example.studentmanagementsystem.pojo.College;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface CollegeMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Transactional
    @Insert("INSERT INTO `college_info` (`name`) VALUES (#{name});")
    void insertCollege(College college);

    @Select("select * from college_info where id=#{id}")
    College selectCollegeInfo(Integer id);

    @Update("update college_info set name=#{name} where id=#{id}")
    void updateCollegeInfo(College college);

}
