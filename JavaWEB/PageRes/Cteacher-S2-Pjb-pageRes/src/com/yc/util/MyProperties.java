package com.yc.util;

import java.io.IOException;
import java.util.Properties;



public class MyProperties extends Properties {
	
  //¼ÓÔØpropertiesÎÄ¼þ
	private static String filename="jdbc.properties";
	private static  MyProperties myProperties;
	 private MyProperties(){
		
		 try {
			load(MyProperties.class.getClassLoader().getResourceAsStream(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	
	
	 public synchronized static MyProperties newsInstance(){
		    if(myProperties==null){
		    	myProperties=new MyProperties();
		    }
		    
		    return myProperties;
	 }
}
