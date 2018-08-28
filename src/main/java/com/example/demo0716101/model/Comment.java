package com.example.demo0716101.model;

import lombok.Data;

@Data
public class Comment extends User {
    private String openid;
    private String isbn;
    private String comment;

}
