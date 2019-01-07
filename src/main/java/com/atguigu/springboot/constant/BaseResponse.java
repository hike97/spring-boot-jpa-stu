package com.atguigu.springboot.constant;

import lombok.Data;

/**
 * @author hike97
 * @create 2018-11-15 16:26
 * @desc 返回值基础类
 **/
@Data
public class BaseResponse<T> {

	private Integer code;

	private String msg;

	private T data;

	public BaseResponse () {
		this.code = 0;
		this.msg = "success";
	}
}
