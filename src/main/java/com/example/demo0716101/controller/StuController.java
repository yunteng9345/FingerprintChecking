package com.example.demo0716101.controller;

import com.example.demo0716101.model.Student;
import com.example.demo0716101.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.Date;


@Controller
@RequestMapping("/stu")
public class StuController {

    /*
     *显示所有学生的信息
     * */
    @Autowired
    private StuService stuService;

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showAllStudent(ModelMap mode) {
        mode.addAttribute("stu", stuService.showAllStu());
        mode.addAttribute("student", new Student());
        return "admin-role";
    }

    /*
    首页
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showAllStudent() {
        return "login";
    }

    /*
        首页
         */
    @RequestMapping(value = "/index1", method = RequestMethod.GET)
    public String index1() {
        return "index1";
    }

    /*
     * 点击添加学生按钮
     * */
    @RequestMapping(value = "/clickStu")
    public String clickStu(ModelMap mode) {
        mode.addAttribute("student", new Student());
        return "admin-role-add";
    }

    /*
     * 添加学生
     * */
    @RequestMapping(value = "/addStu")
    public String addStu(@ModelAttribute(value = "student") Student student) throws IOException {
        Date date = new Date();
        student.setUrtime(date);
        stuService.addStu(student);


        /*
        for wifi module send data
         */
//        StringBuffer sendStr = new StringBuffer();
//        sendStr.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//        sendStr.append("<data>");
//        sendStr.append("<flag>add</flag>");
//        sendStr.append("<fid>" + student.getUfingerid() + "</fid>");
//        sendStr.append("</data>");
//        BufferedReader reader = null;
//
//        String strMessage = "";
//        StringBuffer buffer = new StringBuffer();
//// 接报文的地址
//        URL uploadServlet = new URL("http://192.168.43.25:80?fid=2");
//        HttpURLConnection servletConnection = (HttpURLConnection) uploadServlet.openConnection();
//// 设置连接参数
//        servletConnection.setRequestMethod("GET");
//        servletConnection.setDoOutput(true);
//        servletConnection.setDoInput(true);
//        servletConnection.setAllowUserInteraction(true);
//// 开启流，写入XML数据
//        OutputStream output = servletConnection.getOutputStream();
//        System.out.println("发送的报文：");
//        System.out.println(sendStr.toString());
//        output.write(sendStr.toString().getBytes());
//        output.flush();
        //output.close();

        //
        //
        //
        //
        return "redirect:show";
    }

    /*
     * 删除学生信息
     * */
    @RequestMapping(value = "/delet")
    public String deletStu(String id) {

        stuService.deleteStu(id);
        return "redirect:show";
    }


}
