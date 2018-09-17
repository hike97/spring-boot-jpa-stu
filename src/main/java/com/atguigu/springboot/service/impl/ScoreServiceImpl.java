package com.atguigu.springboot.service.impl;


import com.atguigu.springboot.entity.Score;
import com.atguigu.springboot.repository.ScoreRepository;
import com.atguigu.springboot.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("scoreService")
@Transactional
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public Score getById(Integer id){
       return scoreRepository.findOne( id );
    }


    @Override
    public List<Score> getList(Integer stuId) {

        return scoreRepository.findByStuId(stuId);
    }

    @Override
    public void save(Score score) {
        scoreRepository.save( score );

    }

    @Override
    public void delete(Integer id) {
        scoreRepository.delete( id );
    }

    @Override
    public Score selectById(Integer id) {
        return scoreRepository.findOne( id );
    }

    @Override
    public void deleteByYear(Integer year, Integer stuId) {
        scoreRepository.deleteByYearAndStuid(year,stuId);
    }

    @Override
    public List<Score> findAll() {
        return scoreRepository.findAll();
    }


}
