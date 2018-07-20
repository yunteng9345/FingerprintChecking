package com.example.demo0716101.model;

import lombok.Data;

import java.util.Date;


@Data
public class Student {
    private Integer u_id;
    private String u_name;
    private char u_sex;
    private Integer u_age;
    private String u_classes;
    private String u_academy;
    private String u_fingerId;
    private Date u_rTime;
    //private boolean biye;

}
