package com.example.crawler.test;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class HttpClientPoolTest {
	
	public static void main(String[] args) {
		//创建连接池管理器
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		
		//设置最大连接数
		cm.setMaxTotal(100);
		//设置每个主机的最大连接数
		cm.setDefaultMaxPerRoute(10);
		//使用连接池管理器发起请求
		doGet(cm);
		doGet(cm);
	}

	private static void doGet(PoolingHttpClientConnectionManager cm) {
		//从连接池获取
		CloseableHttpClient httpClient=HttpClients.custom().setConnectionManager(cm).build();
		
		HttpGet httpGet=new HttpGet("http://www.baidu.com");
		
		CloseableHttpResponse response = null;
		
		try {
				response = httpClient.execute(httpGet);
				if(response.getStatusLine().getStatusCode() == 200) {
					//响应体
					HttpEntity httpEntity =response.getEntity();
					String content = EntityUtils.toString(httpEntity,"utf8");
					
					System.out.println(content.length());
				}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
		}finally {
			if(response != null) {
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
	}
	
	
	
}
