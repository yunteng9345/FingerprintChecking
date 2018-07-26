package com.example.demo0716101.Dao;

import com.example.demo0716101.model.Check;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckMapper {
    Integer insertTime(Check check);

    Check selectOne(Check check);

    Integer updateOne(Check check);
    Integer updateOne1(Check check);

    List<Check> selectCheckByTime (@Param("time1")String time1);

    Integer upCD(@Param("cd") String cd,@Param("fid") String fid);
    Integer upZT(@Param("zt")String zt,@Param("fid") String fid);

}
