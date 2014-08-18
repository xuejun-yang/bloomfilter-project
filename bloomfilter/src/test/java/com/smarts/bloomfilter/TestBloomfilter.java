package com.smarts.bloomfilter;

import org.junit.Test;

public class TestBloomfilter {
	
	@Test
	public  void filter() {
		String str = "http://republicw.iteye.com/blog/1886055";
		IBloomfilter bloom = BloomfilterMemImpl.getInstance();
		boolean flag = bloom.unique(str);
		System.out.println(flag);
		flag = bloom.unique(str);
		System.out.println(flag);
	}
}
