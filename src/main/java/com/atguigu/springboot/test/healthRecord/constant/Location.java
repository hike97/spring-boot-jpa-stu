package com.atguigu.springboot.test.healthRecord.constant;

/**
 * 地理位置
 */
public enum Location {
    GUANG_XI("1","广西分公司",1f),
    SHEN_ZHEN("2","深圳分公司",1f),
    GUI_ZHOU("3","贵州分公司",1f),
    NEI_MENG_GU("4","内蒙古分公司",1f),
    SHAN_XI("5","山西分公司",1f),
    GAN_SU("6","甘肃分公司",1f),
    CHONG_QING("7","重庆分公司",1f),
    FU_JIAN("8","福建分公司",1f),
    XIN_JIANG("9","新疆分公司",1f),
    JIANG_SU("10","江苏分公司",1f),
    GUANG_DONG("11","广东分公司",1f),
    AN_HUI("12","安徽分公司",1f),
    ZHE_JIANG("13","浙江分公司",1f),
    XIA_MEN("14","厦门分公司",1f),
    HEI_LONG_JIANG("15","黑龙江分公司",1f),
    BEI_JING("16","北京分公司",1f),
    HE_BEI("17","河北分公司",1f),
    SHAN_DONG("18","山东分公司",1f),
    YUN_NAN("19","云南分公司",1f),
    SHANG_HAI("20","上海分公司",0.98f),
    JIANG_XI("21","江西分公司",0.98f),
    TIAN_JIN("22","天津分公司",0.98f),
    JI_LIN("23","吉林分公司",0.98f),
    SI_CHUAN("24","四川分公司",0.98f),
    LIAO_NING("25","辽宁分公司",0.98f),
    HE_NAN("26","河南分公司",0.98f),
    QING_DAO("27","青岛分公司",0.98f),
    HU_BEI("28","湖北分公司",0.98f),
    HU_NAN("29","湖南分公司",0.95f),
    DA_LIAN("30","大连分公司",0.95f),
    HAI_NAN("31","海南分公司",0.95f),
    SHAN_Xi("32","陕西分公司",0.95f),
    ;
    private String code;
    private String companyName;
    private float percent;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    Location(String code, String companyName, float percent) {
        this.code = code;
        this.companyName = companyName;
        this.percent = percent;
    }

    //通过code获取枚举类
    public static Location getEnum(String code){
        for (Location location : values()) {
            if (code.equals( location.getCode() )){
                return location;
            }
        }
        return null;
    }
}
