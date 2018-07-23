package com.example.demo0716101.Dao;

import com.example.demo0716101.model.Check;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckMapper {
    Integer insertTime(Check check);

    Check selectOne(Check check);

    Integer updateOne(Check check);
    Integer updateOne1(Check check);

}
