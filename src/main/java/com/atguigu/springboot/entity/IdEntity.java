package com.atguigu.springboot.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@MappedSuperclass
public class IdEntity implements Serializable{
	/**
	 * //	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Transient
	private Map<String, Object> ext= new LinkedHashMap<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<String, Object> getExt() {
		return ext;
	}

	@SuppressWarnings("unchecked")
	public <T> T getExtValue(String key) {
		return (T)ext.get(key);
	}

	/**
	 * @param key
	 * @param value
	 */
	public void setExt(String key,Object value) {
		ext.put(key, value);
	}

	
}
