package com.smarts.bloomfilter;

import com.smarts.bloomfilter.common.config.ConfigReader;

/**
 * bloomfilter 计算器，根据元素个数和误判率来计算所需空间大小和hash个数
 * @author 杨雪军(Yang xue jun)<br>
 *2014年8月15日 上午10:45:02<br>
 */
public final class BloomfilterCalculator {

	private static long max=ConfigReader.getLongValue("number.items");
	private static double p = ConfigReader.getBigDecimalValue("percent.false").doubleValue();
	/**
	 * 获取bit所需的空间值
	 * @return
	 */
	public static long getNumberBits(){
		double m = Math.round(Math.ceil(max*Math.log(p))/Math.log(1.0/Math.pow(2.0, Math.log(2.0))))+0.5;
		return (long)m;
	}
	/**
	 * 获取所需的hash值的个数
	 * @param m bit所需的空间值
	 * @return
	 */
	public static int getNumberHash(long m){	
		return (int)Math.round(Math.log(2.0)*m/max);
	}
	public static void main(String[] args) {
		long m = getNumberBits();
		System.out.println(m);
		int k = getNumberHash(m);
		System.out.println(k);
	}
}
