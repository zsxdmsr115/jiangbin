package com.yc.util;

import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

public class MyProperties extends Properties {
	
  //¼ÓÔØpropertiesÎÄ¼þ
	private static String filename="db.properties";
	private static  MyProperties myProperties;
	 private MyProperties(String ...filename1){
		  if(filename1!=null && filename1.length>1){
			  this.filename=filename1[0];
		  }
		 try {
			load(MyProperties.class.getClassLoader().getResourceAsStream(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	
	
	 public synchronized static MyProperties newsInstance(String ...fi){
		    if(myProperties==null){
		    	myProperties=new MyProperties();
		    }
		    
		    return myProperties;
	 }
}
