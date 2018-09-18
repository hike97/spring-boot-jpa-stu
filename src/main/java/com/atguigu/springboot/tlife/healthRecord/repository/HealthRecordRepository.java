package com.atguigu.springboot.tlife.healthRecord.repository;

import com.atguigu.springboot.tlife.healthRecord.repository.entity.HealthRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hike97
 * @create 2018-09-14 10:23
 * @desc 健康档案jpa接口
 **/
public interface HealthRecordRepository extends JpaRepository<HealthRecordEntity,Integer> {

    @Query(value = "select * from t_healthrecord t WHERE t.user_id = ?",nativeQuery = true)
    HealthRecordEntity getRecordByStuId(Integer stuId);

    @Modifying
    @Transactional
    @Query(value = "update t_healthrecord t set t.this_score = NULL where t.id = ?",nativeQuery = true)
    void updateById(Integer id);
}
