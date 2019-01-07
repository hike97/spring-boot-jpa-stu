package com.atguigu.springboot.common.util;
/*
 * Project: greenline-common-util
 * 
 * File Created at 2012-5-29
 * 
 * Copyright 2012 Greenline.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Greenline Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Greenline.com.
 */


/**
 * @Type DESUtil
 * @Desc DES 加解密工具类
 * @author weirui.shenwr
 * @date 2012-5-29
 * @Version V1.0
 */
public class DESUtil {

    /**
     * des加密
     * 
     * @param strMing 明文
     * @param key 密钥
     * @return
     */
    public static String DESEncode(String strMing, String key) {
        DesEncrypt des = new DesEncrypt();
        des.getKey(key);
        return des.getEncString(strMing);
    }

    /**
     * des解密
     * 
     * @param strSecret 密文
     * @param key 解密密钥
     * @return
     */
    public static String DESDecode(String strSecret, String key) {
        DesEncrypt des = new DesEncrypt();
        des.getKey(key);
        return des.getDesString(strSecret);
    }
}
