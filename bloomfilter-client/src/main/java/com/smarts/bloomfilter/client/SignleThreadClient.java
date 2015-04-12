package com.smarts.bloomfilter.client;

import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.smarts.bloomfilter.common.config.ConfigReader;
import com.smarts.bloomfilter.common.thrift.Filter;
import com.smarts.bloomfilter.common.thrift.FilterResult;

/**
 * 去重通讯类（客户端）
 * 
 * @author 杨雪军(Yang xue jun)<br>
 *         2014年8月18日 下午2:58:46<br>
 */
public class SignleThreadClient {
	private Filter.Client client;
	private TTransport transport;
	private TProtocol protocol;

	private SignleThreadClient() {
		transport = new TSocket(ConfigReader.getStringValue("server.host"),
				ConfigReader.getIntValue("server.port"));
		try {
			transport.open();
			protocol = new TBinaryProtocol(transport);
			client = new Filter.Client(protocol);
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}

	private void reConnection() {
		transport = new TSocket(ConfigReader.getStringValue("server.host"),
				ConfigReader.getIntValue("server.port"));
		try {
			transport.open();
			protocol = new TBinaryProtocol(transport);
			client = new Filter.Client(protocol);
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}

	private static SignleThreadClient simpleClient = new SignleThreadClient();

	public static SignleThreadClient getInsClient() {
		return simpleClient;
	}

	/**
	 * 单个去重
	 * @param key
	 * @return
	 */
	public boolean filterSingle(String key) {
		try {
			return client.filterSingle(key);
		} catch (TException e) {
			e.printStackTrace();
			if (client != null) {
				client = null;
			}
			if (protocol != null) {
				protocol = null;
			}
			if (transport != null) {
				if (transport.isOpen()) {
					transport.close();
				} else {
					transport = null;
				}
			}
			reConnection();
		}
		return false;
	}

	/**
	 * 批量去重
	 * @param keys
	 * @return
	 */
	public List<FilterResult> filterList(List<FilterResult> keys) {

		try {
			return client.filterList(keys);
		} catch (TException e) {
			e.printStackTrace();
			if (client != null) {
				client = null;
			}
			if (protocol != null) {
				protocol = null;
			}
			if (transport != null) {
				if (transport.isOpen()) {
					transport.close();
				} else {
					transport = null;
				}
			}
			reConnection();
		}
		return keys;
	}
}
