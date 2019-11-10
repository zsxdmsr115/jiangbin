package com.yc.bbs.dao;

import java.util.List;
import java.util.Map;

import com.yc.bbs.bean.User;

public interface UserInfoDao {
   public List<User> findUsers(Map<String,String>condtion);

   public User getUserById(int uid);

   public double getCount(String sql, List<Object> params);
   public User reg(User user);
} 
