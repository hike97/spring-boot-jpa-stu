package com.atguigu.springboot.service;


import com.atguigu.springboot.entity.Score;

import java.util.List;

public interface ScoreService {

    List<Score> getList(Integer stuId);

    void save(Score student);

    void delete(Integer id);

    Score selectById(Integer id);

    void deleteByYear(Integer year, Integer stuId);

    List<Score> findAll();

    Score getById(Integer integer);
}
