package com.atguigu.springboot.tlife.healthRecord.service;

import com.atguigu.springboot.tlife.healthRecord.repository.entity.HealthRecordEntity;

import java.util.Optional;

/**
 * @author hike97
 * @create 2018-09-14 10:27
 * @desc 健康档案服务层
 **/
public interface HealthRecordService {

    HealthRecordEntity getRecordByStuId(Integer stuId);

    HealthRecordEntity findById(String id);

    void save(HealthRecordEntity healthRecordEntity);
}
