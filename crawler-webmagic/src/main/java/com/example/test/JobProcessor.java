package com.example.test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.xsoup.xevaluator.ElementOperator.Regex;

public class JobProcessor implements PageProcessor{

	
	

	@Override
	public void process(Page page) {
		//解析返回的数据page,并且把结果放入ResultItems中
		page.putField("div",page.getHtml().css("div.J_modal_box").all());

		page.putField("div2",page.getHtml().xpath("//div[@id=J_report]/p"));
		
		page.putField("div3", page.getHtml().css("div.phone-code-box label input").all());
		
		page.putField("div4", page.getHtml().css("div.phone-code-box label input").regex(".*手机.*").all());
		
		//结果处理
		page.putField("div5", page.getHtml().css("div.phone-code-box label input").regex(".*手机.*").get());
		page.putField("div6", page.getHtml().css("div.phone-code-box label input").regex(".*手机.*").toString());
		
		page.putField("div7", page.getHtml().css("li.icp_item a.icp_dil").regex(".*手机.*").get());
		//获取链接
		page.addTargetRequests(page.getHtml().css("li.icp_item a.icp_dil").links().all());
		page.putField("url",page.getHtml().css("title").all());
	}
	
	private Site site=Site.me()
			.setCharset("utf-8")
			.setTimeOut(10000)
			.setSleepTime(3)
			.setRetrySleepTime(3000)
			;
	@Override
	public Site getSite() {
		return site;
	}
	public static void main(String[] args) {
		Spider.create(new JobProcessor())
			.addUrl("https://mini.eastday.com/a/200322133758947.html?qid=02160")
			.addPipeline(null)
			.thread(5)
			.run();
		
	}

}
