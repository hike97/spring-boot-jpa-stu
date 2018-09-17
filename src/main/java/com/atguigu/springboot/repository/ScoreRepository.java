package com.atguigu.springboot.repository;

import com.atguigu.springboot.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score,Integer> {

    @Query(value = "select * from t_score t WHERE t.stu_id = ?",nativeQuery = true)
    List<Score> findByStuId(Integer stuId);

    @Modifying
    @Transactional
    @Query(value = "delete from t_score\n" +
            "    where year =? and stu_id = ?",nativeQuery = true)
    void deleteByYearAndStuid(Integer year, Integer stuId);
}
