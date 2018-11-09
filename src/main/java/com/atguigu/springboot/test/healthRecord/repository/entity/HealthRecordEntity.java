package com.atguigu.springboot.test.healthRecord.repository.entity;


import com.atguigu.springboot.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author hike97
 * @create 2018-09-13 19:48
 * @desc 健康档案实体类
 **/
@Entity
@Table(name = "t_healthrecord")//省略 表明类名小写
public class HealthRecordEntity extends IdEntity{

    public HealthRecordEntity() {
    }


    private Integer userId;//用户Id关联
    private Integer gender;//0女 1男
    private Integer age;//未实名默认1970年1月1日
    private float height;//身高 男：170cm 女：160cm
    private float weight;//体重 男：65kg 女：50kg
    private Integer maritalStatus;//婚姻状况 0未婚 1离异 2已婚 3丧偶
    private Integer fertility;//生育状况 0都不是 1备孕期 2怀孕期 3已怀孕
    private String allergicHistory;//过敏史
    private String familyMedicialHistory;//家族病史



    /*
          饮食习惯
         */
    private Integer thisWeekdiet;//0规律三餐 1三餐不固定 2不吃早餐 3常吃外卖 4常吃宵夜
    //口味喜好
    private Integer taste;//0适中 1清淡 2辛辣 3重口味 4油腻
    //食物组成
    private String dietCompositions;

    public enum DietComposition{
        BALANCE_MEAT_VEGETABLE("荤素搭配","10",0),
        PRFER_MEAT("偏爱肉食","20",0),
        VEGANISM("素食主义","30",0),
        FITNESS_DIET("减肥餐","40",0),
        WHOLE_GRAINS("五谷杂粮","50",0),
        PREFER_FRUIT("水果爱好者","60",0),
        PREFER_SWEET("喜好甜食","70",0);
        /**
         * 枚举类中的成员变量 描述 代表码 积分规则
         */
        private  String desc;
        private  String code;
        private  Integer score;

        public String getDesc() {
            return desc;
        }

        public String getCode() {
            return code;
        }

        public Integer getScore() {
            return score;
        }

        DietComposition(String desc, String code, Integer score) {
            this.desc = desc;
            this.code = code;
            this.score = score;
        }
        //通过code获取枚举类
        public static DietComposition getEnum(String code){
            for (DietComposition composition : values()) {
                if (code.equals( composition.getCode() )){
                    return composition;
                }
            }
            return null;
        }

    }

    /*
        生活习惯
     */
    /////////////////////////////////0从不 1偶尔 2 经常///////////////////////////
    private Integer smoke;
    private Integer drink;
    private Integer stayUp;

    private Integer sleepCondition; //0易入睡 1.困难 2.早醒
    private Integer sleepHours;//0 <6小时 1 6~8小时 2.>8小时
    /*
        其他个人习惯
     */
    private String otherConditions;
    private boolean hiddenScore;

    public boolean isHiddenScore() {
        return hiddenScore;
    }

    public void setHiddenScore(boolean hiddenScore) {
        this.hiddenScore = hiddenScore;
    }
    public enum OtherCondition{
        PHUBBER("低头族","10",-1),
        SEDENTARY("久坐","20",-1),
        STANDING("久站","30",-1),
        CROSS_ONES_LEGS("跷二郎腿","40",-1),
        STANDUP_URINATE("强忍大小便","50",-1),
        DISLIKE_WATER("不喜喝水","60",-1),
        BED_AFTER_MEAL("饭后卧床","70",-1),
        NEWSPAPER_IN_TOILET("如厕看报纸","80",-1),
        PHONE_BEFORE_SLEEP("睡前刷手机","90",-1),
        LIKE_COOL_DRINK("喜冷饮","100",-1),
        OVEREATING("暴饮暴食","110",-1),
        PLAY_PHONEORPC_FORLONG("长时间玩手机/电脑","120",-1);
        /**
         * 枚举类中的成员变量 描述 代表码 积分规则
         */
        private  String desc;
        private  String code;
        private  Integer score;

