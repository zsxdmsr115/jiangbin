package com.yc.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class SAXReader extends DefaultHandler {
	private String qName;
	private DBBean db;
	private static  SAXReader saxReader;
   private List<DBBean> list;
   public static void main(String[] args) {
	   List<DBBean> list2 = SAXReader.newinstance();
	  String driver = list2.get(1).getDriver();
	  System.out.println(driver);
}
   private SAXReader() throws IOException, SAXException, ParserConfigurationException {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();

		XMLReader xmlReader = parser.getXMLReader();
		
		xmlReader.setContentHandler(this);
	
		xmlReader.parse(SAXReader.class.getClassLoader().getResource("config.xml").getPath());
		
		
	}
   public synchronized static List<DBBean>  newinstance(){
	   if(saxReader==null){
		   try {
			saxReader=new SAXReader();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} 
	   }
	   return saxReader.getList();
   }
   @Override
public void startDocument() throws SAXException {
	   list = new ArrayList<>();
}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		this.qName = qName;
		
		if ("db".equals(qName)) {
			db = new DBBean();
			String id = attributes.getValue("id");
			db.setId(id);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.println(new String(ch,start,length));
		if ("driver".equals(qName)) {
			System.out.println(new String(ch,start,length));
          db.setDriver(new String(ch,start,length));  
		}else if("url".equals(qName)){
			db.setUrl(new String(ch,start,length));
		}else if("user".equals(qName)){
			db.setUser(new String(ch,start,length));
		}else if("password".equals(qName)){
			db.setPassword(new String(ch,start,length));
		}
		
	qName=null;
	
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//System.out.println(qName);
		if("db".equals(qName)){
			//System.out.println("½øÀ´");
			//System.out.println(db.getUrl());
			list.add(db);
			this.qName=null;
		}
		
	}
	public List<DBBean> getList(){
		return list;
	}
	public DBBean getDb() {
		return db;
	}
    
}
