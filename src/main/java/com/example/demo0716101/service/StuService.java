package com.example.demo0716101.service;

import com.example.demo0716101.Dao.StuMapper;
import com.example.demo0716101.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuService {


    @Autowired
    private StuMapper stuMapper;

    public Integer addStu(Student student) {
        return stuMapper.addStu(student);
    }

    public Integer deleteStu(Integer id) {
        return stuMapper.deleteStu(id);
    }

    public Integer updateStu(Integer id) {
        return stuMapper.updateStu(id);
    }

   public List<Student> showAllStu(){
        return stuMapper.showAllStu();
    }

    public  Student selectOne(Integer id){return  stuMapper.selectOne(id);}


}
