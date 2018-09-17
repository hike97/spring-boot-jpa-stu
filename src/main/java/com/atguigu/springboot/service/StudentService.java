package com.atguigu.springboot.service;


import com.atguigu.springboot.entity.Student;

import java.util.List;

public interface StudentService {

    Student getById(Integer id);

    Student loginValidate(String userName, String passWord);

    List<Student> getList();

    void save(Student student);

    void delete(Integer id);


}
