package com.yc.bbs.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.bbs.bean.User;
import com.yc.bbs.dao.UserInfoDao;
import com.yc.util.DBHelper;
import com.yc.util.Encrypt;

public class UserInfoDaoImpl extends DBHelper implements UserInfoDao {

	List list = new ArrayList();

	@Override
	public List<User> findUsers(Map condtion) {
		dbIndex = 0;
		String uname = (String) condtion.get("uname");
		String upass = (String) condtion.get("upass");
		String sql = "select * from tbl_user where uname=? and upass=?";
		list.add(uname);
		list.add(Encrypt.md5(upass));// 加密
		try {
			System.out.println("List<User>===" + list.get(1));
			return find(sql, User.class, list);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User getUserById(int uid) {
		dbIndex = 0;
		String sql = "select * from tbl_user where uid=" + uid;
		List<User> ulist = null;
		try {
			ulist = find(sql, User.class);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return ulist.get(0);
	}

	@Override
	public User reg(User user) {
		if (user == null) {
			throw new RuntimeException("argument should not be null");
		}
//		if (checkUname(user.getUname())) {
//			throw new RuntimeException(user.getUname() + "has been registered");
//		}
		String sql="insert into tbl_user(uname,upass,head,regtime,gender) values(?,?,?,now(),?)";
		List<Object> params=new ArrayList<Object>();
		params.add(user.getUname());
		//密码要加密后存储
		params.add(Encrypt.md5(user.getUpass()));
		params.add(user.getHead());
		params.add(user.getGender());
		double r=0.0;
		try {
			r=doUpdate(sql, params);
			sql="select uid,regtime from tbl_user order by uid desc limit 1,1";
			Map<String,String> map=findSingleObject(sql, null);
			user.setUid(Integer.parseInt(map.get("uid")));
			user.setRegtime(map.get("regtime"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		return user;
	}

	private boolean checkUname(String uname) {
		String sql = "select count(uname) from tbl_user where uname=?";
		List<Object> params = new ArrayList<Object>();
		params.add(uname);
		double r = 0.0;
		try {
			r =getCount(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		if (r > 0) {
			return true;
		} else {
			return false;
		}
	}

}
