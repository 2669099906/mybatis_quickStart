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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", birthday=" + birthday +
				", roles=" + roles +
				", orderList=" + orderList +
				'}';
	}
}
