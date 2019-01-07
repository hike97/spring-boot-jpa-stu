package com.atguigu.springboot.common.util.test;

import com.atguigu.springboot.common.util.BusinessCryptoUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;

/**
 * @author
 * @date 2018年11月17日
 * @Version V1.0
 */

public class GetLoginURL {

    public static void main(String args[]) throws JSONException {
        wy();
    }

    private static void wy() throws JSONException {
        // 线上
        // BusinessCryptoUtils utils = new
        // BusinessCryptoUtils("uMMjJnrCiF0mLzqa9Tj6Bk2V");
        // 测试环境
        BusinessCryptoUtils utils = new BusinessCryptoUtils("6QNb29csQ9HJICgrmTR5Q4U8");
        JSONObject json = new JSONObject();
        json.put("id", "taikang412");// 唯一id,必填
        try {

            String encodeInfo = utils.encrypt(json.toString());
            // String target = "/my/order/list";// 我的预约列表
            String target = "/fastorder/hospital";
            String timestamp = String.valueOf(System.currentTimeMillis());
            String sign = DigestUtils.md5Hex(encodeInfo + timestamp);
            encodeInfo = URLEncoder.encode(encodeInfo);
            String jumpUrl = Constants.LOGIN_URL + "/business/entry" + "?info=" + encodeInfo + "&timestamp="
                    + timestamp + "&t=" + target + "&sign=" + sign;

            System.out.println(jumpUrl);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
