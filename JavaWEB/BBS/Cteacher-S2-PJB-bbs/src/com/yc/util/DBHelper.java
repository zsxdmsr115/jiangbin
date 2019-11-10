package com.yc.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Blob;
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


public class DBHelper {
	protected static Connection con = null;
	protected PreparedStatement pstmt = null;
	protected ResultSet rs = null;
	public static int dbIndex;
	 static List<DBBean> dblist;
	 
	static {
		    
		   dblist  = SAXReader.newinstance();
		try {
			
			Class.forName(dblist.get(dbIndex).getDriver());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static  Connection getConnection() {
		try {
			System.out.println(dblist.get(dbIndex).getUrl());
												
			con = DriverManager.getConnection(dblist.get(dbIndex).getUrl(),
					dblist.get(dbIndex).getUser(),
					dblist.get(dbIndex).getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public void closeAll(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				LogUtil.log.error(e.toString());
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				LogUtil.log.error(e.toString());
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				LogUtil.log.error(e.toString());
			}
		}
	}

	/**
	 * key���� �� value:�����һ������ ��ѯ�����Ľ���浽Map��,һ�����ݾ�һ��Map;
	 * 
	 * @param sql
	 * @param objs
	 * @return
	 */
	public List<Map<String, Object>> finds(String sql, Object... objs) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			// ��ռλ����ֵ
			this.setValues(pstmt, objs);
			rs = pstmt.executeQuery();// ��ȡ�����
			// ��ȡ���ؽ���е��е�����
			ResultSetMetaData rsmd = rs.getMetaData();
			int colLen = rsmd.getColumnCount(); // ��ȡ��������е�����
			String[] colNames = new String[colLen];
			for (int i = 0; i < colLen; i++) {// ȡ��ÿ���е�������ŵ�������
				colNames[i] = rsmd.getColumnName(i + 1);
			}

			Map<String, Object> map = null;
			String typeName;
			Object obj;
			while (rs.next()) {// ѭ��ȡֵ��ÿѭ��һ�ξ���һ����¼����ŵ�һ��map��
				map = new HashMap<String, Object>();
				for (int i = 0; i < colLen; i++) {
					obj = rs.getObject(colNames[i]);// ѭ��ȡ��ÿ���е�ֵ
					if (obj != null) {
						typeName = obj.getClass().getSimpleName();

						if ("BLOB".equals(typeName)) {
							Blob blob = rs.getBlob(colNames[i]);
							byte[] bt = null;
							BufferedInputStream bis = null;
							bis = new BufferedInputStream(blob.getBinaryStream());
							bt = new byte[(int) blob.length()];
							try {
								bis.read(bt);
								map.put(colNames[i], bt);
							} catch (IOException e) {
								e.printStackTrace();
							} finally {

								if (bis != null) {
									try {
										bis.close();
									} catch (Exception e) {
										e.printStackTrace();
									}
								}

							}

						} else {

							map.put(colNames[i], rs.getString(colNames[i]));
						}
					} else {
						map.put(colNames[i], ""); // �Ե�ǰ�е�����Ϊ�����Ե�ǰ�е�ֵΪֵ
					}

				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll(con, pstmt, rs);
		}
		return list;
	}

	public void setValues(PreparedStatement pstmt, List<Object> params) {
		if (pstmt != null && params != null && params.size() > 0) {
			String type = null;
			for (int i = 0; i < params.size(); i++) {
				Object o = params.get(i);
				try {
					if (o != null) {
						type = o.getClass().getName();
						if ("javax.sql.rowset.serial.SerialBlob".equals(type)) {
							pstmt.setBlob(i + 1, (Blob) params.get(i));
						} else if ("java.lang.Integer".equals(type)) {
							pstmt.setInt(i + 1, Integer.parseInt(String.valueOf(o)));
						} else {
							pstmt.setString(i + 1, String.valueOf(o));
						}
					} else {
						pstmt.setString(i + 1, "");
					}

				} catch (SQLException e) {
					e.printStackTrace();
					LogUtil.log.error(e.toString());
				}
			}
		}
	}

	public void setValues(PreparedStatement pstmt, Object... objs) {
		if (pstmt != null && objs != null && objs.length > 0) {
			String type = null;
			for (int i = 0, len = objs.length; i < len; i++) {
				Object o = objs[i];

				try {
					if (o != null) {
						type = o.getClass().getName();
						if ("javax.sql.rowset.serial.SerialBlob".equals(type)) {
							pstmt.setBlob(i + 1, (Blob) o);
						} else if ("java.lang.Integer".equals(type)) {
							pstmt.setInt(i + 1, Integer.parseInt(String.valueOf(o)));
						} else {
							pstmt.setString(i + 1, String.valueOf(o));
						}
					} else {
						pstmt.setString(i + 1, "");
					}
				} catch (SQLException e) {
					e.printStackTrace();
					LogUtil.log.error(e.toString());
				}
			}
		}
	}

	/**
	 * ��ѯ�����Ľ����װ��bean��
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws NumberFormatException
	 */

	public <T> List<T> find(String sql, Class<T> c, Object... params) throws InstantiationException,
			IllegalAccessException, NumberFormatException, IllegalArgumentException, InvocationTargetException {
		List<Map<String, Object>> listMap = finds(sql, params);
		List<T> list = new ArrayList<T>();
		T t = null;
		if (listMap == null || listMap.size() <= 0) {
			return list;
		}
		// ��ȡ��һ��Map�����ܹ���ȡ���еļ�ֵ
		Map<String, Object> map = listMap.get(0);
		// ��ȡ��ֵ
		Set<String> keys = map.keySet();
		// ������ƴ����set�����Ҫ�ҵķ�����
		Set<String> methodNames = getMethodName(keys);
		Method[] ms = c.getMethods();
		for (Map<String, Object> m : listMap) {
			t = (T) c.newInstance(); // �����������ʵ��������Product
			// ѭ�����еķ������鵽 ��methodNames �е���ͬ�ķ���
			for (Method method : ms) {
				for (String mn : methodNames) { // setResadmin
					if (method.getName().equals(mn)) {
						// �������method ��invoke
						String keyname = mn.substring(3, 4).toLowerCase() + mn.substring(4);

						String typeName = method.getParameterTypes()[0].getName();
						
						if ("java.lang.Integer".equals(typeName) || "int".equals(typeName)) {
							
							method.invoke(t, Integer.parseInt(m.get(keyname).toString()));
						} else if ("java.lang.Double".equals(typeName) || "double".equals(typeName)) {
							method.invoke(t, Double.parseDouble(m.get(keyname).toString()));
						} else if ("java.lang.Float".equals(typeName) || "float".equals(typeName)) {
							method.invoke(t, Float.parseFloat(m.get(keyname).toString()));
						} else if ("java.lang.Long".equals(typeName) || "long".equals(typeName)) {
							method.invoke(t, Long.parseLong(m.get(keyname).toString()));
						} else {
							method.invoke(t, m.get(keyname).toString());
							
						}
						break;
					}
				}
			}
			list.add(t);
		}
		return list;
	}

	private Set<String> getMethodName(Set<String> keys) {
		Set<String> result = new HashSet<String>();
		for (String key : keys) {
			String newName = key.substring(0, 1).toUpperCase() + key.substring(1);
			result.add("set" + newName);
		}
		return result;
	}

	/**
	 * ��ѯ������
	 * 
	 * @param <T>
	 *            ���ͣ�����Ҫ�õ��ļ����д�Ķ��������
	 * @param sql:
	 *            ��ѯ��䣬���Ժ���?
	 * @param params:
	 *            ?����Ӧ�Ĳ���ֵ�ļ���
	 * @param c��
	 *            ������������Ӧ�ķ������
	 * @return ���洢�˶���ļ���
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws NumberFormatException
	 */
	public <T> List<T> find(String sql, Class<T> c, List<Object> params) throws InstantiationException,
			IllegalAccessException, NumberFormatException, IllegalArgumentException, InvocationTargetException {
		if (params == null) {
			return find(sql, c);
		} else {
			return find(sql, c, params.toArray());
		}
	}

	public double getCount(String sql, List<Object> params) {
		double d = 0.0;
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			setParams(pstmt, params);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				d = rs.getDouble(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			closeAll(con, pstmt, rs);
		}
		return d;
	}

	public void setParams(PreparedStatement pstmt2, List<Object> params) throws SQLException {
		if (params != null&&params.size() > 0 ) {
			for (int i = 0; i < params.size(); i++) {
				pstmt2.setString((i + 1), params.get(i).toString());
			}
		}
	}

	public int doUpdate(String sql, List<Object> params) {
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			setParams(pstmt, params);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * ��ѯ: ����һ��Map����, ֻ��һ������ ����ж������������������� select * from ���� where id=1;
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws NamingException
	 */
	public Map<String, String> findSingleObject(String sql, List<Object> params)
			throws SQLException, IOException {
		Map<String, String> map = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			setParams(pstmt, params);
			rs = pstmt.executeQuery();
			List<String> columneName = getAllColumeNames(rs);

			if (rs.next()) {
				// if (rs.isLast()) {
				map = new HashMap<String, String>();
				for (String cn : columneName) {
					map.put(cn, rs.getString(cn));
				}
				// } else {
				// throw new RuntimeException(
				// "��ѯ�������ж�������ʹ�ñ����е� findMultiObject()");
				// }
			}
		} finally {
			closeAll(con, pstmt, rs);
		}
		return map;
	}
	/**
	 * �ӽ������ȡ�����е��������浽һ������list�� : ������: jdbc2.0ȡԪ����
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private List<String> getAllColumeNames(ResultSet rs) throws SQLException {
		List<String> columneName = new ArrayList<String>();
		if (rs != null) {
			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				columneName.add(rs.getMetaData().getColumnName(i + 1));
			}
		}
		return columneName;
	}
}
