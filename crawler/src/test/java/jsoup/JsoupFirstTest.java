package jsoup;

import java.io.File;
import java.net.URL;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class JsoupFirstTest {
	@Test
	public void testUrl() throws Exception{
		Document doc = Jsoup.parse(new URL("http://news.baidu.com/"),1000);
		
		//使用标签选择器，获取title标签内内容
		String title = doc.getElementsByTag("title").first().text();
		
		System.out.println(title);
	}
	@Test
	public void testString() throws Exception{
		String content=FileUtils.readFileToString(new File("C:\\Users\\摩尔那个点\\Desktop\\百度新闻——海量中文资讯平台.html"), "utf8");
		
		Document doc=Jsoup.parse(content);
		
		String title = doc.getElementsByTag("title").first().text();
		
		System.out.println(title);
	}
	@Test
	public void testFile() throws Exception{
		Document doc =Jsoup.parse(new File("C:\\Users\\摩尔那个点\\Desktop\\百度新闻——海量中文资讯平台.html"), "utf8");
		
		String title = doc.getElementsByTag("title").first().text();
		
		System.out.println(title);
	}
	
	@Test
	public void testDom() throws Exception{
		Document doc =Jsoup.parse(new File("C:\\Users\\摩尔那个点\\Desktop\\百度新闻——海量中文资讯平台.html"), "utf8");
		
		//Element element = doc.getElementById("cl");
		
		//Element element = doc.getElementsByTag("span").first();
		
		//Element element = doc.getElementsByClass("en").first();
		
		Element element = doc.getElementsByAttribute("abc").first();
		
		System.out.println(element.text());
	}
	@Test
	public void testData() throws Exception{
		Document doc =Jsoup.parse(new File("C:\\Users\\摩尔那个点\\Desktop\\百度新闻——海量中文资讯平台.html"), "utf8");
		
		Element element = doc.getElementById("city_name");
		
		String str = "";
		
		str =element.id();
		
		str =element.className();
//		Set<String> classSet =element.classNames();
//		for(String s : classSet) {
//			System.out.println(s);
//		}
		//从元素中获取属性的值
		str=element.attr("id");
		
		Attributes attributes=element.attributes();
		System.out.println(attributes.toString());
		
		str =element.text();
		System.out.println(str);
	}
	@Test
	public void testSelector() throws Exception{
		Document doc =Jsoup.parse(new File("C:\\Users\\摩尔那个点\\Desktop\\百度新闻——海量中文资讯平台.html"), "utf8");
		
//		Elements elements=doc.select("span");
//		for(Element element:elements) {
//			System.out.println(elements.text());
//		}
		
		//Element e=doc.select("#city_name").first();
		
		//Element e=doc.select(".en").first();
		
		Element e=doc.select("[abc]").first();
		
		Elements elements=doc.select("[abc=123]");
		for(Element element:elements) {
			System.out.println(elements.text());
		}
		System.out.println(e.text());
	}
	@Test
	public void testSelector2() throws Exception{
		Document doc =Jsoup.parse(new File("C:\\Users\\摩尔那个点\\Desktop\\百度新闻——海量中文资讯平台.html"), "utf8");
		
		//Element e=doc.select("h3#city_bj").first();
		//Element e=doc.select("span[abc]").first();
		//Element e=doc.select("span[abc].s_name").first();
		//基本于jquery一致
	}
	
}
