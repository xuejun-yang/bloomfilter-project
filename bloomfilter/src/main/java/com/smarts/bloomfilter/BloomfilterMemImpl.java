package com.smarts.bloomfilter;

import java.util.BitSet;

import com.smarts.bloomfilter.common.config.ConfigReader;
import com.smarts.bloomfilter.hash.MurmurHash;

/**
 * 去重,非线程安全
 * 
 * @author 杨雪军(Yang xue jun)<br>
 *         2014年8月15日 下午12:54:39<br>
 */
public class BloomfilterMemImpl implements IBloomfilter {

	private int max = ConfigReader.getIntValue("number.items");
	private long m; // bit占用空间值
	private int k; // hash函数个数
	private BitSet memCache;

	private BloomfilterMemImpl() {
		m = BloomfilterCalculator.getNumberBits();//算出占用内存大小
		System.out.println("共占用:"+m/(8*1024*1024)+" m");
		k = BloomfilterCalculator.getNumberHash(m);
		memCache = new BitSet(max);
	}

	private static BloomfilterMemImpl boolmfilter = new BloomfilterMemImpl();

	public static BloomfilterMemImpl getInstance() {
		return boolmfilter;
	}

	public boolean unique(String value) {
		int[] hash = getHash(value.getBytes());
		// 如果所有位都为true，则表示已存在，只要有一位为false，则表示不存在
		for (int i : hash) {
			if (!memCache.get(i)) {
				signBit(hash);
				return true;
			}
		}
		return false;
	}

	public boolean signBit(int[] hash) {
		for (Integer i : hash) {
			memCache.set(i, true);
		}
		return true;
	}

	private int[] getHash(byte[] value) {
		int[] list = new int[k];
		int hash1 = MurmurHash.hash32(value, value.length, 0);
		int hash2 = MurmurHash.hash32(value, value.length, hash1);
		for (int i = 0; i < k; i++) {
			list[i] = Math.abs((hash1 + hash2 * i) % max);
		}
		return list;
	}
}
