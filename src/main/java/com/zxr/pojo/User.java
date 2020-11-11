package com.zxr.pojo;

import java.util.Date;
import java.util.List;

/**
 * @Author zhaoxiangrui
 * @create 2020/11/9 15:39
 */
public class User {

	private Integer id;
	private String username;
	private String password;
	private Date birthday;
	private List<Role> roles;
	private List<Order> orderList;

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orders) {
		this.orderList = orders;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", roles=" + roles +
				", orderList=" + orderList +
				'}';
	}
}
