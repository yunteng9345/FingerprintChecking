package com.example.demo0716101.controller;

import com.example.demo0716101.model.Check;
import com.example.demo0716101.model.Student;
import com.example.demo0716101.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/check")
public class CheckingController {

    @Autowired
    private CheckService checkService;
    /*
    * 单片机发送指纹信息*/
    @RequestMapping(value = "/scm",method = RequestMethod.GET)
    public String showAllStudent(ModelMap mode,String fid){
        System.out.println(fid);
        //1，首先查询是不是今天第一次打卡
        Check check =new Check();
        check.setFid(fid);
        checkService.selectOne(check);
        System.out.println("返回值："+checkService.selectOne(check));
        //2，如果是第一次打卡，就插入fid和第一次打卡的时间。反之，就update第二次打卡的时间，并将标志位设定为俩次打卡的标志位
        //3，如果第三次打卡，不存储任何信息。
        //mode.addAttribute("stu",stuService.showAllStu());
        mode.addAttribute("title",fid);
        return  "check";
    }
}
