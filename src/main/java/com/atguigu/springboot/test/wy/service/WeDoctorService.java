package com.atguigu.springboot.test.wy.service;

import com.atguigu.springboot.test.wy.repository.entity.BookingRegisterTotal;
import com.atguigu.springboot.test.wy.repository.entity.UserEntity;
import org.bson.types.ObjectId;

import java.util.Map;

/**
 * @author hike97
 * @create 2018-11-16 15:02
 * @desc
 **/
public interface WeDoctorService {

	UserEntity isRealName (ObjectId id);

	Map<String, Object> importWeDoctorUser (UserEntity user);

	String geturl (String id, int type);

	void insertRegisterOrder (BookingRegisterTotal total);

	BookingRegisterTotal findByWeDoctorId (String id);
}
