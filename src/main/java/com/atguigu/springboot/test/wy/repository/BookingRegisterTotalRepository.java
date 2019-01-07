package com.atguigu.springboot.test.wy.repository;

import com.atguigu.springboot.test.wy.repository.entity.BookingRegisterTotal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author hike97
 * @create 2018-11-15 16:14
 * @desc 微医消息推送接口
 **/
public interface BookingRegisterTotalRepository extends MongoRepository<BookingRegisterTotal,ObjectId> {

	BookingRegisterTotal findByWeDoctorId (String id);
}
