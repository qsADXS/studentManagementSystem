package com.example.studentmanagementsystem.dao;


import com.example.studentmanagementsystem.pojo.Major;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface MajorMapper {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Transactional
    @Insert("INSERT INTO `major_info` (`name`,`college_id`) VALUES (#{name},#{college_id});")
    void insertMajor(Major major);

    @Select("select * from major_info where id=#{id}")
    Major selectMajorInfo(Integer id);

    @Update("update major_info set name=#{name} where id=#{id}")
    void updateMajorInfo(Major major);

}
