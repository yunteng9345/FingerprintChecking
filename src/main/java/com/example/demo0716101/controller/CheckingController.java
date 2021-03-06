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

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    public String showAllStudent(ModelMap mode, String fid, HttpServletRequest request) {

//        request.getHeader("User-Agent");    //就是取得客户端的系统版本
//        request.getRemoteAddr();    //取得客户端的IP
//        request.getRemoteHost();    //取得客户端的主机名
//        request.getRemotePort();    //取得客户端的端口
//        request.getRemoteUser();    //取得客户端的用户
//        request.getLocalAddr();    //取得服务器IP
//        request.getLocalPort();    //取得服务器端口
//        System.out.println(request.getRemotePort());
//        System.out.println(request.getLocalAddr());

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
            Date nowdate = new Date();
            SimpleDateFormat nowdateformatter = new SimpleDateFormat("yyyy-MM-dd");
            String nowdate1 = nowdateformatter.format(nowdate);
            //设置今天的时间和id，判断此人今天是否是第一次打卡
            check4.setNowday(nowdate1);
            check4.setFid(fid);
            Check check5 = checkService.selectOne(check4);
            if (check5 == null) {//今天没有打卡
//
//              // 1，查询是不是今天第一次打卡
                Check check = new Check();
                check.setFid(fid);
//                //当天第一次打卡
//                Check check110 = checkService.selectOne(check);
//                if (check110 == null) {
                Date date4 = new Date();
                check.setClock_in_1(date4);
                check.setNowday(nowdate1);
                check.setFlag('1');//设定当天第一次打卡标志位
                checkService.insertTime(check);//将数据插入数据库中
                mode.addAttribute("mes", "今天第一次打卡完成！");
                System.out.println(fid + ",今天第一次打卡完成！");
                mode.addAttribute("title", fid);
                return "check";
//
            }
//
            else if (check5.getFlag() == '1') {
                //当天第二次打卡
                Check check2 = new Check();
                check2.setFid(fid);

                Date date3 = new Date();
                check2.setClock_in_2(date3);
                check2.setNowday(nowdate1);
                check2.setFlag('2');
                checkService.updateOne(check2);

                /*
                判断迟到与否
                 */
                String DATE1 = "09:00:00";//第一次考勤，打卡时间大于这个时间是迟到
                String DATE2 = "18:00:00";//第二次考勤，打卡时间小于这个时间是早退
                SimpleDateFormat formatter22 = new SimpleDateFormat("yyyy-MM-dd");
                String nowdateString = formatter22.format(date3);
                List<Check> checklsit1 = checkService.selectCheckByTime(nowdateString);
                /*
                 *     0   代表正常           1   代表迟到早退
                 * */
                for (Check check : checklsit1) {
                    if (TableController.compTime(check.getClock_in_1().toString().substring(11, 18), DATE1)) {
//                    System.out.println("迟到");
                        checkService.upCD("1", check.getFid(), nowdateString);
//                   check.setLate("0");
                    } else {
                        checkService.upCD("0", check.getFid(), nowdateString);
                    }

                    if (TableController.compTime(DATE2, check.getClock_in_2().toString().substring(11, 18))) {
                        checkService.upZT("1", check.getFid(), nowdateString);
//                    System.out.println("早退");
                    } else {
                        checkService.upZT("0", check.getFid(), nowdateString);
                    }

                }


                mode.addAttribute("mes", "今天第二次打卡完成！");
                System.out.println(fid + ",今天第二次打卡完成！");
                mode.addAttribute("title", fid);
                return "check";
            } else if (check5.getFlag() == '2') {
//                Date date = check5.getClock_in_1();
//                Date date1 = new Date();
//                SimpleDateFormat formatter22 = new SimpleDateFormat("yyyy-MM-dd");
//                SimpleDateFormat formatter33 = new SimpleDateFormat("yyyy-MM-dd");
//                String dateString22 = formatter22.format(date);
//                String dateString33 = formatter33.format(date1);
//                if (check5.getFlag() == '2' && dateString22.equals(dateString33)) {
                mode.addAttribute("mes", "您今天已经打卡俩次！请不要重复操作！");
                System.out.println(fid + ",您今天已经打卡俩次！请不要重复操作！");
                mode.addAttribute("title", fid);
                return "check";
//                }
            }
            mode.addAttribute("title", fid);
            return "check";


        }
    }
}
