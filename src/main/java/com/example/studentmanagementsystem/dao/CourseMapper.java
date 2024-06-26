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

    @Select("SELECT `choose_course`.`grade`,`course_info`.`name`,`course_info`.`id`" +
            "FROM `choose_course`" +
            "JOIN `course_info` ON" +
            "`course_info`.`id` = `choose_course`.`course_id`" +
            "WHERE `choose_course`.`student_id` = #{id};")
    List<CourseDTO> selectCourseInfo(Integer id);

    @Select("select count(*) from `choose_course` where course_id = #{id}")
    Integer selectCourseCount(Integer id);

    @Select("select max_count from `course_info` where id = #{id}")
    Integer selectMaxCount(Integer id);

    @Select("SELECT MIN(grade) FROM `choose_course` WHERE student_id = #{id} AND grade IS NOT NULL;")
    Integer minGrade(Integer id);
    @Select("SELECT MAX(grade) FROM `choose_course` WHERE student_id = #{id} AND grade IS NOT NULL;")
    Integer maxGrade(Integer id);

    @Select("SELECT avg(grade) FROM `choose_course` WHERE student_id = #{id} AND grade IS NOT NULL;")
    double avgGrade(Integer id);

    @Select("SELECT MIN(grade) FROM `choose_course` WHERE course_id = #{id} AND grade IS NOT NULL;")
    Integer minCourseGrade(Integer id);
    @Select("SELECT MAX(grade) FROM `choose_course` WHERE course_id = #{id} AND grade IS NOT NULL;")
    Integer maxCourseGrade(Integer id);

    @Select("SELECT avg(grade) FROM `choose_course` WHERE course_id = #{id} AND grade IS NOT NULL;")
    double avgCourseGrade(Integer id);


}



