package com.atguigu.springboot.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_student")//省略 表明类名小写
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    private String userName;

    private String passWord;

    private String realName;

    private Integer gender;

    private Integer age;

    private String role;

    private Integer status;

}