package com.example.demo0716101.Dao;

import com.example.demo0716101.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StuMapper {

    List<Student> showAllStu();
    Integer deleteStu(String id);
    Integer updateStu(Integer id);
    Integer addStu(Student student);
    Student selectOne(Student student);
    List<Check> search(Check check);


    /*
    漂流书
     */
    Integer addUser(User user);
    User selectUser(User user);
    /**
     * 添加书
     */
    Integer addBook(Book book);
    Book selectBook(Book book);
    List<Book> allBook();
    List<Book> allBookByOpenid(Book book);
    /*
    添加书吧
     */
    Integer addBookBar(BookBar bookBar);
    List<BookBar> allBookBarByOpenid(BookBar bookBar);
    List<BookBar> allBookByBarName(BookBar bookBar);
    /*
    appoint
     */
    Integer insertappoint(Appoint appoint);

    List<Appoint> selectAppoint(Appoint appoint);

    Integer insertComment(Comment comment);

   List<Comment> showAllComment(Comment comment);
}
