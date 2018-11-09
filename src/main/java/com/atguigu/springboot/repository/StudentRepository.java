package com.atguigu.springboot.repository;

import com.atguigu.springboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    @Query(value = "select * from t_student where user_Name =? and pass_word = ?",nativeQuery = true)
    Student loginValidate(String userName, String passWord);

    @Query(value = "select * from t_student t where t.status>=? ",nativeQuery = true)
    List<Student> findByStatusGreaterThanEquals(Integer i);
}
