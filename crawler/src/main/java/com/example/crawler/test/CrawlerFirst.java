package com.example.crawler.test;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CrawlerFirst {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		//创建HttpClent对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//发出get请求创建HttpGet对象
		HttpGet httpGet =new HttpGet("https://www.baidu.com");
		//发起亲求，返回请求
		CloseableHttpResponse response = httpClient.execute(httpGet);
		//判断状态码
		if(response.getStatusLine().getStatusCode() == 200) {
			//响应体
			HttpEntity httpEntity =response.getEntity();
			String content = EntityUtils.toString(httpEntity,"utf8");
			
			System.out.println(content);
		}
		
	}
}
