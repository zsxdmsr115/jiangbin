package com.yc.examin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class BaseServelet<T> extends HttpServlet{
   protected String op;
    @Override
    	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    		doPost(req, resp);
    	}
       @Override
    	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
    	  arg0.setCharacterEncoding("UTF-8");
    	  op = arg0.getParameter("op");
    	  super.service(arg0, arg1);
       }
       protected void outJson(Object obj, HttpServletResponse response) throws IOException {
   		Gson gson = new Gson();
   		String json = gson.toJson(obj);
   		response.setCharacterEncoding("UTF-8");
   		response.setContentType("application/json");
   		PrintWriter out = response.getWriter();
   		out.println(json);
   		out.close();

   	}
       protected T parseRequest(HttpServletRequest request, Class<T> c) {

   		Map<String, String[]> map = request.getParameterMap();
   		Set<String> methodNames = getMethodName(map.keySet());
   		Method[] ms = c.getMethods();
   		T t = null;
   		try {
   			t = (T) c.newInstance();
   			for (Method method : ms) {
   				for(String mn:methodNames){
   					if(method.getName().equals(mn)){
   					  // setXxx 	截取Xxx
   					 String keyname =  mn.substring(3, 4).toLowerCase()+mn.substring(4);
   					//获取函数的参数类型名 setXxx函数的参数
   					 String typeName = method.getParameterTypes()[0].getName();
   					 String[] value = map.get(keyname);
   					 if("java.lang.Integer".equals(typeName)||"int".equals(typeName)){
   						 method.invoke(typeName, Integer.parseInt(value[0]));
   					 }else if( "java.lang.Double".equals(typeName)  ||  "double".equals(typeName) ){
   							method.invoke(t , Double.parseDouble(value[0] ));
   						}else if( "java.lang.Float".equals(typeName)  ||  "float".equals(typeName) ){
   							method.invoke(t , Float.parseFloat(value[0] ));
   						}else if( "java.lang.Long".equals(typeName)  ||  "long".equals(typeName) ){
   							method.invoke(t , Long.parseLong(value[0]));
   						}else {
   							method.invoke(t , value[0].toString() );
   						}
   						break;
   					}
   				}
   			}
   		} catch (Exception e) {
   			e.printStackTrace();
   		}
   		return t;
   	}
       private Set<String> getMethodName(Set<String> keys) {
   		Set<String> result = new HashSet<String>();
   		for (String key : keys) {
   			/**
   			 * 将input name属性值拼接 setXxx函数名， 例如 username 拼接成setUsername
   			 */
   			String newName = key.substring(0, 1).toUpperCase() + key.substring(1);
   			result.add("set" + newName);
   		}
   		return result;
   	}
 
}
