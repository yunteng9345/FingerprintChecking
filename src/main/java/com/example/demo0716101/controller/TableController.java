package com.example.demo0716101.controller;

import com.example.demo0716101.model.Check;
import com.example.demo0716101.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
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

//        System.out.println(dateString);
        List<Check> checklsit = checkService.selectCheckByTime(dateString);
        // List<String> beforelist = new ArrayList<String>();
        // List<String> laterlist = new ArrayList<String>();

        String DATE1 = "09:00:00";//第一次考勤，打卡时间大于这个时间是迟到
        String DATE2 = "18:00:00";//第二次考勤，打卡时间小于这个时间是早退
//        DateFormat df = new SimpleDateFormat("hh:mm:ss");
        /*
        *     0   代表正常           1   代表迟到早退
         * */
        for (Check check : checklsit) {
            if (compTime(check.getClock_in_1().toString().substring(11, 18), DATE1)) {
//                    System.out.println("迟到");
                checkService.upCD("1", check.getFid());
//                   check.setLate("0");
            }
            else{
                checkService.upCD("0", check.getFid());
            }

            if (compTime(DATE2, check.getClock_in_2().toString().substring(11, 18))) {
                checkService.upZT("1", check.getFid());
//                    System.out.println("早退");
            }
            else {
                checkService.upZT("0", check.getFid());
            }

    }


        List<Check> checklsit1 = checkService.selectCheckByTime(dateString);
        model.addAttribute("nowday",dateString);
        model.addAttribute("todaycheck", checklsit1);
        return "table";
    }


    public static boolean compTime(String s1, String s2) {
        try {
            if (s1.indexOf(":") < 0 || s1.indexOf(":") < 0) {
                System.out.println("格式不正确");
            } else {
                String[] array1 = s1.split(":");
                int total1 = Integer.valueOf(array1[0]) * 3600 + Integer.valueOf(array1[1]) * 60 + Integer.valueOf(array1[2]);
                String[] array2 = s2.split(":");
                int total2 = Integer.valueOf(array2[0]) * 3600 + Integer.valueOf(array2[1]) * 60 + Integer.valueOf(array2[2]);
                return total1 - total2 > 0 ? true : false;
            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            return true;
        }
        return false;

    }


//    public static void test() {
//
//        String DATE1 = "09:00:00";//第一次考勤，打卡时间大于这个时间是迟到
//        String DATE2 = "18:00:00";//第二次考勤，打卡时间小于这个时间是早退
//
//        DateFormat df = new SimpleDateFormat("hh:mm:ss");
//
//        try {
//            Date dt1 = df.parse(DATE1);
//            Date dt2 = df.parse(DATE2);
//            if (dt1.getTime() < dt2.getTime()) {
//                System.out.println("迟到");
//            } else {
//                System.out.println("正常");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        test();
//    }

}
