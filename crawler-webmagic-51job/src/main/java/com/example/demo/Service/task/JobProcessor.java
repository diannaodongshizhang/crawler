package com.example.demo.Service.task;

import java.util.List;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.pojo.JobInfo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

@Component
public class JobProcessor implements PageProcessor {

	private String url ="https://search.51job.com/list/080000,000000,0000,01%252C38,9,99,java,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
	
	private Site site=Site.me()
			.setCharset("gbk")
			.setTimeOut(10000)
			.setRetrySleepTime(3000)
			.setRetryTimes(3)
			;
	@Override
	public Site getSite() {
		
		return site;
	}

	@Override
	public void process(Page page) {
		List<Selectable> list = page.getHtml().css("div#resultList div.el").nodes();
		if(list.size() == 0) {
			this.saveJobInfo(page);
		}else {
			for(Selectable selectable:list) {
				String JobInfoUrl=selectable.links().toString();
				page.addTargetRequest(JobInfoUrl);
			}
			//获取下一页的url
			String bkUrl=page.getHtml().css("div.p_in li.bk").nodes().get(1).links().toString();
			page.addTargetRequest(bkUrl);
		}
		String html=page.getHtml().toString();
	}
	
	private void saveJobInfo(Page page) {
		JobInfo jobInfo=new JobInfo();
		Html html=page.getHtml();
		
		jobInfo.setCompanyName(html.css("div.cn p.cname  a","text").toString());
		jobInfo.setCompanyAddr(Jsoup.parse(html.css("div.bmsg ").nodes().get(1).toString()).text());
		jobInfo.setCompanyInfo(Jsoup.parse(html.css("div.tmsg ").toString()).text());
		//text获取标签内内容
		jobInfo.setJobName(html.css("div.cn h1","text").toString());
		String JobAddr=Jsoup.parse(html.css("div.bmsg.inbox p.fp").nodes().get(2).toString()).text();
		JobAddr=JobAddr.substring(5, JobAddr.length());
		jobInfo.setJobAddr(JobAddr);
		jobInfo.setJobInfo(Jsoup.parse(html.css("div.job_msg").toString()).text());
		jobInfo.setUrl(page.getUrl().toString());
		
		//jobInfo.setTime(time);
		
		Integer[] salary = MathSalary.getSalary(html.css("div.cn strong", "text").toString());
        jobInfo.setSalaryMin(salary[0]);
        jobInfo.setSalaryMax(salary[1]);
        String time = Jsoup.parse(html.css("div.cn p").regex(".*发布").toString()).text();
        jobInfo.setTime(time.substring(time.length()-7,time.length()-2));
     
		page.putField("jobInfo", jobInfo);
	}
	 @Autowired
	 private SpringDataPipeline springDataPipeline;

	@Scheduled(initialDelay =1000,fixedDelay =100*100)
	public void process() {
		Spider.create(new JobProcessor())
			.addUrl(url)
			.setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
			.thread(10)
			.addPipeline(this.springDataPipeline)
			.run();
		
	}
	
}
