package com.example.demo0716101.controller;

import com.example.demo0716101.model.Check;
import com.example.demo0716101.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/table")
public class TableController {


    @Autowired
    private CheckService checkService;

    @RequestMapping("/alltoday")
    public String kaoqinTable(ModelMap model) {

        Date date = new Date();
        SimpleDateFormat formatter22 = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter22.format(date);

        System.out.println(dateString);
        List<Check> checklsit = checkService.selectCheckByTime(dateString);
        model.addAttribute("todaycheck", checklsit);
        return "table";
    }

    public void test() {

        String DATE1 = "09:00:00";//第一次考勤，打卡时间大于这个时间是迟到
        String DATE2 = "18:00:00";//第二次考勤，打卡时间小于这个时间是早退

        DateFormat df = new SimpleDateFormat("hh:mm:ss");

        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("迟到");
            } else {
                System.out.println("正常");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
