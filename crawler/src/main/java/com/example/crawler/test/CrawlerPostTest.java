package com.example.crawler.test;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CrawlerPostTest {
	public static void main(String[] args) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//发出get请求创建HttpGet对象
		HttpPost httpPost =new HttpPost("https://www.baidu.com");
		//发起亲求，返回请求
		CloseableHttpResponse response = null;
		
		try {
				response = httpClient.execute(httpPost);
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
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				httpClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}
	
}
