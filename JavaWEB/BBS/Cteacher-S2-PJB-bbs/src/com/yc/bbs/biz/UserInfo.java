package com.yc.bbs.biz;

import java.util.List;
import java.util.Map;

import com.yc.bbs.bean.User;

public interface UserInfo {
   public List<User> findUsers(Map<String,String> condition);
   public User  getUserById(int uid);    
	/**
	 * 注册操作
	 * @param user:要注册的用户信息(用户名，密码，头像，性别)
	 * @return返回新注册的用户信息
	 */
	public User reg(User user);
   
}
