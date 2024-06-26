package com.example.studentmanagementsystem.dao;


import com.example.studentmanagementsystem.pojo.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TeacherMapper {
    @Select("SELECT * FROM teacher_info WHERE id = #{id}")
    Teacher getTeacherInfo(String id);

    @Update("UPDATE choose_course SET grade = #{grade} WHERE course_id = #{courseId} AND student_id = #{studentId}")
    Integer setGrade(@Param("teacherId") String teacherId, @Param("courseId") Long courseId, @Param("studentId") Long studentId, @Param("grade") Integer grade);

    @Select("SELECT id FROM course_info WHERE teacher_id = #{teacherId}")
    List<Long> getCourseIds(Integer teacherId);

    @Update("UPDATE teacher_info SET password = #{newPassword} WHERE id = #{id} AND password = #{password}")
    void updatePassword(@Param("id") Integer id,@Param("password") String password,@Param("newPassword") String newPassword);

    @Update({
            "<script>",
            "UPDATE teacher_info",
            "<set>",
            "<if test='name != null'> name = #{name}, </if>",
            "<if test='sex != null'> sex = #{sex}, </if>",
            "<if test='age != null'> age = #{age}, </if>",
            "<if test='title != null'> title = #{title}, </if>",
            "<if test='college_id != null'> college_id = #{college_id}, </if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"
    })
    void updateTeacherInfo(Teacher teacher);

}
