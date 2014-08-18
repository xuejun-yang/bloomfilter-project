package com.smarts.bloomfilter.client;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.smarts.bloomfilter.common.thrift.FilterResult;

public class TestClient {

	SignleThreadClient client;
	
	@Before
	public void init(){
		client = SignleThreadClient.getInsClient();
	}
	
	@Test
	public void testSignle(){
		String key="http://thrift.apache.org/tutorial/java";
		System.out.println(client.filterSingle(key));
		System.out.println("<----------------------------------------------------->");
		System.out.println(client.filterSingle(key));
	}
	
	@Test
	public void testList(){
		List<FilterResult> keys = new ArrayList<FilterResult>();
		keys.add(new FilterResult("http://thrift.apache.org/tutorial/java", false));
		keys.add(new FilterResult("http://thrift.apache.org/developers", false));
		List<FilterResult> result = client.filterList(keys);
		for(FilterResult key:result){
			System.out.println(key.getKey()+" : "+key.isResult());
		}
		System.out.println("<----------------------------------------------------->");
		result = client.filterList(keys);
		for(FilterResult key:result){
			System.out.println(key.getKey()+" : "+key.isResult());
		}
	}
}
