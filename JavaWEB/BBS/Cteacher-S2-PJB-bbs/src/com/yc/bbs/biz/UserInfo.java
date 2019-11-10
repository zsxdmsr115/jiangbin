package com.yc.bbs.biz;

import java.util.List;
import java.util.Map;

import com.yc.bbs.bean.User;

public interface UserInfo {
   public List<User> findUsers(Map<String,String> condition);
   public User  getUserById(int uid);    
	/**
	 * ע�����
	 * @param user:Ҫע����û���Ϣ(�û��������룬ͷ���Ա�)
	 * @return������ע����û���Ϣ
	 */
	public User reg(User user);
   
}
