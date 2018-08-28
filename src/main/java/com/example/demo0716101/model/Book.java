package com.example.demo0716101.model;

import lombok.Data;

@Data
public class Book extends User {
    private String isbn;
    private String booktitle;
    private String bookpic;
    private String bookauthor;
    private String openid;
    private String send_word;
    private String bookaddress;
    private String ownBookBar;

}
