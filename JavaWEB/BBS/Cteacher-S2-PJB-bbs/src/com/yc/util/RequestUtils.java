package com.yc.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {
 public <T> T parseRequest(HttpServletRequest request,Class<T> c){
	 
	  try {
		  request.setCharacterEncoding("UTF-8");
		
		  T t = c.newInstance();
		      List<Method> setMethod = getAllSetMethod(c);
		      Map<String, RequestBean> map = parseRequest(request);
		   
		    for (Method method : setMethod) {
				//获取函数名
		    	String setMathodName = method.getName();
		    	RequestBean rb = map.get(setMathodName);
		    	
		    	if(rb==null){
		    		continue;
		    	}
		    	String ss =	((String[])rb.getObj())[0];
		      String methodName = 	request.getMethod();
		          
		    	  // ss = new String( ss.getBytes("ISO-8859-1"),"UTF-8");
		    	   
		      
		       
		    	
		    	//获取函数参数的类型
		    	String paramtypeName = method.getParameterTypes()[0].getName();
		    	 if("int".equals(paramtypeName)|| "java.lang.Integer".equals(paramtypeName)){
		    		 method.invoke(t, Integer.parseInt(ss));
		    	 }else if("double".equals(paramtypeName)|| "java.lang.Double".equals(paramtypeName)){
		    		 method.invoke(t, Double.parseDouble(ss));
		    	 }else if("java.lang.String".equals(paramtypeName)){
		    		 method.invoke(t, ss);
		    	 }else if("long".equals(paramtypeName)||"java.lang.Long".equals(paramtypeName)){
		    		 method.invoke(t, Long.parseLong(ss));
		    	 }else{
		    		method.invoke(t, rb.getObj()); 
		    	 }
			}
		    return t;
	} catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	}
	 
	
 }
 /**
  *  map key 方法
  */
 public Map<String,RequestBean> parseRequest(HttpServletRequest request){
	
	   Enumeration<String> parameterNames = request.getParameterNames();
	   Map<String,RequestBean> map = new HashMap<String, RequestBean>();
	   while(parameterNames.hasMoreElements()){
		   RequestBean rb = new RequestBean();
		  String pname =  parameterNames.nextElement();
		   rb.setParamName( pname);
		   rb.setMethodName(getSetMethodName(pname));
		   rb.setObj(request.getParameterValues(pname));//valu值
		   map.put(getSetMethodName(pname), rb);
	   }
	   return map;
 }
 /**
  * 拼接set函数
  * @param pname
  * @return
  */
 public String   getSetMethodName(String pname){ 
	
	   return "set"+  pname.substring(0, 1).toUpperCase()+pname.substring(1);
 }
  public <T> List<Method> getAllSetMethod(Class<T> c){
	   List<Method> list = new ArrayList<Method>();
	    Method[] methods = c.getMethods();
	    for(int i=0;i<methods.length;i++){
	    	if(methods[i].getName().startsWith("set")){
	    		list.add(methods[i]);
	    		
	    	}
	    }
	   return list; 
  }
 class RequestBean{
	  private Object obj;
	  private String paramName;
	  private String methodName;
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	  
  }
}
