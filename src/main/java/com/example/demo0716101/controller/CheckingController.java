package com.example.demo0716101.controller;

import com.example.demo0716101.Dao.StuMapper;
import com.example.demo0716101.model.Check;
import com.example.demo0716101.model.Student;
import com.example.demo0716101.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/check")
public class CheckingController {

    @Autowired
    private CheckService checkService;
    @Autowired
    private StuMapper stuMapper;

    /*
     * 单片机发送指纹信息
     * 1。查询fid是否录入系统数据库，如果没有，就提醒录入数据。如果有，就设置今天第一次打卡
     * 2。
     * */
    @RequestMapping(value = "/scm", method = RequestMethod.GET)
    public String showAllStudent(ModelMap mode, String fid) {


        //判断此人有没有录入信息
        Student student = new Student();
        student.setUfingerid(fid);
        Student student1 = stuMapper.selectOne(student);
        //如果是没有录入指纹
        if (student1 == null) {
            mode.addAttribute("mesg", fid + "，没有录入系统，请点击此处录入指纹信息！");
            mode.addAttribute("title", fid);
            return "check";
        } else {

            //判断此人今天是否已经打卡
            Check check4 = new Check();
            check4.setFid(fid);
            Check check5 = checkService.selectOne(check4);

//            Date date7 = check5.getClock_in_1();
//            Date date8 = new Date();
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
//            String dateString = formatter.format(date7);
//            String dateString1 = formatter1.format(date8);

//            if (check5.getFlag() == '2' && dateString.equals(dateString1)) {
//
//                mode.addAttribute("mes", "您今天已经打卡俩次！请不要重复操作！");
//                mode.addAttribute("title", fid);
//                return "check";
//            }

            if (check5 == null) {
                //已经录入指纹
                // 1，查询是不是今天第一次打卡
                Check check = new Check();
                check.setFid(fid);
                //当天第一次打卡
                Check check110 = checkService.selectOne(check);
                if (check110 == null) {
                    Date date4 = new Date();
                    check.setClock_in_1(date4);
                    check.setFlag('1');//设定当天第一次打卡标志位
                    checkService.insertTime(check);//将数据插入数据库中
                    mode.addAttribute("mes", "今天第一次打卡完成！");
                    mode.addAttribute("title", fid);
                    return "check";

                }

            } else {
                //当天第二次打卡
                Check check2 = new Check();
                check2.setFid(fid);
                Check check22 = checkService.selectOne(check2);
                if (check22.getFlag() == '1') {
                    Date date3 = new Date();
                    check2.setClock_in_2(date3);
                    check2.setFlag('2');
                    checkService.updateOne(check2);
                    mode.addAttribute("mes", "今天第二次打卡完成！");
                    mode.addAttribute("title", fid);
                    return "check";
                }
                Date date = check5.getClock_in_1();
                Date date1 = new Date();
                SimpleDateFormat formatter22 = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat formatter33 = new SimpleDateFormat("yyyy-MM-dd");
                String dateString22 = formatter22.format(date);
                String dateString33 = formatter33.format(date1);
                if (check5.getFlag() == '2' && dateString22.equals(dateString33)) {
                    mode.addAttribute("mes", "您今天已经打卡俩次！请不要重复操作！");
                    mode.addAttribute("title", fid);
                    return "check";
                }
            }
            mode.addAttribute("title", fid);
            return "check";
        }
    }
}
