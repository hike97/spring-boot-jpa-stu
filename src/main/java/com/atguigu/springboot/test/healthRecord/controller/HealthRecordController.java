package com.atguigu.springboot.test.healthRecord.controller;

import com.atguigu.springboot.BaseController;
import com.atguigu.springboot.entity.Student;
import com.atguigu.springboot.test.healthRecord.constant.Location;
import com.atguigu.springboot.test.healthRecord.repository.entity.HealthRecordEntity;
import com.atguigu.springboot.test.healthRecord.service.HealthRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
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
        @Autowired
        private RedisTemplate  redisTemplate;

        //查看
        @GetMapping("/info/{stuId}")
        public String getlist(@PathVariable Integer stuId){

             Optional<HealthRecordEntity> op_healthRecord   =Optional.ofNullable(healthRecordService.getRecordByStuId(stuId));
             op_healthRecord.ifPresent(o->{
                 /**
                  *  判断创建时间或最后修改时间是否在本周
                  */
                     LocalDate sunDay = LocalDate.now().minusWeeks( 1l ).with( DayOfWeek.SUNDAY );//上周日
                     LocalDate monDay = LocalDate.now().plusWeeks( 1l ).with( DayOfWeek.MONDAY );//下周一
                     /*
                        date 转为localDate
                        atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
                      */
                     boolean afterSunday = false;
                     boolean beforeMonday = false;
                     /*
                        创建时间
                      */
                     Date createTime = o.getCreateTime();
                     LocalDate createTime_ = createTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                     afterSunday = createTime_.isAfter( sunDay );
                     beforeMonday = createTime_.isBefore( monDay );

                     /*
                     最后修改时间
                      */
                     if (o.getLastUpdateTime() !=null){
                         Date lastUpdateTime = o.getLastUpdateTime();
                         Instant instant = lastUpdateTime.toInstant();
                         ZoneId zoneId = ZoneId.systemDefault();
                         LocalDate lastupdateTime_ = instant.atZone(zoneId).toLocalDate();
                         afterSunday = lastupdateTime_.isAfter( sunDay );
                         beforeMonday = lastupdateTime_.isBefore( monDay );
                     }

                     if (!afterSunday&&beforeMonday){
                         //把当前实体类放入redis
                        // redisTemplate.delete( "user_"+stuId );
                         redisTemplate.opsForValue().set( "user_"+stuId,o);

                         o.setHiddenScore(true);
                     }

                 request.setAttribute( "healthRecordEntity",o);
             });

                //从redis中获取
                HealthRecordEntity entity_  = (HealthRecordEntity) redisTemplate.opsForValue().get( "user_" + stuId );
                if (entity_!=null){
                    request.setAttribute( "hi_entity",entity_ );
                }
             return "admin/health/info";
        }

        //添加or编辑页面
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
           //System.out.println( "id>>>>>:"+id );
            //用户id关联
            if ("".equals(id)){
                Student loginUser = (Student) request.getSession().getAttribute( "loginUser" );
                healthRecordEntity.setUserId( loginUser.getId() );
                healthRecordEntity.setCreateTime( new Date() );
                healthRecordEntity.setStatus(0);//第一次 添加
            }else {
                healthRecordEntity = healthRecordService.findById( id );
                //Redis--存储
                healthRecordEntity.setLastUpdateTime( new Date() );
                healthRecordEntity.setStatus(1);
                //第二次修改 添加新字段
                String maritalStatus = getParameter( "maritalStatus" );
                String allergicHistory = getParameter( "allergicHistory" );
                String familyMedicialHistory = getParameter( "familyMedicialHistory" );
                healthRecordEntity.setMaritalStatus( Integer.valueOf( maritalStatus ) );
                healthRecordEntity.setFamilyMedicialHistory( familyMedicialHistory );
                healthRecordEntity.setAllergicHistory( allergicHistory );
                redisTemplate.opsForValue().set( "user_"+healthRecordEntity.getUserId(),healthRecordEntity);
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
            healthRecordEntity.setHiddenScore(false);
            healthRecordService.save(healthRecordEntity);
            request.setAttribute( "healthRecordEntity",healthRecordEntity);
            return "redirect:info/"+healthRecordEntity.getUserId();
        }

    /**
     * 根据BMI算分
     * @param h
     * @return
     */
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
        final Integer[] score = {100};
            Integer bmi = getBMI( h );
            //--口味喜好
            // 0适中 1清淡 2辛辣(-2) 3重口味(-3) 4油腻(-3)
            switch (h.getTaste()){
                case 2:
                    score[0] -=2; break;
                case 3:
                    score[0] -=2; break;
                case 4:
                    score[0] -=2; break;
            }
            //食物组成（多选） 都是0
            //其他个人习惯（多选）
            Arrays.stream( h.getOtherConditions().split( "," ) ).forEach(str->{
                            switch (str){
                                case "10":
                                    score[0] -=1;break;
                                case "20":
                                    score[0] -=1;break;
                                case "30":
                                    score[0] -=1;break;
                                case "40":
                                    score[0] -=1;break;
                                case "50":
                                    score[0] -=1;break;
                                case "60":
                                    score[0] -=1;break;
                                case "70":
                                    score[0] -=1;break;
                                case "80":
                                    score[0] -=1;break;
                                case "90":
                                    score[0] -=1;break;
                                case "100":
                                    score[0] -=1;break;
                                case "110":
                                    score[0] -=1;break;
                                case "120":
                                    score[0] -=1;break;
                            }
                    });
            //运动习惯
            Integer runSteps = h.getRunSteps();
            if (runSteps>8000){

            }else if (runSteps>=2000&&runSteps<=8000){
                score[0]-=2;
            }else {
                score[0]-=3;
            }
            //地理位置
            float percent_location = Location.getEnum( "23" ).getPercent();
            //System.out.println("根据地理位置计算百分比："+ percent_location);
            //吸烟 喝酒 熬夜  睡眠时长 本周饮食规律
            //性别年龄百分比
            float percent_ageAndGender = 1f;
            Integer age = h.getAge();
            switch (h.getGender()){
                //女
                case 0:
                    if (age<=28){

                    }else if (age>=29&&age<=32){
                        percent_ageAndGender = 0.98f;
                    }else if (age>=33&&age<=38){
                        percent_ageAndGender = 0.95f;
                    }else if (age>=39&&age<=44){
                        percent_ageAndGender = 0.8f;
                    }else if (age>=45&&age<=72){
                        percent_ageAndGender = 0.87f;
                    }else {
                        percent_ageAndGender = 0.85f;
                    }
                break;
                //男
                case 1:
                    if (age<=34){

                    }else if (age>=35&&age<=37){
                        percent_ageAndGender = 0.98f;
                    }else if (age>=38&&age<=41){
                        percent_ageAndGender = 0.95f;
                    }else if (age>=42&&age<=47){
                        percent_ageAndGender = 0.9f;
                    }else if (age>=48&&age<=78){
                        percent_ageAndGender = 0.87f;
                    }else {
                        percent_ageAndGender = 0.85f;
                    }
                break;
            }
            System.out.println( "年龄百分比："+percent_ageAndGender );
            score[0] = score[0] + h.getThisWeekdiet() + h.getDrink() + h.getSmoke() + h.getStayUp() + h.getSleepCondition() + h.getSleepHours() + bmi;
            Float v = score[0] * percent_ageAndGender * percent_location;
            int value = v.intValue();
            return value;
        }
}
