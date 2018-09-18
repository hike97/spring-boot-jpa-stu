package com.atguigu.springboot.tlife.healthRecord.service.impl;

import com.atguigu.springboot.tlife.healthRecord.repository.HealthRecordRepository;
import com.atguigu.springboot.tlife.healthRecord.repository.entity.HealthRecordEntity;
import com.atguigu.springboot.tlife.healthRecord.service.HealthRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hike97
 * @create 2018-09-14 10:30
 * @desc 健康档案服务层实现类
 **/
@Service
public class HealthRecordServiceImpl implements HealthRecordService{

    @Autowired
    private HealthRecordRepository healthRecordRepository;

    @Override
    public HealthRecordEntity getRecordByStuId(Integer stuId) {
        return healthRecordRepository.getRecordByStuId(stuId);
    }

    @Override
    public HealthRecordEntity findById(String id) {
        return healthRecordRepository.findOne( Integer.valueOf( id ) );
    }

    @Override
    public void save(HealthRecordEntity healthRecordEntity) {
        healthRecordRepository.save( healthRecordEntity );
    }

    @Override
    public void updateById(Integer id) {
        healthRecordRepository.updateById(id);
    }
}
