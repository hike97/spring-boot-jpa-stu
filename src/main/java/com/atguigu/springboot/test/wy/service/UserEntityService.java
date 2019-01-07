package com.atguigu.springboot.test.wy.service;

import com.atguigu.springboot.test.wy.repository.entity.UserEntity;

/**
 * @author hike97
 * @create 2018-11-15 16:18
 * @desc
 **/
public interface UserEntityService {

	UserEntity finByWeDoctorId (String id);
}
