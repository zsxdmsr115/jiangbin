package com.yc.bbs.biz.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.bbs.bean.User;
import com.yc.bbs.biz.UserInfo;
import com.yc.bbs.dao.UserInfoDao;
import com.yc.bbs.dao.impl.UserInfoDaoImpl;


public class UserInfoImpl implements UserInfo {
    UserInfoDao udao = new UserInfoDaoImpl();

	@Override
	public List<User> findUsers(Map<String, String> condition) {
		return udao.findUsers(condition);
	}
	@Override
	public User getUserById(int uid) {
		return udao.getUserById(uid);
	}
	@Override
	public User reg(User user) {
		
		return  udao.reg(user);
	}


}
