package com.atguigu.springboot.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Data
@Table(name = "t_score")
public class Score extends IdEntity{

    private Double score;

    private Integer year;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    @ManyToOne
    @JoinColumn(name = "stu_id")
    private Student student;

}