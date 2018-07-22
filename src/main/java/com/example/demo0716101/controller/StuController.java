package com.example.demo0716101.controller;
import com.example.demo0716101.model.Student;
import com.example.demo0716101.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;


@Controller
@RequestMapping("/stu")
public class StuController {

    /*

    *显示所有学生的信息
    * */
    @Autowired
    private StuService stuService;
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public String showAllStudent(ModelMap mode){
        mode.addAttribute("stu",stuService.showAllStu());
        mode.addAttribute("student",new Student());
        return  "index";
    }

    /*
    * 添加学生
    * */
    @RequestMapping(value = "/addStu")
    public String addStu(@ModelAttribute(value="student")Student  student){
        Date date =new Date();
        student.setUrtime(date);
        stuService.addStu(student);
        return  "redirect:show";
    }
    /*
    * 删除学生信息
    * */
    @RequestMapping(value = "/delet")
    public String deletStu(String id){

        stuService.deleteStu(id);
        return  "redirect:show";
    }


}
