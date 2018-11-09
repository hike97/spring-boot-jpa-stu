package com.atguigu.springboot.service.impl;

import com.atguigu.springboot.entity.Student;
import com.atguigu.springboot.repository.StudentRepository;
import com.atguigu.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;


    public Student getById(Integer id){

        return studentRepository.getOne( id );
    }

    @Override
    public Student loginValidate(String userName, String passWord) {
        return studentRepository.loginValidate(userName,passWord);
    }

    @Override
    public List<Student> getList() {

        return studentRepository.findByStatusGreaterThanEquals(0);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void delete(Integer id) {
        studentRepository.deleteById( id );
    }




}
