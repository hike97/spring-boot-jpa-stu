package com.atguigu.springboot.test.healthRecord.service;

import com.atguigu.springboot.test.healthRecord.repository.entity.HealthRecordEntity;

/**
 * @author hike97
 * @create 2018-09-14 10:27
 * @desc 健康档案服务层
 **/
public interface HealthRecordService {

    HealthRecordEntity getRecordByStuId(Integer stuId);

    HealthRecordEntity findById(String id);

    void save(HealthRecordEntity healthRecordEntity);

    void updateById(Integer id);
}
