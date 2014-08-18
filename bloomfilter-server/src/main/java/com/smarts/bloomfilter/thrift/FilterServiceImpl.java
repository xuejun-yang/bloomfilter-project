package com.smarts.bloomfilter.thrift;

import java.util.List;

import org.apache.thrift.TException;

import com.smarts.bloomfilter.BloomfilterMemImpl;
import com.smarts.bloomfilter.IBloomfilter;
import com.smarts.bloomfilter.common.thrift.Filter;
import com.smarts.bloomfilter.common.thrift.FilterResult;
/**
 * 去重服务通讯类（server端）
 * @author 杨雪军(Yang xue jun)<br>
 *2014年8月18日 下午2:12:04<br>
 */
public class FilterServiceImpl implements Filter.Iface{

	private IBloomfilter filter = BloomfilterMemImpl.getInstance();
	
	@Override
	public boolean filterSingle(String key) throws TException {
		
		return filter.unique(key);
	}

	@Override
	public List<FilterResult> filterList(List<FilterResult> keys)
			throws TException {
		for(FilterResult key:keys){
			key.setResult(filter.unique(key.getKey()));
		}
		return keys;
	}

}
