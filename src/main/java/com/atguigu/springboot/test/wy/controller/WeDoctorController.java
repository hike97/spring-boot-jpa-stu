package com.atguigu.springboot.test.wy.controller;

import com.atguigu.springboot.constant.BaseResponse;
import com.atguigu.springboot.test.wy.dto.BookingRegisterOrderParam;
import com.atguigu.springboot.test.wy.repository.entity.BookingRegisterOrder;
import com.atguigu.springboot.test.wy.repository.entity.BookingRegisterTotal;
import com.atguigu.springboot.test.wy.repository.entity.UserEntity;
import com.atguigu.springboot.test.wy.service.UserEntityService;
import com.atguigu.springboot.test.wy.service.WeDoctorService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author hike97
 * @create 2018-11-15 17:02
 * @desc 微医控制层
 **/
@Slf4j
@Controller
@RequestMapping (value = "/user-doctor",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WeDoctorController {

//	@Autowired
//	private UserEntityRepository repository;

//	@Autowired
//	MongoOperations mongoOps;

	@Autowired
	WeDoctorService weDoctorService;

	@Autowired
	UserEntityService userEntityService;

	/**
	 * 测试
	 */
	@PostMapping(value ="testLoginUrl" )
	@ResponseBody
	public BaseResponse loginUrl(String objectId){
		BaseResponse baseResponse = new BaseResponse<> ();
		Map<String, Object> map;


	}
	/**
	 * 生成微医id
	 * @param objectId
	 * @return
	 */
	@GetMapping(value = "/importUser")
	@ResponseBody
	public BaseResponse importUser(String objectId){
		BaseResponse<Object> baseResponse = new BaseResponse<> ();
		Map<String, Object> map = new LinkedHashMap<> ();
		ObjectId userId = new ObjectId (objectId);
		//判断是否实名
		UserEntity user = weDoctorService.isRealName(userId);
		if (StringUtils.isEmpty (user)){
			baseResponse.setMsg ("该用户未实名！！！");
		}else {
			//进行用户导入操作
			map = weDoctorService.importWeDoctorUser(user);
			//判断是否导入成功
			String code = map.get ("code").toString ();
			if (!StringUtils.isEmpty (code)){
				if (!code.equals ("0")){
					if (code.equals ("2")){
						baseResponse.setMsg ("已订购此套餐");
					}else {
						baseResponse.setMsg ("导入用户失败");
					}
				}else {
					baseResponse.setMsg ("导入用户成功");
				}
			}
		}

		return  baseResponse;
	}

	/**
	 * 登录操作
	 * 传入id 进行信息加密返回url
	 * @param weDoctorId
	 * @return
	 */
	@GetMapping(value = "/toLoginUrl")
	@ResponseBody
	public String toLoginUrl(String weDoctorId){
		String url = "";
		int type = 1;//登录跳转
		if (isExistUser(weDoctorId)){
		   url="该用户不存在或未导入";
		}else {
			url = weDoctorService.geturl(weDoctorId, type);
		}
		return url;
	}

	/**
	 * 我的预约
	 * 传入id 进行信息加密返回url
	 * @param weDoctorId
	 * @return
	 */
	@GetMapping(value = "/toOrderUrl")
	@ResponseBody
	public String toOrderUrl(String weDoctorId){
		String url = "";
		int type = 2;//登录跳转
		if (isExistUser(weDoctorId)){
		   url="该用户不存在或未导入";
		}else {
			url = weDoctorService.geturl(weDoctorId, type);
		}
		return url;
	}

	/**
	 * 推送接口
	 * @param
	 * @return
	 */
	@PostMapping("/insertRegisterOrders")
	@ResponseBody
	public BaseResponse insertRegisterOrders(@RequestBody BookingRegisterOrderParam orderParam, HttpServletRequest request){
		BaseResponse baseResponse = new BaseResponse<> ();
		String id = orderParam.getUserId ();
		if(isExistUser(id)){
			baseResponse.setMsg ("该账号不存在，推送失败");
			return  baseResponse;
		}else{
			List<BookingRegisterOrder> orderList = null;
			BookingRegisterTotal total = weDoctorService.findByWeDoctorId (id);
			if (StringUtils.isEmpty (total)){
				//首次存储
				total= new BookingRegisterTotal ();
				total.setWeDoctorId (id);
				orderList = new ArrayList<> ();
			}else {
				//再次存储
				orderList = total.getBookingRegisterOrderList ();
			}
			BookingRegisterOrder order = new BookingRegisterOrder ();
			//日期格式转换
			SimpleDateFormat sdf = new SimpleDateFormat ("yyyyMMddHHmmss");
			SimpleDateFormat sdf2 = new SimpleDateFormat ("yyyyMMdd");
			BeanUtils.copyProperties (orderParam,order);
			try {
				Date createTime = sdf.parse (orderParam.getCreateTime ());
				Date appointTime = sdf2.parse (orderParam.getAppointTime ());
				order.setAppointTime (appointTime);
				order.setCreateTime (createTime);
			} catch (ParseException e) {
				e.printStackTrace ();
			}
			System.out.println ("++++:"+order);
			orderList.add (order);
			total.setBookingRegisterOrderList (orderList);
			weDoctorService.insertRegisterOrder(total);
			baseResponse.setMsg ("数据导入成功！！！");
			return baseResponse;
		}
	}


	//判断用户是否存在
	public boolean isExistUser(String weDoctorId){
		UserEntity entity  =  userEntityService.finByWeDoctorId(weDoctorId);
		return StringUtils.isEmpty (entity);
	}
}
