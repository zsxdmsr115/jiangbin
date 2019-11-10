package com.yc.biz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yc.bean.Page;

import com.yc.bean.Resfood;
import com.yc.dao.DBHelper;

import redis.clients.jedis.Jedis;

public class ResfoodBizImpl {
	DBHelper db = new DBHelper();
	private ResultSet rs;


	/**
	 * ������
	 * 
	 * @return
	 */
	public int getcount(Map<String,String> map) {
		String sql = "select count(*) c from resfood where 1=1  ";
		 if(map!=null){
			 
			 for(Map.Entry<String, String> entry:map.entrySet()) {
				 String keyname = entry.getKey();
				 String value = entry.getValue();
				 sql+= "  and  "+keyname+" like '%"+value+"%'";
			 }
		 }
		Connection conn = null;
		PreparedStatement prep = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			conn = db.getConnection();
			
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();
			if (rs.next()) {
				result = rs.getInt("c");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public Resfood details(int id) {
		String sql = "select * from resfood where fid=?";
		List<Resfood> result = db.finds(sql, Resfood.class, id);
		return result.get(0);
	}
	/**
	 * ��ȫ��
	 * @return
	 */
	public List<Resfood> find(){
		Page<Resfood> p = new Page<Resfood>();
		p.setFlag(false);
	  return find(p).getList();
	}
	
	/**
	 * ��ҳ��
	 * 
	 * @param page
	 * @param params
	 * @return
	 */
	public Page<Resfood> find(Page<Resfood> page, String... params) {

		Jedis jedis = new Jedis("localhost", 6379);
		int praise = 0;
		int visited = 0;
		List<Resfood> rlist = selectLimit(page, params);
		System.out.println(rlist);
		List<Resfood> newRlist = new ArrayList<Resfood>();
		for (Resfood resfood : rlist) {

			try {
				praise = Integer.parseInt(jedis.get("food:" + resfood.getFid() + ":praise"));// ������

			} catch (Exception e) {
				praise = 0;

			}
			resfood.setParise((long) praise);
			try {

				visited = Integer.parseInt(jedis.get("food:" + resfood.getFid() + ":visited"));
			} catch (Exception e) {

				visited = 0;
			}
			resfood.setVisited((long) visited);
			newRlist.add(resfood);
		}
		page.setList(newRlist);
		return page;

	}

	public List<Resfood> selectLimit(Page<Resfood> page, String... params) {
		List<Resfood> rlist = null;
		try {
			String sql = "select * from resfood where 1=1";
			if (params != null && params.length!=0 ) {
				sql += " and fname like '%"+params[0]+"%' ";
			} 
			if (page.getFlag()) {
              
				int start = (page.getPageNo() - 1) * page.getPagesize();
				sql += " limit " + start + "," + page.getPagesize();
			}
			
		
			rlist = db.finds(sql, Resfood.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rlist;

	}

	/**
	 * ��food�����ݿ�������һ�η�����
	 * 
	 * @param food
	 * @return
	 */
	public Resfood visiteResfood(Resfood food) {
		Jedis jedis = new Jedis("localhost", 6379);
		long visited = jedis.incr("food:" + food.getFid() + ":visited");
		food.setVisited(visited);
		jedis.close();
		return food;
	}

	

	/**
	 * ��¼һ���˱���Щip���food:�˵ı��:ips ��¼һ���˵��޵����� food:�˵ı��
	 * 
	 * @param fid
	 * @param ip
	 * @return
	 */
	public long praise(int fid, String ip) {
		long praise = 0;
		Jedis jedis = new Jedis("localhost", 6379);
		// �ж����ip�Ƿ���޹�����ˣ��������ޣ�����������Ϊtrue
		if (jedis.sismember("food:" + fid + ":ips", ip) == false) {
			// ��һ�ε�
			jedis.sadd("food:" + fid + ":ips", ip);
			praise = jedis.incr("food:" + fid + ":praise");
		} else {// ȡ������
			jedis.decr("food:" + fid + ":praise");
			jedis.srem("food:" + fid + ":ips", ip);
		}
		jedis.close();
		return praise;
	}

	/**
	 * ��redis�м�¼���ip���������fid
	 * 
	 * @param ip
	 * @param fid
	 */
	@SuppressWarnings("resource")
	public void recordHistory(String ip, int fid) {
		Jedis jedis = new Jedis("localhost", 6379);
		Date d = new Date();
		long time = d.getTime();

		jedis.zadd("food:" + ip + ":foods", time, fid + "");
		jedis.close();
	}

	/**
	 * ����ip�������IP���ʹ������еĲ˵ı�ż��˵�ͼƬ
	 * 
	 * @param ip
	 * @return
	 */
	public List<Resfood> findHistory(String ip) {
		Jedis jedis = new Jedis("localhost", 6379);
		Set<String> foodIds = jedis.zrevrange("food:" + ip + ":foods", 0,3);
		

		jedis.close();
		List<Resfood> listFoods = find();
		List<Resfood> list = new ArrayList<Resfood>();
		for (String id : foodIds) {
			Resfood f = new Resfood();
			for (Resfood rf : listFoods) {
				if (rf.getFid() == Integer.parseInt(id)) {
					f.setFphoto(rf.getFphoto());
					break;
				}
			}
			if (f.getFphoto() != null) {
				f.setFid(Integer.parseInt(id));
				list.add(f);
			}
		}
		return list;
	}

	public int searchCount(String likeName) {
		String sql = "select count(*) c from resfood where fname like%" + likeName + "%";
		
		ResultSet resultSet;
		try {
			Connection conn = db.getConnection();
			PreparedStatement prep = conn.prepareStatement(sql);

			resultSet = prep.executeQuery();
			resultSet.next();
			return resultSet.getInt("c");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	
}