        public String getDesc() {
            return desc;
        }

        public String getCode() {
            return code;
        }

        public Integer getScore() {
            return score;
        }

        OtherCondition(String desc, String code, Integer score) {
            this.desc = desc;
            this.code = code;
            this.score = score;
        }

        //通过code获取枚举类
        public static OtherCondition getEnum(String code){
            for (OtherCondition otherCondition : values()) {
                if (code.equals( otherCondition.getCode() )){
                    return otherCondition;
                }
            }
            return null;
        }
    }
    private Integer runSteps;//运动步数（调接口）
    private String location;//地理位置
    private Date createTime;//本次创建时间
    private Date lastUpdateTime;//上期评估时间
    private Integer thisScore;//本次分数
    private Integer status;//0 待评估 1 此前未有评估 2 评估过本周未有评估 3 此前有过评估并再次评估


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getFertility() {
        return fertility;
    }

    public void setFertility(Integer fertility) {
        this.fertility = fertility;
    }

    public String getAllergicHistory() {
        return allergicHistory;
    }

    public void setAllergicHistory(String allergicHistory) {
        this.allergicHistory = allergicHistory;
    }

    public String getFamilyMedicialHistory() {
        return familyMedicialHistory;
    }

    public void setFamilyMedicialHistory(String familyMedicialHistory) {
        this.familyMedicialHistory = familyMedicialHistory;
    }

    public Integer getTaste() {
        return taste;
    }

    public void setTaste(Integer taste) {
        this.taste = taste;
    }


    public Integer getSmoke() {
        return smoke;
    }

    public void setSmoke(Integer smoke) {
        this.smoke = smoke;
    }

    public Integer getDrink() {
        return drink;
    }

    public void setDrink(Integer drink) {
        this.drink = drink;
    }

    public Integer getStayUp() {
        return stayUp;
    }

    public void setStayUp(Integer stayUp) {
        this.stayUp = stayUp;
    }

    public Integer getSleepCondition() {
        return sleepCondition;
    }

    public void setSleepCondition(Integer sleepCondition) {
        this.sleepCondition = sleepCondition;
    }

    public Integer getSleepHours() {
        return sleepHours;
    }

    public void setSleepHours(Integer sleepHours) {
        this.sleepHours = sleepHours;
    }


    public Integer getRunSteps() {
        return runSteps;
    }

    public void setRunSteps(Integer runSteps) {
        this.runSteps = runSteps;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getThisScore() {
        return thisScore;
    }

    public void setThisScore(Integer thisScore) {
        this.thisScore = thisScore;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDietCompositions() {
        return dietCompositions;
    }

    public void setDietCompositions(String dietCompositions) {
        this.dietCompositions = dietCompositions;
    }

    public String getOtherConditions() {
        return otherConditions;
    }

    public void setOtherConditions(String otherConditions) {
        this.otherConditions = otherConditions;
    }

    public Integer getThisWeekdiet() {
        return thisWeekdiet;
    }

    public void setThisWeekdiet(Integer thisWeekdiet) {
        this.thisWeekdiet = thisWeekdiet;
    }

    @Override
    public String toString() {
        return "HealthRecordEntity{" +
                "userId=" + userId +
                ", gender=" + gender +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", maritalStatus=" + maritalStatus +
                ", fertility=" + fertility +
                ", allergicHistory='" + allergicHistory + '\'' +
                ", familyMedicialHistory='" + familyMedicialHistory + '\'' +
                ", thisWeekdiet=" + thisWeekdiet +
                ", taste=" + taste +
                ", dietCompositions='" + dietCompositions + '\'' +
                ", smoke=" + smoke +
                ", drink=" + drink +
                ", stayUp=" + stayUp +
                ", sleepCondition=" + sleepCondition +
                ", sleepHours=" + sleepHours +
                ", otherConditions='" + otherConditions + '\'' +
                ", runSteps=" + runSteps +
                ", location='" + location + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", thisScore=" + thisScore +
                ", status=" + status +
                '}';
    }
}
