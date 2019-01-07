package com.atguigu.springboot.common.util.test;

import com.atguigu.springboot.common.util.DESUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.Request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hike97
 * @create 2018-11-14 17:03
 * @desc 开发步骤
 **/
public class OperationStep {

	//接口一：调用接口
	public Map<String,Object> wylogin(Request request){
		//1.获取用户
		//2.判断用户是否实名
		//3.实名的情况下 判断wyid 是否存在 存在 --->已预约 不存在未预约 用uuid生成wyid标志是否存
		//4.成功 把wyid 返回
		return  new HashMap<> ();
	}

	//接口二：订单接口
	public void sign(String wyid) throws JSONException {
		//1.根据wyid获取user 获取user
		JSONObject object = new JSONObject ();
		/*id=用户系统用户的唯一标识
		name=姓名
		idcard=身份证号
		mobile=手机*/
		object.put ("id",wyid);
		object.put ("name","naroto");
		String DEC_KEY = "12SqqsRfflliiiaooii9999@#";
		DESUtil.DESEncode (object.toString (),DEC_KEY);
	}

	public static void main (String[] args) throws JSONException, ParseException {
		JSONObject object = new JSONObject ();
		/*id=用户系统用户的唯一标识
		name=姓名
		idcard=身份证号
		mobile=手机*/
		object.put ("id",999);
		object.put ("name","naroto");
		String DEC_KEY = "12SqqsRfflliiiaooii9999@#";
		String s = DESUtil.DESEncode (object.toString (), DEC_KEY);
		System.out.println (s);
		String s1 = DESUtil.DESDecode (s, DEC_KEY);
		System.out.println (s1);
		SimpleDateFormat format = new SimpleDateFormat ("yyyyMMddHHmmss");
		Date parse = format.parse ("20180720101045");
		
	}
}
