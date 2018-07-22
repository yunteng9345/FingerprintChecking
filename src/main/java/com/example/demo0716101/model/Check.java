package com.example.demo0716101.model;

import lombok.Data;

import java.util.Date;

@Data
public class Check {
    private String fid;
    private Date clock_in_1;
    private Date clock_in_2;
    private char flag;//0,当天没有打卡。1，当天第一次打卡。2，当天第二次打卡。

}
