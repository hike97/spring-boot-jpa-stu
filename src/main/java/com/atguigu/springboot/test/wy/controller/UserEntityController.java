package com.atguigu.springboot.test.wy.controller;

import com.atguigu.springboot.constant.BaseResponse;
import com.atguigu.springboot.test.wy.repository.UserEntityRepository;
import com.atguigu.springboot.test.wy.repository.entity.UserEntity;
import com.atguigu.springboot.test.wy.service.UserEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hike97
 * @create 2018-11-15 16:16
 * @desc
 **/
@Slf4j
@RestController
@RequestMapping(value = "/user",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserEntityController {

	@Autowired
	private UserEntityService userEntityService;

	@Autowired
	private UserEntityRepository repository;

	@PostMapping(value = "/addUser")
	public BaseResponse insertUser(@RequestBody UserEntity entity){
		System.out.println ("---->:"+entity);
		try {
			repository.save (entity);
		} catch (Exception e) {
			e.printStackTrace ();
		}
		return new BaseResponse ();
	}
}
