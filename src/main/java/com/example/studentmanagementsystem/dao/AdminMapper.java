package com.example.studentmanagementsystem.dao;

import com.example.studentmanagementsystem.dto.StudentDTO;
import com.example.studentmanagementsystem.dto.TeacherDTO;
import com.example.studentmanagementsystem.pojo.Admin;
import com.example.studentmanagementsystem.pojo.Student;
import com.example.studentmanagementsystem.pojo.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface AdminMapper {
    @Select("select * from admin_info where id=#{id}")
    Admin selectStudentInfo(Integer id);
    @Update("UPDATE `admin_info` SET `phone` = #{phone} WHERE `id` = #{id}")
    @Transactional
    Integer updateAdminPhone(Integer id, String phone);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Transactional
    @Insert("INSERT INTO `teacher_info` (`name`, `sex`, `age`, `title`, `college_id`) VALUES (#{name},#{sex},#{age},#{title},#{college_id})")
    void insertTeacher(Teacher teacher);
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Transactional
    @Insert("INSERT INTO `admin_info` (`name`, `sex`, `age`, `phone`) VALUES (#{name},#{sex},#{age},#{phone})")
    void insertAdmin(Admin admin);
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Transactional
    @Insert("INSERT INTO `student_info` (`name`, `sex`, `age`, `major_id`) VALUES (#{name},#{sex},#{age},#{major_id})")
    void insertStudent(Student student);

    @Update("UPDATE `student_info` SET `password` = #{password} WHERE `id` = #{id}")
    @Transactional
    Integer updateStudentPassword(Integer id, String password);

    @Update("UPDATE `teacher_info` SET `password` = #{password} WHERE `id` = #{id}")
    @Transactional
    Integer updateTeacherPassword(Integer id, String password);

    @Transactional
    @Update("UPDATE `teacher_info` SET `title` = #{title} WHERE `id` = #{id}")
    Integer updateTeacherTitle(Teacher teacher);

    @Transactional
    @Select("SELECT `student_info`.`id`,`student_info`.`name`,`sex`,`age`,`major_info`.`name` AS 'major',`major_info`.`id` " +
            "FROM `student_info` " +
            "JOIN `major_info` ON " +
            "`student_info`.`major_id` = `major_info`.`id`" +
            "ORDER BY `student_info`.`id` ASC LIMIT #{offset},#{limit}")
    List<StudentDTO> selectStudentList(Integer offset, Integer limit);

    @Transactional
    @Select("SELECT `teacher_info`.`id`,`teacher_info`.`name`,`sex`,`age`,`title`,`college_id`,`college_info`.`name` AS 'collegeName' FROM `teacher_info`" +
            "JOIN `college_info` ON `college_info`.`id`=`teacher_info`.`college_id`" +
            "ORDER BY `teacher_info`.`id` ASC LIMIT #{offset},#{limit}")
    List<TeacherDTO> selectTeacherLister(Integer offset, Integer limit);
    @Update("UPDATE admin_info SET password = #{newPassword} WHERE id = #{id} AND password = #{password}")
    void updatePassword(Integer id, String password, String newPassword);

    @Update({
            "<script>",
            "UPDATE admin_info",
            "<set>",
            "<if test='name != null'> name = #{name}, </if>",
            "<if test='sex != null'> sex = #{sex}, </if>",
            "<if test='age != null'> age = #{age}, </if>",
            "<if test='phone != null'> phone = #{phone}, </if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"
    })
    void updateAdminInfo(Admin admin);
    @Select("SELECT COUNT(*) AS course_count FROM choose_course WHERE course_id=#{courseId} and ( grade BETWEEN #{lowScore} AND #{highScore} ) ;")
    Integer scoreNum(@Param("highScore") Integer highScore,@Param("lowScore") Integer lowScore,@Param("courseId") Integer courseId);

}
