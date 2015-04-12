package com.smarts.bloomfilter;


/**
 * 
 * @author 杨雪军(Yang xue jun)<br>
 *2014年8月15日 下午1:25:15<br>
 */
public interface IBloomfilter {

	/**
	 * 判断是否唯一
	 * @param hash   
	 * @return true表示不能存在，false表示存在
	 */
	public boolean unique(String value);
	
	/**
	 * 标记所有标志位
	 * @param hash
	 * @return
	 */
	public boolean signBit(int[] hash);
}
