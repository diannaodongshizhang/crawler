package com.example.crawler.test;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CrawlerGetParamTest {
	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//发出get请求创建HttpGet对象
		
		//https://android.myapp.com/myapp/detail.htm?apkName=cn.mynewclouedeu&ADTAG=mobile
		//创建URIBuilder
		URIBuilder uriBuilder =new URIBuilder("https://android.myapp.com/myapp/detail.htm");
		//设置参数
		uriBuilder.setParameter("apkName", "cn.mynewclouedeu").setParameter("ADTAG","mobile");
		
		HttpGet httpGet =new HttpGet(uriBuilder.build());
		//发起亲求，返回请求
		
		System.out.println("发起请求"+httpGet);
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
