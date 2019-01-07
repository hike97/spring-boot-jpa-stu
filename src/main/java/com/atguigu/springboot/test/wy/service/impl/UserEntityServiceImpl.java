package com.atguigu.springboot.test.wy.service.impl;

import com.atguigu.springboot.test.wy.repository.UserEntityRepository;
import com.atguigu.springboot.test.wy.repository.entity.UserEntity;
import com.atguigu.springboot.test.wy.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hike97
 * @create 2018-11-15 16:18
 * @desc
 **/
@Service
public class UserEntityServiceImpl implements UserEntityService {
	@Autowired
	UserEntityRepository repository;
	@Override
	public UserEntity finByWeDoctorId (String id) {
		return repository.findByWeDoctorId (id);
	}
}
