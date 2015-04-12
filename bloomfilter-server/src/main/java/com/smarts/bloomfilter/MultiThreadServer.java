package com.smarts.bloomfilter;

import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

import com.smarts.bloomfilter.common.config.ConfigReader;
import com.smarts.bloomfilter.common.thrift.Filter;
import com.smarts.bloomfilter.thrift.FilterServiceImpl;

/**
 * 启动服务，多线程方式实现
 * 
 * @author 杨雪军(Yang xue jun)<br>
 *         2014年8月18日 下午2:41:07<br>
 */
public class MultiThreadServer {

	public static void main(String[] params) {
		try {
			TServerTransport serverSocket = new TServerSocket(
					ConfigReader.getIntValue("server.port"));
			Args args = new Args(serverSocket);
			TProcessor processor = new Filter.Processor<Filter.Iface>(
					new FilterServiceImpl());
			args.processor(processor);
			TServer server = new TThreadPoolServer(args);
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}

	}
}
