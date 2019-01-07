package com.atguigu.springboot.test.wy.dto;

import lombok.Data;


/**
 * @author hike97
 * @create 2018-11-19 16:37
 * @desc
 **/
@Data
public class BookingRegisterOrderParam {

	private String appointTime;    //预约时间
	private String createTime;	//创建时间
	private Long deptId;		//科室Id
	private String deptName;   //科室名称
	private Long	doctorId;  //医生Id
	private String  doctorName; //医生姓名
	private Long  	hospitalId; //医院Id
	private String	hospitalName; //医院名称
	private String	orderFee; //挂号费用
	private Long    orderId; //订单Id
	private String	outPatientType;//门诊类型
	private String  patientName; //就诊人姓名
	private String	status; //订单状态
	private String  timeType; //时间段类型
	private String  userId; //第三方用户Id
}
