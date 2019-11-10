package com.yc.biz;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.yc.bean.CityInfo;

//������Ϣ��ҵ��
public class CityInfoBiz {

	public static Map<String, List<CityInfo>> cityInfoMap = new HashMap<String, List<CityInfo>>();

	// ��̬��
	static {
		try {
			cityInfoMap = getAllCityInfo();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���������������ȡ���е����� ����ʡ������ ����ʡ������ ����ر����������
	 * 
	 * @param addressInfo
	 * @return
	 */
	public static CityInfo getCityInfo(String addressInfo) {
		// 1.ȡ��ʡ������Ϣ
		String provinceName = null;
		String cityName = null;
		Pattern p = Pattern.compile("(.+)ʡ(.+)��");
		System.out.println(addressInfo);
		Matcher m = p.matcher(addressInfo);
		if (m.find()) {
			provinceName = m.group(1); // ȡ��һ������Ҫ���ֵ
			cityName = m.group(2);
			return findCityInfoByCityName(provinceName, cityName);
		}
		// TODO:����...
//		p = Pattern.compile("(.+)��(.+)");
//		m = p.matcher(addressInfo);
//		if( m.find()){
//			provinceName = m.group(1); // ȡ��һ������Ҫ���ֵ
//			cityName = m.group(2);
//			return findCityInfoByCityName(provinceName, cityName);
//		}
		return null;
	}

	public static void main(String[] args) {
		 System.out.println(cityInfoMap);
		System.out.println( findCityInfoByCityName("����","����") );
	System.out.println(getCityInfo("����ʡ������"));
		//System.out.println(getCityInfo("����׳��������������"));
	}

	/**
	 * ȡ���еĳ��е���Ϣ,��װ�� ʡ -> List<CityInfo> excel ����...
	 * 
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static Map<String, List<CityInfo>> getAllCityInfo() throws FileNotFoundException, IOException {
		List<CityInfo> list = getAllCity();
		for (CityInfo ci : list) {
			List<CityInfo> l = new ArrayList<CityInfo>();
			if (cityInfoMap.containsKey(ci.getZone())) {
				l = cityInfoMap.get(ci.getZone());
			} else {
				l = new ArrayList<CityInfo>();
			}
			if (ci.getArea().equals(ci.getCountryOrAreaName())) {
				l.add(ci);
			}
			cityInfoMap.put(ci.getZone(), l);
		}
		return cityInfoMap;
	}

	private static List<CityInfo> getAllCity() throws FileNotFoundException, IOException {
		// ��Ŀ��·�� \bin\AbountCity.xls
		//String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "AboutCity.xls";
		//TODO: ����Ի��
		//String path="C:\\tomcat\\apache-tomcat-7.0.47\\webapps\\yc3637web1\\WEB-INF\\classes\\AboutCity.xls";
		//POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(path));
		POIFSFileSystem fs = new POIFSFileSystem( CityInfoBiz.class.getClassLoader().getResourceAsStream("AboutCity.xls")  );
		// �õ�Excel����������
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		// �õ�Excel���������
		HSSFSheet sheet = wb.getSheet("About_City_View");

		// ��ȡ��Ч������
		int rowcount = sheet.getLastRowNum();
		List<CityInfo> list = new ArrayList<CityInfo>();
		for (int i = 1; i < rowcount; i++) {
			CityInfo ci = new CityInfo();
			// �õ�Excel���������
			HSSFRow row = sheet.getRow(i);
			// ��Ԫ����
			int cellNum = row.getLastCellNum();
			for (int j = 0; j < cellNum; j++) {
				// �õ�Excel������ָ���еĵ�Ԫ��
				HSSFCell cell = row.getCell((short) j);
				if (cell != null) {
					if (j == 0) {
						ci.setTid((int) cell.getNumericCellValue());
					} else if (j == 1 && cell.getStringCellValue() != null) {
						ci.setZone(cell.getStringCellValue());
					} else if (j == 2 && cell.getStringCellValue() != null) {
						ci.setArea(cell.getStringCellValue());
					} else if (j == 3 && cell.getStringCellValue() != null) {
						ci.setAreaEn(cell.getStringCellValue());
					} else if (j == 4 && cell.getStringCellValue() != null) {
						ci.setCountryOrAreaName(cell.getStringCellValue());
					} else if (j == 5 && cell.getStringCellValue() != null) {
						ci.setCountryName(cell.getStringCellValue());
					} else if (j == 6 && cell.getStringCellValue() != null) {
						ci.setCountryNameEn(cell.getStringCellValue());
					} else if (j == 7 && cell.getStringCellValue() != null) {
						ci.setAboutCity(cell.getStringCellValue());
					} else if (j == 8 && cell.getStringCellValue() != null) {
						ci.setClimateBackground(cell.getStringCellValue());
					}
				}
			}
			list.add(ci);
		}
		return list;
	}

	/*
	 * ����ʡ�������ʡ���е���, ��cityInfoMap�в�
	 */
	public static List<CityInfo> findCityListByProName(String provinceName) {
		return cityInfoMap.get(provinceName);
	}

	/**
	 * ���������õ��е���Ϣ ��cityInfoMap�в�
	 */
	public static CityInfo findCityInfoByCityName(String provinceName, String cityName) {
		List<CityInfo> list = findCityListByProName(provinceName);
		if (list != null) {
			for (CityInfo ci : list) {
				if (ci.getCountryOrAreaName().equals(cityName)) {
					return ci;
				}
				if (ci.getZone().equals(cityName)) {
					return ci;
				}
			}
		}
		return null;
	}

}
