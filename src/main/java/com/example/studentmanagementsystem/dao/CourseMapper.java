package com.example.studentmanagementsystem.dao;


import com.example.studentmanagementsystem.dto.CourseDTO;
import com.example.studentmanagementsystem.pojo.Course;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface CourseMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Transactional
    @Insert("INSERT INTO `course_info` (`name`,`credit`,`teacher_id`) VALUES (#{name},#{credit},#{teacher_id});")
    void insertMajor(Course course);

    @Transactional
    @Delete("delete from course_info where id=#{id}")
    Integer deleteCourse(Integer id);

    @Transactional
    @Delete("DELETE FROM `choose_course` WHERE `course_id` = #{course_id}")
    Integer deleteChooseCourse(Integer course_id);

    @Select("SELECT `choose_course`.`grade`,`college_info`.`name`" +
            "FROM `choose_course`" +
            "JOIN `college_info` ON" +
            "`college_info`.`id` = `choose_course`.`course_id`" +
            "WHERE `choose_course`.`student_id` = #{id};")
    List<CourseDTO> selectCourseInfo(Integer id);












}



