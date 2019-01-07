package com.atguigu.springboot.common.util.test;


import com.atguigu.springboot.common.util.OrderPackageRequest;
import com.atguigu.springboot.common.util.XmlConverter;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 
 * @type InterfaceTest
 * @desc 订购接口测试
 * @author
 * @date 2018年11月17日
 * @version V1.0
 */
public class PackageOrder {
    /**
     * 
     */
    private static final String HEAD_NAME_SIGNATURE = "sign";

    /**
     * 
     */
    private static final String HEAD_NAME_PARTNER_ID = "partner";

    public static void main(String[] args) {
        /**
         * 订购套餐 1 {<actionType>2</actionType>} <br/>
         * 取消套餐 2 {<actionType>2</actionType>}
         */
        applyServiceTest();
    }

    /**
     * 用户购买套餐服务
     */
    private static void applyServiceTest() {
        HttpClient httpclient = new HttpClient();

        try {
            // 传xml文件
            // 按entity内容传
            // Sent HTTP POST request to add customer
            System.out.println("\n");
            System.out.println("Sent HTTP POST request to add customer");
            PostMethod post = new PostMethod(Constants.IMPORT_URL); // 订购测试地址

            // 推送内容，XML格式
            OrderPackageRequest request = new OrderPackageRequest();
            request.setActionType("1");// 类型1为导入用户（必填）
            request.setProofNo("taikang412"); // 用户验证唯一ID（必填）
            request.setIdCard("41272819880405"); // 用户身份证号（选填）
            request.setName("刘测试"); // 用户姓名（选填）
            // request.setMobile("17600736129"); // 用户手机（选填）
            // 银联协助预约套餐：
            request.setPackageCode("PD476"); // 套餐编号 (必填)，由挂号网提供
            request.setPartnerPackageName("泰康预约挂号");
            // 套餐名，命名包括两部分，前面部分由客户简称，如“阳光人寿”，第二部分子客户（保险公司的客户），渠道，第三部分
            // 套餐，以“-”分隔(必填)
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            request.setEffectTime(timestamp); // 订购时间，时间戳格式(必填)
            String body = XmlConverter.toXML(request, OrderPackageRequest.class);

            // String body =
            // "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><responseinfo><actionType>2</actionType><idCard>330381198801017878</idCard><mobile>18628177883</mobile><name>白先生</name></responseinfo>";
            System.out.println(body);
            RequestEntity entity = new StringRequestEntity(body, "text/xml", "utf-8");
            post.setRequestEntity(entity);
            post.getParams().setContentCharset("UTF-8");
            signHeader(post);

            int result = httpclient.executeMethod(post);

            System.out.println("Response status code: " + result);
            System.out.println("Response body: ");
            System.out.println(post.getResponseBodyAsString());
            String xml = post.getResponseBodyAsString();//假设为xml字符串
            XMLSerializer xmlSerializer = new XMLSerializer ();
            String resutStr = xmlSerializer.read(xml).toString();
            JSONObject json = JSONObject.fromObject(resutStr);
            System.out.println ("json:"+json);
            Object code = json.get ("ResponseCode");
            System.out.println (code);
        } catch (Exception e) {
        }
    }

    /**
     * 请求头设置
     * 
     * @param post
     */
    public static void signHeader(PostMethod post) {
        // 挂号平台分配的接入ID
        post.addRequestHeader(HEAD_NAME_PARTNER_ID, Constants.PARTNER_ID);
        // 参数做摘要
        post.addRequestHeader(HEAD_NAME_SIGNATURE, signature(post));

    }

    public static String signature(PostMethod post) {

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        String sign = "";
        // 签名
        if (post.getParameters().length == 0) {
            // 对entity签名
            try {
                post.getRequestEntity().writeRequest(os);
                String content = os.toString("UTF-8");
                sign = getMD5Format(new String[] { Constants.PARTNER_KEY, content });
                System.out.println(sign);
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }
        } else {
            List<String> list = new ArrayList<String>();
            for (NameValuePair nv : post.getParameters()) {
                list.add(nv.getName() + nv.getValue());
            }
            // 排序
            Collections.sort(list);
            String[] datas = new String[post.getParameters().length + 1];
            datas[0] = Constants.PARTNER_KEY;
            for (int i = 0; i < list.size(); i++) {
                datas[i + 1] = list.get(i);
            }
            sign = getMD5Format(datas);
        }
        return sign;
    }

    public static String getMD5Format(String[] data) {
        try {
            MessageDigest message = MessageDigest.getInstance("MD5");
            if (message == null) {
                message = MessageDigest.getInstance("MD5");
            }
            for (String str : data) {
                message.update(str.getBytes("UTF-8"));
            }
            byte[] b = message.digest();
            String digestHexStr = "";
            for (int i = 0; i < 16; i++) {
                digestHexStr += byteHEX(b[i]);
            }

            return digestHexStr;
        } catch (Exception e) {
            return null;
        }
    }

    // 用来将字节转换成 16 进制表示的字符
    static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /***
     * 
     * @Title: byteHEX
     * @Description:
     * @author wujl
     * @param ib
     * @return String 返回类型
     */
    private static String byteHEX(byte ib) {
        char[] ob = new char[2];
        ob[0] = hexDigits[(ib >>> 4) & 0X0F];
        ob[1] = hexDigits[ib & 0X0F];
        String s = new String(ob);
        return s;
    }
}
