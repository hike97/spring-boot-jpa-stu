package com.atguigu.springboot.common.util;

import java.io.Serializable;

public class OrderPackageRequest implements Serializable {

    /**
	 * 
	 */

    private String proofNo;

    public String getProofNo() {
        return proofNo;
    }

    public void setProofNo(String proofNo) {
        this.proofNo = proofNo;
    }

    /**
     * 姓名
     */
    private String name;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 套餐代码
     */
    private String packageCode;
    /**
     * 业务方套餐名
     */
    private String partnerPackageName;
    /**
     * 生效时间
     */
    private String effectTime;
    /**
     * 操作类型
     */
    private String actionType;

    public String getActionType() {
        return actionType;
    }

    public String getEffectTime() {
        return effectTime;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getMobile() {
        return mobile;
    }

    public String getName() {
        return name;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public String getPartnerPackageName() {
        return partnerPackageName;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public void setEffectTime(String effectTime) {
        this.effectTime = effectTime;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public void setPartnerPackageName(String partnerPackageName) {
        this.partnerPackageName = partnerPackageName;
    }

}
