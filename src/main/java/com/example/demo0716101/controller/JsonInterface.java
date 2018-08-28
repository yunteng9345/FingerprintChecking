package com.example.demo0716101.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo0716101.model.*;
import com.example.demo0716101.service.StuService;
import com.example.demo0716101.tools.MyX509TrustManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/json")
public class JsonInterface {

    @Autowired
    private StuService stuService;


    @RequestMapping(value = "/book", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String getBook(@RequestParam String isbn) {
        //System.out.println(isbn);
        String requestURL = "https://api.douban.com/v2/book/isbn/" + isbn;
        String string = MyX509TrustManager.httpsRequest(requestURL, "POST", null);
        //System.out.println(string);
        return string;
    }


    @RequestMapping(value = "/openid", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String getopenid(@RequestParam String code) {
        //System.out.println(code);
        String APPID = "wx01d314f898eb597f";
        String SECRET = "c06beb90d7eb4b83807934fa3119df6e";
        String requestURL = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID + "&secret=" + SECRET + "&js_code=" + code + "&grant_type=authorization_code";
        String string = MyX509TrustManager.httpsRequest(requestURL, "POST", null);
        //System.out.println(string);
        return string;
    }
    /*
        添加用户
     */

    @RequestMapping(value = "/addUser", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody
    void addUser(@RequestParam String openid, @RequestParam String uname, @RequestParam String usex, @RequestParam String uicon) {

        System.out.println(openid);
        System.out.println(uname);
        System.out.println(usex);
        System.out.println(uicon);
        User user = new User();
        user.setOpenid(openid);
        user.setUname(uname);
        if (usex.equals("1")) {
            user.setUsex("男");
        } else {
            user.setUsex("女");
        }
        user.setUicon(uicon);
        if (stuService.selectUser(user) == null) stuService.addUser(user);
        //return  null;
    }

    /*
        添加书籍
     */

    @RequestMapping(value = "/addBook", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody
    void addUser(@RequestParam String openid, @RequestParam String title, @RequestParam String author, @RequestParam String image,
                 @RequestParam String isbn, @RequestParam String send_word, @RequestParam String ownBookBar) {

        System.out.println(openid);
        System.out.println(title);
        System.out.println(author);
        System.out.println(image);
        System.out.println(isbn);
        System.out.println(send_word);

        Book book = new Book();

        book.setOpenid(openid);
        book.setIsbn(isbn);
        book.setBookaddress("bookaddress");
        book.setBookauthor(author);
        book.setBookpic(image);
        book.setBooktitle(title);
        book.setSend_word(send_word);
        book.setOwnBookBar(ownBookBar);


        if (stuService.selectBook(book) == null) stuService.addBook(book);

    }

    /**
     * 显示所有书籍
     */
    @RequestMapping(value = "/allBook", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String allBook() {
        List<Book> list = stuService.allBook();
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        String s = JSON.toJSONString(list);
        //创建一个json对象数组
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("booklist", jsonArray);
        JSONObject json = new JSONObject(map);
        return json.toString();
    }

    /**
     * 显示所有书籍
     */
    @RequestMapping(value = "/allBookByOpenid", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String allBookByOpenid(@RequestParam String openid) {
        Book book = new Book();
        book.setOpenid(openid);
        List<Book> list = stuService.allBookByOpenid(book);
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        String s = JSON.toJSONString(list);
        //创建一个json对象数组
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("booklist", jsonArray);
        JSONObject json = new JSONObject(map);
        return json.toString();
    }

    /*
    添加书吧
     */
    @RequestMapping(value = "/addBookBar", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody
    void addBookBar(@RequestParam String openid, @RequestParam String name, @RequestParam String addr, @RequestParam String miaoshu) {

        System.out.println(openid);
        System.out.println(name);
        System.out.println(addr);
        System.out.println(miaoshu);
        BookBar bookBar = new BookBar();
        bookBar.setOpenid(openid);
        bookBar.setName(name);
        bookBar.setAddr(addr);
        bookBar.setMiaoshu(miaoshu);
        stuService.addBookBar(bookBar);


    }

    /*
    显示所有bookbar
     */
    @RequestMapping(value = "/allBookBarByOpenid", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String allBookBarByOpenid(@RequestParam String openid) {
        BookBar book = new BookBar();
        book.setOpenid(openid);
        List<BookBar> list = stuService.allBookBarByOpenid(book);
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        String s = JSON.toJSONString(list);
        //创建一个json对象数组
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("bookbarlist", jsonArray);
        JSONObject json = new JSONObject(map);
        return json.toString();
    }

    /*
    显示所有bookbar中的book
     */
    @RequestMapping(value = "/allBookByBarName", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String allBookByBarName(@RequestParam String name) {
        BookBar book = new BookBar();
        book.setName(name);
        List<BookBar> list = stuService.allBookByBarName(book);
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        String s = JSON.toJSONString(list);
        //创建一个json对象数组
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("barbooklist", jsonArray);
        JSONObject json = new JSONObject(map);
        return json.toString();
    }

    /*
    appoint
     */

    @RequestMapping(value = "/appoint", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody
    void appoint(@RequestParam String openid1, @RequestParam String openid2, @RequestParam String content, @RequestParam String isbn, @RequestParam String flag) {

        Appoint appoint = new Appoint();
        appoint.setOpenid1(openid1);
        appoint.setOpenid2(openid2);
        appoint.setContent(content);
        appoint.setFlag(flag);
        appoint.setIsbn(isbn);
        stuService.insertappoint(appoint);

    }

    /*
    显示所有bookbar中的book
     */
    @RequestMapping(value = "/selectAppoint", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String selectAppoint(@RequestParam String openid2) {
        // System.out.println(openid2);
        Appoint appoint = new Appoint();
        appoint.setOpenid2(openid2);
        List<Appoint> list = stuService.selectAppoint(appoint);
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        String s = JSON.toJSONString(list);
        //创建一个json对象数组
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appointlist", jsonArray);
        JSONObject json = new JSONObject(map);
        return json.toString();
    }

      /*
    comment
     */

    @RequestMapping(value = "/comment", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody
    void addcomment(@RequestParam String openid, @RequestParam String isbn, @RequestParam String comment) {
        Comment comment1 = new Comment();
        comment1.setOpenid(openid);
        comment1.setIsbn(isbn);
        comment1.setComment(comment);
        stuService.insertComment(comment1);
        // stuService.insertappoint();
    }

    /*
   showAllComment
    */
    @RequestMapping(value = "/showAllComment", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody
    String showAllComment(@RequestParam String isbn) {
        // System.out.println(openid2);
        Comment comment =new Comment();
        comment.setIsbn(isbn);

        List<Comment> list = stuService.showAllComment(comment);
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        String s = JSON.toJSONString(list);
        //创建一个json对象数组
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("allComment", jsonArray);
        JSONObject json = new JSONObject(map);
        return json.toString();
    }


}
