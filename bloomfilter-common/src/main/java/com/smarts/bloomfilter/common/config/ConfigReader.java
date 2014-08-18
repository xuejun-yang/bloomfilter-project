package com.smarts.bloomfilter.common.config;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

/**
 * 配置文件读取工具类
 * @author 杨雪军(Yang xue jun)<br>
 *2014年8月15日 上午11:25:10<br>
 */
public final class ConfigReader {

	private static Properties p;
	static{
		p=new Properties();
		try {
			p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static String getStringValue(String key){
		return p.getProperty(key);
	}
	
	public static Long getLongValue(String key){
		return Long.parseLong(getStringValue(key));
	}
	
	public static BigDecimal getBigDecimalValue(String key){
		return new BigDecimal(getStringValue(key));
	}
	
	public static Integer getIntValue(String key){
		return Integer.parseInt(getStringValue(key));
	}
	
	public static void main(String[] args) {
		BigDecimal bd = getBigDecimalValue("percent.false");
		System.out.println(bd.doubleValue());
	}
}
