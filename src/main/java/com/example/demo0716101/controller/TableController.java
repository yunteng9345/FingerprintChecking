package com.example.demo0716101.controller;

import com.example.demo0716101.model.Check;
import com.example.demo0716101.service.CheckService;
import com.example.demo0716101.service.StuService;
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
@RequestMapping("/table")
public class TableController {


    @Autowired
    private CheckService checkService;

    @Autowired
    private StuService stuService;

    @RequestMapping("/alltoday")
    public String kaoqinTable(ModelMap model) {
        Date date = new Date();
        SimpleDateFormat formatter22 = new SimpleDateFormat("yyyy-MM-dd");
        String nowdateString = formatter22.format(date);
        //        System.out.println(dateString);
        List<Check> checklsit = checkService.selectCheckByTime(nowdateString);
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
                checkService.upCD("1", check.getFid(), nowdateString);
//                   check.setLate("0");
            } else {
                checkService.upCD("0", check.getFid(), nowdateString);
            }

            if (compTime(DATE2, check.getClock_in_2().toString().substring(11, 18))) {
                checkService.upZT("1", check.getFid(), nowdateString);
//                    System.out.println("早退");
            } else {
                checkService.upZT("0", check.getFid(), nowdateString);
            }

        }

        List<Check> checklsit1 = checkService.selectCheckByTime(nowdateString);

        model.addAttribute("nowday", nowdateString);
        model.addAttribute("todaycheck", checklsit1);
//        model.addAttribute("wtime", DATE1);
//        model.addAttribute("rtime", DATE2);
        return "table";
    }

    /*
        设置上下班时间
     */
    //        public String search(HttpServletRequest request,ModelMap mode){


    //            timu.setGuanka(request.getParameter("search"));
    @RequestMapping(value = "/setTime", method = RequestMethod.GET)
    public String setTime(ModelMap model, HttpServletRequest request) {
//        String DATE1 = request.getParameter("worktime") + ":00";
//        String DATE2 = request.getParameter("resttime") + ":00";
        String inputdate = request.getParameter("inputdate");
        String DATE1 = "09:00:00";//第一次考勤，打卡时间大于这个时间是迟到
        String DATE2 = "18:00:00";//第二次考勤，打卡时间小于这个时间是早退
        // System.out.println(inputdate);
//        System.out.println(DATE1);
//        System.out.println(DATE2);

        Date date = new Date();
        SimpleDateFormat formatter22 = new SimpleDateFormat("yyyy-MM-dd");
        String nowdateString = formatter22.format(date);
        List<Check> checklsit1 = checkService.selectCheckByTime(inputdate);
        /*
         *     0   代表正常           1   代表迟到早退
         * */
//        for (Check check : checklsit1) {
//            if (compTime(check.getClock_in_1().toString().substring(11, 18), DATE1)) {
////                    System.out.println("迟到");
//                checkService.upCD("1", check.getFid(), inputdate);
////                   check.setLate("0");
//            } else {
//                checkService.upCD("0", check.getFid(), inputdate);
//            }
//
//            if (compTime(DATE2, check.getClock_in_2().toString().substring(11, 18))) {
//                checkService.upZT("1", check.getFid(), inputdate);
////                    System.out.println("早退");
//            } else {
//                checkService.upZT("0", check.getFid(), inputdate);
//            }
//
//        }
        model.addAttribute("nowday", inputdate);
        model.addAttribute("todaycheck", checklsit1);
//        model.addAttribute("wtime", DATE1);
//        model.addAttribute("rtime", DATE2);
        return "table";

        // return null;
    }

    //public  void checktime(List checklsit){
//    for (Check check : checklsit) {
//        if (compTime(check.getClock_in_1().toString().substring(11, 18), DATE1)) {
////                    System.out.println("迟到");
//            checkService.upCD("1", check.getFid());
////                   check.setLate("0");
//        }
//        else{
//            checkService.upCD("0", check.getFid());
//        }
//
//        if (compTime(DATE2, check.getClock_in_2().toString().substring(11, 18))) {
//            checkService.upZT("1", check.getFid());
////                    System.out.println("早退");
//        }
//        else {
//            checkService.upZT("0", check.getFid());
//        }
//
//    }
//}

    /*
    多条件查询信息首页
    */
    @RequestMapping(value = "/anyCondition", method = RequestMethod.GET)
    public String anyCondition(ModelMap mode,HttpServletRequest request) {

        //默认显示当天的考勤

        Date date = new Date();
        SimpleDateFormat formatter22 = new SimpleDateFormat("yyyy-MM-dd");
        String nowdateString = formatter22.format(date);
        List<Check> checklsit1 = checkService.selectCheckByTime(nowdateString);
        String DATE1 = "09:00:00";//第一次考勤，打卡时间大于这个时间是迟到
        String DATE2 = "18:00:00";//第二次考勤，打卡时间小于这个时间是早退
        // System.out.println(inputdate);
//        System.out.println(DATE1);
//        System.out.println(DATE2);

        /*
         *     0   代表正常           1   代表迟到早退
         * */
//        for (Check check : checklsit1) {
//            if (compTime(check.getClock_in_1().toString().substring(11, 18), DATE1)) {
//                   System.out.println("迟到");
//                checkService.upCD("1", check.getFid(), nowdateString);
//                  check.setLate("0");
//            } else {
//                checkService.upCD("0", check.getFid(), nowdateString);
//            }
//
//            if (compTime(DATE2, check.getClock_in_2().toString().substring(11, 18))) {
//                checkService.upZT("1", check.getFid(), nowdateString);
//                   System.out.println("早退");
//            } else {
//                checkService.upZT("0", check.getFid(), nowdateString);
//            }
//
//        }


        mode.addAttribute("nowday", nowdateString);
        mode.addAttribute("todaycheck", checklsit1);
        return "anyCondition";

    }
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(ModelMap mode,HttpServletRequest request) {

        System.out.println(request.getParameter("name"));
        System.out.println(request.getParameter("academy"));
        System.out.println(request.getParameter("classs"));
        System.out.println(request.getParameter("chidao"));
        System.out.println(request.getParameter("zaotui"));
        System.out.println(request.getParameter("inputtime"));

        Check check = new Check();

        check.setUname(request.getParameter("name"));
        check.setUacademy(request.getParameter("academy"));
        check.setUclass(request.getParameter("classs"));
        check.setLate(request.getParameter("chidao"));
        check.setBefor(request.getParameter("zaotui"));
        check.setNowday(request.getParameter("inputtime"));



        List<Check> list =stuService.search(check);
        for(Check c:list){
            System.out.println(c.getUname());
        }

        mode.addAttribute("todaycheck", list);
        return "anyCondition";
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


    /*

    <if test="uclass != null">
                and uclass=#{uclass}
            </if>

            <if test="late != null">
                and late=#{late}
            </if>

            <if test="befor != null">
                and befor=#{befor}
            </if>

            <if test="nowday != null">
                and nowday=#{nowday}
            </if>




     */

}
