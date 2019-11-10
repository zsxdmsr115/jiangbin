package com.yc.dao;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.yc.bean.Resfood;
import com.yc.util.MyProperties;

public class DBHelper {
	static {
		try {
			Class.forName(MyProperties.newsInstance().getProperty("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
    public static void main(String[] args) {
		Connection connection = new DBHelper().getConnection();
	
	}
	public Connection getConnection() {
		Connection connection = null;
		try {
			Context initCtx = new InitialContext();
			DataSource datasource = (DataSource) initCtx.lookup("java:comp/env/jdbc/redes");
			connection = datasource.getConnection();
			System.out.println("连接1==="+connection);
			
		} catch (Exception e) {
			try {
				connection = DriverManager.getConnection(MyProperties.newsInstance().getProperty("url"),
						MyProperties.newsInstance().getProperty("uname"), MyProperties.newsInstance().getProperty("pwd"));
				
				System.out.println("连接2======"+connection);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
		return connection;

	}

	public void closeAll(Connection connection, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public <T> List<T> finds(String sql, Class<T> c, Object... params) {
		List<T> rlist = new ArrayList<T>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			ps = conn.prepareStatement(sql);

			setParams(ps, params);
			Method[] ms = c.getMethods();
			
			rs = ps.executeQuery();
			List<Map<String, Object>> list = columnNameMap(rs);
			T t = null;
			// 获取所有的键值
			Set<String> keySet = list.get(0).keySet();
			Set<String> mothodNames = getMothodName(keySet);// --set函数集合
			for (int i = 0; i < list.size(); i++) {
				t=c.newInstance();
				Map<String, Object> map = list.get(i);

				for (Method method : ms) {
					for (String mname : mothodNames) {
						if (method.getName().equals(mname)) {
							String keyname = mname.substring(3, 4).toLowerCase() + mname.substring(4);
							
							// 获取参数类型名字
							String typename = method.getParameterTypes()[0].getName();

							if ("java.lang.Integer".equals(typename) || "int".equals(typename)) {
							
								method.invoke(t, Integer.parseInt(map.get(keyname) + ""));
							} else if ("java.lang.Double".equals(typename) || "double".equals(typename)) {
								method.invoke(t, Double.valueOf(map.get(keyname) + ""));
							} else {
								method.invoke(t, map.get(keyname) + "");
							}
						}
					}
					
				}
				rlist.add(t);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, ps, rs);
		}

		return rlist;
	}

	private Set<String> getMothodName(Set<String> keySet) {
		Set<String> set = new HashSet<String>();
		for (String key : keySet) {
			String name = key.substring(0, 1).toUpperCase() + key.substring(1);
			set.add("set" + name);
		}
		return set;
	}

	public List<Map<String, Object>> columnNameMap(ResultSet rs) {
		Map<String, Object> map;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			String[] columnNames = new String[columnCount + 1];
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnName(i);// 列名

				columnNames[i] = columnName;
			}
			while (rs.next()) {
				map = new HashMap<String, Object>();
				for (int i = 0; i < columnNames.length; i++) {
					if (columnNames[i] != null) {

						map.put(columnNames[i], rs.getString(columnNames[i]));
					}
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

	

	public void setParams(PreparedStatement ps, Object... params) {
		if (params.length > 0 && params != null) {
			for (int i = 0; i < params.length; i++) {
				Object o = params[i];
				String className = o.getClass().getName();
				try {
					if ("java.lang.Integer".equals(className)) {
						ps.setInt(i + 1, Integer.parseInt(o + ""));
					} else if ("java.lang.Double".equals(className)) {
						ps.setDouble(i + 1, Double.valueOf(o + ""));
					} else {

						ps.setString(i + 1, o.toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
