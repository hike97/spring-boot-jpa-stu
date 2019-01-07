package com.atguigu.springboot.test.wy.repository.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @author hike97
 * @create 2018-11-15 16:03
 * @desc
 **/
@Data
@Document(collection = "user")
public class UserEntity {
	@Id
	private ObjectId userId;
	private String name;
	private String mobile;
	private String idCardNumber;
	private boolean isRealName;//是否实名
	private String weDoctorId;//是否授权微医
}
