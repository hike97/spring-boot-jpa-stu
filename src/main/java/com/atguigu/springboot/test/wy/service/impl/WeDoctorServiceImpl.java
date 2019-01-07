package com.atguigu.springboot.test.wy.service.impl;

import com.atguigu.springboot.common.util.BusinessCryptoUtils;
import com.atguigu.springboot.common.util.OrderPackageRequest;
import com.atguigu.springboot.common.util.XmlConverter;
import com.atguigu.springboot.common.util.test.Constants;
import com.atguigu.springboot.test.wy.repository.BookingRegisterTotalRepository;
import com.atguigu.springboot.test.wy.repository.UserEntityRepository;
import com.atguigu.springboot.test.wy.repository.entity.BookingRegisterTotal;
import com.atguigu.springboot.test.wy.repository.entity.UserEntity;
import com.atguigu.springboot.test.wy.service.WeDoctorService;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author hike97
 * @create 2018-11-16 15:02
 * @desc
 **/
@Service
public class WeDoctorServiceImpl implements WeDoctorService{

	@Autowired
	UserEntityRepository repository;

	@Autowired
	BookingRegisterTotalRepository totalRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public UserEntity isRealName (ObjectId userId) {
		UserEntity entity = repository.findById (userId).get ();
		String weDoctorId = null;
		//判断是否实名
		boolean name = entity.isRealName ();
		if (name){
			String doctorId = entity.getWeDoctorId ();
			if (StringUtils.isEmpty (doctorId)){
				weDoctorId = UUID.randomUUID ().toString ();
				entity.setWeDoctorId (weDoctorId);
				repository.save (entity);
			}
			return entity;
		}
		return null;
	}

	@Override
	public Map<String, Object> importWeDoctorUser (UserEntity entity) {
		Map<String,Object> map = new HashMap<> ();
		//用户信息和套餐的导入
		map = applyService(entity);
		return map;
	}


	@Override
	public String geturl (String id, int type) {
		// 线上
		// BusinessCryptoUtils utils = new
		// BusinessCryptoUtils("uMMjJnrCiF0mLzqa9Tj6Bk2V");
		// 测试环境
		BusinessCryptoUtils utils = new BusinessCryptoUtils("6QNb29csQ9HJICgrmTR5Q4U8");
		org.json.JSONObject json = new org.json.JSONObject ();
		//json.put("id", "taikang412");// 唯一id,必填
		json.put("id", id);// 唯一id,必填
		try {

			String encodeInfo = utils.encrypt(json.toString());
			String target = "";
			if (type==1){
				target = "/fastorder/hospital";
			}else if (type==2){
				target = "/my/order/list";// 我的预约列表
			}

			String timestamp = String.valueOf(System.currentTimeMillis());
			String sign = DigestUtils.md5Hex(encodeInfo + timestamp);
			encodeInfo = URLEncoder.encode(encodeInfo);
			String jumpUrl = Constants.LOGIN_URL + "/business/entry" + "?info=" + encodeInfo + "&timestamp="
					+ timestamp + "&t=" + target + "&sign=" + sign;

			System.out.println(jumpUrl);
			return jumpUrl;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 消息推送
	 * @param total
	 */
	@Override
	public void insertRegisterOrder (BookingRegisterTotal total) {
		totalRepository.save (total);
	}

	@Override
	public BookingRegisterTotal findByWeDoctorId (String id) {
		return totalRepository.findByWeDoctorId(id);
	}

	/**
	 * 用户购买套餐服务
	 */
	private Map<String,Object>  applyService(UserEntity userEntity) {
		HttpClient httpclient = new HttpClient();
		Map<String,Object> map = new LinkedHashMap<> ();
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
			/**
			 * 用户信息
			 */
			request.setProofNo(userEntity.getWeDoctorId ()); // 用户验证唯一ID（必填）
			request.setIdCard(userEntity.getIdCardNumber ()); // 用户身份证号（选填）
			request.setName(userEntity.getName ()); // 用户姓名（选填）
			request.setPackageCode("PD476"); // 套餐编号 (必填)，由挂号网提供
			request.setPartnerPackageName("泰康预约挂号");
			String timestamp = new SimpleDateFormat ("yyyyMMddHHmmss").format(new Date());
			request.setEffectTime(timestamp); // 订购时间，时间戳格式(必填)
			String body = XmlConverter.toXML(request, OrderPackageRequest.class);


			RequestEntity entity = new StringRequestEntity (body, "text/xml", "utf-8");
			post.setRequestEntity(entity);
			post.getParams().setContentCharset("UTF-8");
			signHeader(post);

			int result = httpclient.executeMethod(post);

			System.out.println("Response status code: " + result);
			System.out.println("Response body: ");
			System.out.println(post.getResponseBodyAsString());
			//将xml返回json读取responseCode的值传回前台
			//		0	成功
			//		1	请求参数为空
			//		2	已订购此套餐
			//		3	系统异常
			//		4	未订购服务
			//		99	合作方partnerId为空
			//		100	未知来源
			//		101	未知用户验证类型
			//		102	验证类型不可为空
			//		103	二代身份证号格式有误
			//		104	手机号格式有误
			//		105	用户姓名只能为汉字组成
			//		106	ProofNo最大长度为40位，请核实！
			//		107	proofNo：用户唯一编号不可为空
			//		108	用户姓名不可为空
			XMLSerializer xmlSerializer = new XMLSerializer ();
			String xml  = post.getResponseBodyAsString ();
			String resutStr = xmlSerializer.read(xml).toString();
			JSONObject json = JSONObject.fromObject(resutStr);
			Object code = json.get ("ResponseCode");
			System.out.println (code);
			map.put ("code",code);
		} catch (Exception e) {
			map.put ("msg","导入用户发生异常");
		}
		return map;
	}
	//第三方方法
	/**
	 *
	 */
	private static final String HEAD_NAME_SIGNATURE = "sign";

	/**
	 *
	 */
	private static final String HEAD_NAME_PARTNER_ID = "partner";
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
			List<String> list = new ArrayList<String> ();
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
