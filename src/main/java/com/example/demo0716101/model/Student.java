package com.example.demo0716101.model;

import lombok.Data;

import java.util.Date;


@Data
public class Student {
    private String uid;
    private String uname;
    private String usex;
    private Integer uage;
    private String uclass;
    private String uacademy;
    private String ufingerid;
    private Date urtime;

}
