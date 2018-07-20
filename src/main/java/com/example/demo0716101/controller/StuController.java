package com.example.demo0716101.controller;

import com.example.demo0716101.model.Student;
import com.example.demo0716101.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/stu")
public class StuController {
    @Autowired
    private StuService stuService;


    @RequestMapping("/show")
    public String showAllStudent(ModelMap mode){
        mode.addAttribute("stu",stuService.showAllStu());
        mode.addAttribute("student",new Student());
        return  "index";

    }

    @RequestMapping(value = "/addStu",method = RequestMethod.POST)
    public String addStu(@ModelAttribute(value="student")Student  student){
        stuService.addStu(student);
        return  "redirect:show";

    }

    @RequestMapping(value = "/delet",method = RequestMethod.GET)
    public String deletStu(Integer id){
        stuService.deleteStu(id);
        return  "redirect:show";

    }
    @RequestMapping(value = "/selectOne",method = RequestMethod.POST)
    public String selectOne(@ModelAttribute(value="stu1")Student  student,ModelMap mode){
        stuService.selectOne(student.getId());
        mode.addAttribute("stu1",new Student());
        System.out.println(student.getId());
        return  "redirect:show";
    }



}
