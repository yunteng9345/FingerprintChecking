package com.example.demo0716101.service;

import com.example.demo0716101.Dao.CheckMapper;
import com.example.demo0716101.model.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckService {

    @Autowired
    private CheckMapper checkMapper;

    public Integer insertTime(Check check) {
        return checkMapper.insertTime(check);
    }

    public Check selectOne(Check check) {
        return checkMapper.selectOne(check);
    }

    public Integer updateOne(Check check){
        return  checkMapper.updateOne(check);
    }
}
