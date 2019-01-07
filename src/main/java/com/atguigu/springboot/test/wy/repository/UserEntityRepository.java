package com.atguigu.springboot.test.wy.repository;

import com.atguigu.springboot.test.wy.repository.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author hike97
 * @create 2018-11-15 16:14
 * @desc 用户操作接口
 **/
public interface UserEntityRepository extends MongoRepository<UserEntity,ObjectId> {

	UserEntity findByWeDoctorId (String id);
}
