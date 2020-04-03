package com.example.crawler.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class CrawlerPostParamTest {
	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//发出get请求创建HttpGet对象
		HttpPost httpPost =new HttpPost("https://android.myapp.com/myapp/detail.htm");
		
		//声明list集合，封装表单中参数
		List<NameValuePair> params =new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("apkName", "cn.mynewclouedeu"));
		params.add(new BasicNameValuePair("ADTAG","mobile"));
		//创建表单的Entity对象
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params,"utf8"); 
		//设置表单的Entity对象到post请求
		httpPost.setEntity(formEntity);
		
		
		
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
