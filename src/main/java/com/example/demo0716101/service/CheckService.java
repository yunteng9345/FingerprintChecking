package com.example.demo0716101.service;

import com.example.demo0716101.Dao.CheckMapper;
import com.example.demo0716101.model.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Integer updateOne1(Check check){
        return  checkMapper.updateOne1(check);
    }

    public List<Check> selectCheckByTime(String time1){return  checkMapper.selectCheckByTime(time1);}

    public Integer upCD(String cd,String fid,String nowday){return checkMapper.upCD(cd,fid,nowday);}
    public Integer upZT(String zt,String fid,String nowday){return  checkMapper.upZT(zt,fid,nowday);}
}
