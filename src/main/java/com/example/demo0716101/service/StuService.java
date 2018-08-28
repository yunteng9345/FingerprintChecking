package com.example.demo0716101.service;

import com.example.demo0716101.Dao.StuMapper;
import com.example.demo0716101.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuService {


    @Autowired
    private StuMapper stuMapper;

    public Integer addStu(Student student) {
        return stuMapper.addStu(student);
    }

    public Integer deleteStu(String id) {
        return stuMapper.deleteStu(id);
    }

    public Integer updateStu(Integer id) {
        return stuMapper.updateStu(id);
    }

   public List<Student> showAllStu(){
        return stuMapper.showAllStu();
    }

    public  Student selectOne(Student  student){return  stuMapper.selectOne(student);}

    public List<Check> search(Check check){
        return stuMapper.search(check);
    }
/*
漂流书
 */
public Integer addUser(User user){return  stuMapper.addUser(user) ;}
public User selectUser(User user){return  stuMapper.selectUser(user);}
public Integer addBook(Book book){return  stuMapper.addBook(book);}
public Book selectBook(Book book){return  stuMapper.selectBook(book);}
public List<Book> allBook(){return  stuMapper.allBook();}
public List<Book> allBookByOpenid(Book book){return  stuMapper.allBookByOpenid(book);}
public Integer addBookBar(BookBar bookBar){return  stuMapper.addBookBar(bookBar);}
 public  List<BookBar> allBookBarByOpenid(BookBar bookBar){return  stuMapper.allBookBarByOpenid(bookBar);}

    public  List<BookBar> allBookByBarName(BookBar bookBar){return  stuMapper.allBookByBarName(bookBar);}
    public  Integer insertappoint(Appoint appoint){return  stuMapper.insertappoint(appoint);}

    public List<Appoint> selectAppoint(Appoint appoint){return  stuMapper.selectAppoint(appoint);}
public  Integer insertComment(Comment comment){return  stuMapper.insertComment(comment);}

public  List<Comment> showAllComment(Comment comment){return stuMapper.showAllComment(comment);}
}



