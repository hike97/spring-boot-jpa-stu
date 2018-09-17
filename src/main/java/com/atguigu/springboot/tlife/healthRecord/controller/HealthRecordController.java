package com.atguigu.springboot.tlife.healthRecord.controller;

import com.atguigu.springboot.BaseController;
import com.atguigu.springboot.entity.Score;
import com.atguigu.springboot.entity.Student;
import com.atguigu.springboot.tlife.healthRecord.repository.entity.HealthRecordEntity;
import com.atguigu.springboot.tlife.healthRecord.service.HealthRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Id;
import java.awt.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author hike97
 * @create 2018-09-14 10:16
 * @desc 健康档案接口
 **/
@Controller
@RequestMapping(value = "/healthRecord",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)//produces 返回数据类型为UTF-8
public class HealthRecordController extends BaseController{
        @Autowired
        private HealthRecordService healthRecordService;

        //查看
        @GetMapping("/info/{stuId}")
        public String getlist(@PathVariable Integer stuId){
             Optional<HealthRecordEntity> op_healthRecord   =Optional.ofNullable(healthRecordService.getRecordByStuId(stuId));
             op_healthRecord.ifPresent(o->request.setAttribute( "healthRecord",o));
             return "admin/health/info";
        }

        //添加or编辑页面
        //添加页面
        @GetMapping("/toAdd")
        public String toAddPage(){
                String id = getParameter( "id" );
                if (id != null){
                      HealthRecordEntity healthRecordEntity = healthRecordService.findById(id);
                      request.setAttribute( "healthRecord",healthRecordEntity );

                }
                return "admin/health/add";
        }

        @PostMapping("/saveOrupdate")
        public String edit(HealthRecordEntity healthRecordEntity){
           String id = getParameter( "id" );
           System.out.println( "id>>>>>:"+id );
            //用户id关联
            if ("".equals(id)){
                Student loginUser = (Student) request.getSession().getAttribute( "loginUser" );
                healthRecordEntity.setUserId( loginUser.getId() );
                healthRecordEntity.setCreateTime( new Date() );
            }else {
                healthRecordEntity = healthRecordService.findById( id );
                healthRecordEntity.setLastUpdateTime( new Date() );
            }
            //多选字符串
            String[] dietCompositions = getParameterValues( "dietComposition" );
            String[] otherConditions = getParameterValues( "otherCondition" );
            String dietCompositions_str = Arrays.stream( dietCompositions ).collect( Collectors.joining( "," ));
            String otherCoditions_str = Arrays.stream( otherConditions ).collect( Collectors.joining( "," ) );
            healthRecordEntity.setDietCompositions( dietCompositions_str );
            healthRecordEntity.setOtherConditions( otherCoditions_str );
            //设置步数
            healthRecordEntity.setRunSteps( 8000 );
            //地理位置接口
            //healthRecordEntity.setLocation(  );
            /**
             * 计算分数
             */
            Integer thisScore = countScore(healthRecordEntity);
            healthRecordEntity.setThisScore( thisScore );
            healthRecordService.save(healthRecordEntity);
            request.setAttribute( "healthRecordEntity",healthRecordEntity);
            return "admin/health/result";
        }

        private Integer getBMI(HealthRecordEntity h){
            Integer result = 0;
            float bmi = (float) (h.getWeight()/Math.pow(h.getHeight(),2));
            if (bmi>=12&&bmi<=14){
                result = -6;
            }else if (bmi>=15&&bmi<=18){
                result = -4;
            }else if (bmi>=19&&bmi<=22){
                result =-3;
            }else if (bmi>=23&&bmi<=24){

            }else if (bmi>=25&&bmi<=29){
                result =-6;
            }else if (bmi>=30&&bmi<=39){
                result =-8;
            }else {
                result = -10;
            }
            return result;
        }
    /**
     *
     * @param h
     * @return
     */
    private Integer countScore(HealthRecordEntity h) {
            Integer score = 100;
            Integer bmi = getBMI( h );
            //0适中 1清淡 2辛辣(-2) 3重口味(-3) 4油腻(-3)
            switch (h.getTaste()){
                case 2:score-=2; break;
                case 3:score-=2; break;
                case 4:score-=2; break;
            }
            score =score+h.getThisWeekdiet()+h.getSmoke()+
            h.getDrink()+h.getStayUp()+h.getSleepCondition()+h.getSleepHours()+bmi;
            return score;
        }
}
