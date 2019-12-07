package com.yc.drp.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.yc.drp.bean.User;
import com.yc.drp.util.DbUtil;

public class UserManager {
	private static UserManager instance = new UserManager();

	private UserManager() {
	}

	public static UserManager getInstance() {
		return instance;
	}

	public void addUser(User user) {
		String sql = "insert into  t_user (userId,userName,password,contactTel,email,createDate) values(?,?,?,?,?,?)";
		Connection connection = DbUtil.getConnection();
		PreparedStatement prep = null;
		try {
			prep = connection.prepareStatement(sql);
			prep.setString(1, user.getUserId());
			prep.setString(2, user.getUserName());
			System.out.println(user.getUserName());
			prep.setString(3, user.getPassword());
			prep.setString(4, user.getContactTel());
			prep.setString(5, user.getEmail());
			prep.setTimestamp(6, new Timestamp(new Date().getTime()));
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.closeAll(connection, prep, null);
		}
	}
	public User findUserById(String userId){
		String sql = "select userId,userName,password,contactTel,email, createDate from t_user where userId=?";
		
		Connection conn =  null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		User user=null;
		try {
			conn= DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			if(rs.next()){
				user=new User();
				user.setUserId(rs.getString("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setContactTel(rs.getString("contact_tel"));
				user.setEmail(rs.getString("email"));
				user.setCreateDate(rs.getTimestamp("create_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbUtil.closeAll(conn, pstmt, rs);
		}
		return user;
	}
}
