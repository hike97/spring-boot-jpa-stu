package com.atguigu.springboot.test.wy.repository.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author hike97
 * @create 2018-11-19 13:45
 * @desc 预约挂号订单列表
 **/
@Data
@Document(collection = "booking_register_total")
public class BookingRegisterTotal {
	@Id
	private ObjectId bookingRegisterTotalId;

	private String weDoctorId;

	private List<BookingRegisterOrder> bookingRegisterOrderList;
}
