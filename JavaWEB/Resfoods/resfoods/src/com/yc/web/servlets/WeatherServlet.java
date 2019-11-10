package com.yc.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.yc.bean.CityInfo;
import com.yc.bean.IpInfo;
import com.yc.bean.JsonModel;
import com.yc.biz.CityInfoBiz;
import com.yc.utils.NetUtil;
import com.yc.utils.WebXmlUtils;

import redis.clients.jedis.Jedis;

@WebServlet("/weather.action")
public class WeatherServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	class Result{
		String addressInfo;
		String weatherInfo;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		JsonModel jm=new JsonModel();
		jm.setCode(1);
		try {
			String ip = request.getRemoteAddr(); 
			IpInfo info = WebXmlUtils.getCountryCityByIp(ip);
			System.out.println(info);
			String addressInfo = info.ip + "_" + info.provinceAndCity;
			Result r=new Result();
			r.addressInfo=addressInfo;
			
			Jedis jedis = new Jedis();
			String weatherInfo = jedis.get(addressInfo);
			if (weatherInfo != null && weatherInfo.length() > 0) {
				r.weatherInfo=weatherInfo;
			} else {
				CityInfo cityinfo = CityInfoBiz.getCityInfo(info.provinceAndCity);
		//	System.out.println("城市信息:\n" + cityinfo);
				List<String> list = WebXmlUtils.getWeather(1780);
//				for (String s : list) {
//					System.out.println("==" + s);
//				}
				if (list != null && list.size() > 1) {
					String weatherinfo = list.get(4);
					r.weatherInfo=weatherinfo;
				}
			}
			jm.setObj( r  );
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.writeJson(response, jm);
	}

}
