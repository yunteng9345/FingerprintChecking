package com.example.demo0716101.Dao;

import com.example.demo0716101.model.Check;
import com.example.demo0716101.model.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StuMapper {

    List<Student> showAllStu();
    Integer deleteStu(String id);
    Integer updateStu(Integer id);
    Integer addStu(Student student);
    Student selectOne(Student student);
    List<Check> search(Check check);
}
