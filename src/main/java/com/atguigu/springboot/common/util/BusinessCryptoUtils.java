package com.atguigu.springboot.common.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * @author wangts
 * @Type guahao-portal
 * @Desc
 * @date 2016/3/23.
 * @Version V1.0
 */
public class BusinessCryptoUtils {

    private static String SECRET;

    public BusinessCryptoUtils(String key) {
        this.SECRET = key;
    }

    // public static void main(String[] arg) {
    //
    // BusinessCryptoUtils utils = new
    // BusinessCryptoUtils("mX6bmjYN1bZviBMkIClkgr4NH");
    // String str =
    // "5gezK7wYEjGmzReui4GLAvhd5NmXlDSKnxjKS33rKmFGX8najWgzxvo53tARqFsORl/J2o1oM8ZsNGmUDx302vPYtPlZGtp1fxSAz+Audcrn+jjj8daWujs7kN1Js+2bdgbgcDDyNMm1SCdUWgRBZWa6phgJSMrlQG/xL9ds5aeRu0b6neqWBlE1C9qx29V9IGvwaqNCNodQDjr1omYkDNJglntkAPjCwErN77bJnEqP5RA4vcegQ2oSND/V44pZKjO0y4eg5M8v3S6FEgyb7iw06gmdXDToIF17yCI/LHJ1ZvSHXjy0/ZyE8iHR3GrvtUgnVFoEQWVbnpEca+BLfOldowuttxiC2V5uyRu68Hdkifgz1VNn8qT6PMYCNe3gfLGSdf+oTfXMROfrr9aoq38h9pTfk3JIMa6TuRtDT56dKEE8ymNkkR+Go2Di2OfB9yN2yFucJruHe9uxmTcEXO8SnAYIeguvnlGs5xBC24I=";
    //
    // String decrypt2 = utils.decrypt(str);
    //
    // System.out.println("---" + decrypt2);
    // /*
    // * String res1 = utils .encrypt(
    // *
    // "{\"appointTime\":\"201707281956\",\"cancelReason\":\"\",\"department\":\"耳鼻喉科\",\"doctor\":\"方秀\",\"extUserId\":\"TC201707021715\",\"hospital\":\"天津市宁河县医院\",\"idCard\":\"\",\"menzhenType\":\"普通门诊\",\"mobile\":\"\",\"name\":\"方秀\",\"orderDate\":\"20170714195044\",\"patientName\":\"方秀\",\"serialNo\":\"5969\",\"status\":1,\"timeSection\":2,\"type\":\"5\"}"
    // * );
    // */
    // @SuppressWarnings("deprecation")
    // /*
    // * String encode = URLEncoder .encode(utils .encrypt(
    // *
    // "{\"appointTime\":\"201707281956\",\"cancelReason\":\"\",\"department\":\"耳鼻喉科\",\"doctor\":\"方秀\",\"extUserId\":\"TC201707021715\",\"hospital\":\"天津市宁河县医院\",\"idCard\":\"\",\"menzhenType\":\"普通门诊\",\"mobile\":\"\",\"name\":\"方秀\",\"orderDate\":\"20170714195044\",\"patientName\":\"方秀\",\"serialNo\":\"5969\",\"status\":1,\"timeSection\":2,\"type\":\"5\"}"
    // * ));
    // */
    // // PushOrderMessage push = new PushOrderMessage();
    // // push.setAppointTime("2017-09-14 10:58");
    // // push.setDepartment("耳鼻喉科");
    // // push.setDoctor("方秀");
    // // push.setExtUserId("TC201707021715");
    // // push.setHospital("天津市宁河县医院");
    // // push.setMenzhenType("普通门诊");
    // // push.setName("方秀");
    // // push.setOrderDate("2017-07-14 19:50:44");
    // // push.setPatientName("方秀");
    // // push.setSerialNo("5969");
    // // push.setStatus(1);
    // // push.setTimeSection(2);
    // // push.setType("5");
    // // JSONObject pushJSON = JSONObject.fromObject(push);
    // JSONObject json = new JSONObject();
    // json.put("appointTime", "2017-08-24 12:11");
    // json.put("cancelDate", "");
    // json.put("cancelReason", "");
    // json.put("department", "头颈联合会诊门诊");
    // json.put("doctor", "肖文昭");
    // json.put("extUserId", "471");
    // json.put("hospital", "天津市肿瘤医院");
    // json.put("idCard", "");
    // json.put("menzhenType", "普通门诊");
    // json.put("mobile", "");
    // json.put("name", "猪猪猪");
    // json.put("orderDate", "2017-07-17 12:05:51");
    // json.put("patientName", "王文华");
    // json.put("serialNo", "5973");
    // json.put("status", 1);
    // json.put("timeSection", 1);
    // json.put("type", "5");
    //
    // System.out.println(json.toString());
    // @SuppressWarnings("deprecation")
    // String encode = utils.encrypt(json.toString());
    // System.out.println(encode);
    // encode = URLEncoder.encode(encode);
    // System.out.println("加密的信息： " + encode);
    //
    // String md5Format11 = MD5Util.getMD5Format(json.toString() +
    // "170717105936"
    // + MD5Util.getMD5Format("DYKzAJfX8I4c0Rg0uw3bTKq3EKq%A01P"));
    //
    // System.out.println(md5Format11);
    // }

    public String encrypt(String originData) {
        try {
            DESedeKeySpec dks = new DESedeKeySpec(SECRET.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey securekey = keyFactory.generateSecret(dks);

            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, securekey);
            byte[] b = cipher.doFinal(originData.getBytes("UTF-8"));
            return new String(Base64.encodeBase64(b), "UTF-8").replaceAll("\r", "").replaceAll("\n", "");
        } catch (Exception e) {
            return "";
        }

    }

    public String decrypt(String encryptData) {
        try {
            // --通过base64,将字符串转成byte数组
            byte[] bytesrc = Base64.decodeBase64(encryptData.getBytes());
            // --解密的key
            DESedeKeySpec dks = new DESedeKeySpec(SECRET.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey securekey = keyFactory.generateSecret(dks);

            // --Chipher对象解密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, securekey);
            byte[] retByte = cipher.doFinal(bytesrc);

            return new String(retByte, "UTF-8");
        } catch (Exception e) {
            return "";
        }

    }

}
