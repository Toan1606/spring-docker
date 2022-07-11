package com.codedecode.demo.entity.API;

import com.codedecode.demo.entity.User;

 public class DataAPI {
	 private User userInfor;
	 public DataAPI(User user){
		 userInfor = user;
		}
	public User getUserInfor() {
		return userInfor;
	}
	public void setUserInfor(User userInfor) {
		this.userInfor = userInfor;
	}
}