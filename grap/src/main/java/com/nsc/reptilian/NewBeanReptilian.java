package com.nsc.reptilian;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.xalan.templates.ElemText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.nsc.pojo.BusinessNews;
import com.nsc.pojo.NewBean;
import com.nsc.service.BusinessNewsServices;
import com.nsc.service.impl.NewBeanServiceImpl;

import net.sourceforge.htmlunit.corejs.javascript.json.JsonParser.ParseException;

/**
 * 集团要闻
 * 
 * @author xyg url：http://www.ceec.net.cn/col/col11016/index.html
 */
@Component
public class NewBeanReptilian {
	@Autowired
	private NewBeanServiceImpl service; //集团要闻
	
	@Autowired 
	private BusinessNewsServices businessNewsServices;//国际业务部门新闻

	final WebClient client = new WebClient(BrowserVersion.CHROME); // 创建一个webclient

	@SuppressWarnings("unchecked")
	public List<NewBean> CrawlingNew() throws ParseException {
		String url = "http://www.ceec.net.cn/col/col11016/index.html";
		// 登录
		client.getOptions().setJavaScriptEnabled(false);
		client.getOptions().setCssEnabled(false);
		client.setAjaxController(new NicelyResynchronizingAjaxController());// 设置支持AJAX
		client.getOptions().setThrowExceptionOnScriptError(false);
		client.getOptions().setTimeout(10000);// 设置“浏览器”的请求超时时间
		client.setJavaScriptTimeout(10000);// 设置JS执行的超时时间
		client.waitForBackgroundJavaScript(10000);// 设置JS后台等待执行时间
		List<NewBean> list = new ArrayList<NewBean>();
		try {
			HtmlPage page = (HtmlPage) client.getPage(url);

			List<HtmlElement> tableList = (List<HtmlElement>) page.getByXPath("//div[contains(@id,'84325')]/div/table");
			for (int i = 0; i < 7; i++) {
				NewBean newsBean = new NewBean();
				HtmlElement tableElement = tableList.get(i);
				DomNodeList<DomNode> table = tableElement.getChildNodes();
				DomNode tbody = table.get(1);
				DomNodeList<DomNode> trs = tbody.getChildNodes();
				DomNode tr = trs.get(2);
				DomNodeList<DomNode> tds = tr.getChildNodes();
				DomNode td = tds.get(3);
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				newsBean.setANNOUNCE_ID(uuid);
				newsBean.setANNOUNCE_TITLE(td.asText());
				HtmlElement a = ((DomElement) td).getElementsByTagName("a").get(0);
				HtmlPage page2 = a.click();
				List<DomElement> divList = (List<DomElement>) page2
						.getByXPath("//td[contains(@class,'border1')]/table/tbody");
				if (divList.size() > 0) {// 企业要闻
					DomNodeList<DomNode> trlist = divList.get(0).getChildNodes();
					DomElement trDomElement;
					if (trlist.size() == 16) {
						trDomElement = (DomElement) divList.get(0)
								.getByXPath("//td[contains(@class,'border1')]/table/tbody/tr[6]").get(0);
					} else {
						trDomElement = (DomElement) divList.get(0)
								.getByXPath("//td[contains(@class,'border1')]/table/tbody/tr[5]").get(0);
					}
					DomNodeList<DomNode> tdlist = trDomElement.getChildNodes();
					DomNode tddDomNode = tdlist.get(0);
					String strtext = tddDomNode.asText();
					String source = "";
					if (strtext.length() > 0) {
						if (strtext.contains("作者")) {
							// 截取字符串“作者”之前的字符串
							String str1 = strtext.substring(0, strtext.indexOf("作者"));
							// 截取”：“之后的字符串
							source = str1.substring(str1.indexOf('：') + 1);
						}
						// 截取字符串"日期"之前的字符串
						String str2 = strtext.substring(0, strtext.indexOf("日期"));
						String author1 = str2.substring(str2.indexOf('：') + 1);
						String author = author1.substring(author1.indexOf('：') + 1);
						String str3 = strtext.substring(0, strtext.indexOf("字号"));
						String datenow1 = str3.substring(str3.indexOf('：') + 1);
						String datenow2 = datenow1.substring(datenow1.indexOf('：') + 1);
						String datenow3 = datenow2.substring(datenow2.indexOf('：') + 1);
						java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(datenow3);
						Date date2 = new Date(date.getTime());
						newsBean.setANNOUNCE_SOURCE(source);
						newsBean.setANNOUNCE_AUTHOR(author);
						newsBean.setANNOUNCE_DATE(date2);
						List<HtmlElement> contentlist = (List<HtmlElement>) page2
								.getByXPath("//div[contains(@id,'zoom')]");
						String content = contentlist.get(0).asXml();
						newsBean.setANNOUNCE_CONTENT(content);
					}
				} else { // 新华网
							// 获取时间
					List<DomElement> spanList = (List<DomElement>) page2
							.getByXPath("//span[contains(@class,'h-time')]");
					// 获取来源
					List<DomElement> emList = (List<DomElement>) page2.getByXPath("//em[contains(@id,'source')]");
					if (spanList.size() > 0) {
						String dateguang = spanList.get(0).asText();
						String author = emList.get(0).asText();
						newsBean.setANNOUNCE_DATE(Date.valueOf(dateguang));
						newsBean.setANNOUNCE_AUTHOR(author);
						List<DomElement> divcontent = (List<DomElement>) page2
								.getByXPath("//div[contains(@id,'content')]");
						if (divcontent.size() > 0) {
							String content2 = divcontent.get(0).asXml();
							newsBean.setANNOUNCE_CONTENT(content2);
						} else {
							List<DomElement> divcontent1 = (List<DomElement>) page2
									.getByXPath("//div[contains(@id,'p-detail')]");
							String content3 = divcontent1.get(0).asXml();
							newsBean.setANNOUNCE_CONTENT(content3);
						}

					}
				}
				if (newsBean.getANNOUNCE_CONTENT() == null) {
					System.out.println("未抓取所属链接的内容：" + newsBean.getANNOUNCE_TITLE());
				}
				String title = newsBean.getANNOUNCE_TITLE();
				List<NewBean> mlist = service.findNewBean(title);

				if (mlist.size() == 0 && newsBean.getANNOUNCE_CONTENT() != null) {
					service.insertNewBean(newsBean);
					System.out.println("新抓取要闻内容：" + newsBean.getANNOUNCE_TITLE());
				} else {
					System.out.println("已存在要闻：" + newsBean.getANNOUNCE_TITLE());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 国际业务部新闻
	 * 
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public List<NewBean> CrawlingNewTO() throws ParseException {
		String url = "http://www.ceec.net.cn/col/col25932/index.html";
		// 登录
		client.getOptions().setJavaScriptEnabled(false);
		client.getOptions().setCssEnabled(false);
		client.setAjaxController(new NicelyResynchronizingAjaxController());// 设置支持AJAX
		client.getOptions().setThrowExceptionOnScriptError(false);
		client.getOptions().setTimeout(10000);// 设置“浏览器”的请求超时时间
		client.setJavaScriptTimeout(10000);// 设置JS执行的超时时间
		client.waitForBackgroundJavaScript(10000);// 设置JS后台等待执行时间
		List<NewBean> list = new ArrayList<NewBean>();
		try {
			HtmlPage page = (HtmlPage) client.getPage(url);
			BusinessNews newsBean = new BusinessNews();
			List<HtmlElement> tableList = (List<HtmlElement>) page.getByXPath("//div[contains(@id,'243473')]/div/table");
			for (int i = 0; i < 7; i++) {
				HtmlElement tableElement = tableList.get(i);
				DomNodeList<DomNode> table = tableElement.getChildNodes();
				DomNode tbody = table.get(1);
				DomNodeList<DomNode> trs = tbody.getChildNodes();
				DomNode tr = trs.get(2);
				DomNodeList<DomNode> tds = tr.getChildNodes();
				DomNode td = tds.get(3);
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				newsBean.setNewsId(uuid); //id
				newsBean.setNewsTitle(td.asText());//标题
				HtmlElement a = ((DomElement) td).getElementsByTagName("a").get(0);
				HtmlPage page2 = a.click();
				List<DomElement> divList = (List<DomElement>) page2
						.getByXPath("//td[contains(@class,'border1')]/table/tbody");
				if (divList.size() > 0) {// 企业要闻
					DomNodeList<DomNode> trlist = divList.get(0).getChildNodes();
					DomElement trDomElement;
					if (trlist.size() == 6) {
						trDomElement = (DomElement) divList.get(0)
								.getByXPath("//td[contains(@class,'border1')]/table/tbody/tr[3]").get(0);
					} else {
						trDomElement = (DomElement) divList.get(0)
								.getByXPath("//td[contains(@class,'border1')]/table/tbody/tr[4]/td/table/tbody/tr/td[2]").get(0);
					}
					
					String strtext = trDomElement.asText();
					String source = "";
					if (strtext.length() > 0) {
						if (strtext.contains("作者")) {
							// 截取字符串“作者”之前的字符串
							String str1 = strtext.substring(0, strtext.indexOf("作者"));
							// 截取”：“之后的字符串
							source = str1.substring(str1.indexOf('：') + 1);
						}
						// 截取字符串"日期"之前的字符串
						String str2 = strtext.substring(0, strtext.indexOf("日期"));
						String author1 = str2.substring(str2.indexOf('：') + 1);
						String author = author1.substring(author1.indexOf('：') + 1);
						String str3 = strtext.substring(0, strtext.indexOf("字号"));
						String datenow1 = str3.substring(str3.indexOf('：') + 1);
						String datenow2 = datenow1.substring(datenow1.indexOf('：') + 1);
						String datenow3 = datenow2.substring(datenow2.indexOf('：') + 1);
						java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(datenow3);
						Date date2 = new Date(date.getTime());
						newsBean.setNewsSource(source);
						newsBean.setNewsAuthor(author);
						newsBean.setNewsDate(date2);
						List<HtmlElement> contentlist = (List<HtmlElement>) page2
								.getByXPath("//div[contains(@id,'zoom')]");
						String content = contentlist.get(0).asXml();
						newsBean.setNewsContent(content);
					}
				} else { // 新华网
							// 获取时间
					List<DomElement> spanList = (List<DomElement>) page2
							.getByXPath("//span[contains(@class,'h-time')]");
					// 获取来源
					List<DomElement> emList = (List<DomElement>) page2.getByXPath("//em[contains(@id,'source')]");
					if (spanList.size() > 0) {
						String dateguang = spanList.get(0).asText();
						String author = emList.get(0).asText();
						java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateguang);
						newsBean.setNewsDate(date);
						newsBean.setNewsAuthor(author);
						List<DomElement> divcontent = (List<DomElement>) page2
								.getByXPath("//div[contains(@id,'content')]");
						if (divcontent.size() > 0) {
							String content2 = divcontent.get(0).asXml();
							newsBean.setNewsContent(content2);
						} else {
							List<DomElement> divcontent1 = (List<DomElement>) page2
									.getByXPath("//div[contains(@id,'p-detail')]");
							String content3 = divcontent1.get(0).asXml();
							newsBean.setNewsContent(content3);
						}

					}
				}
				if (newsBean.getNewsContent() == null) {
					System.out.println("未抓取所属链接的内容：" + newsBean.getNewsTitle());
				}
				String title = newsBean.getNewsTitle();
				List<BusinessNews> mlist = businessNewsServices.findBusinessNewsInfo(title);

				if (mlist.size() == 0 && newsBean.getNewsContent() != null) {
					businessNewsServices.insertBusinessNewsInfo(newsBean);
					System.out.println("新抓取要闻内容：" + newsBean.getNewsTitle());
				} else {
					System.out.println("已存在要闻：" + newsBean.getNewsTitle());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	public static void main(String[] args) throws ParseException {
		NewBeanReptilian newBeanReptilian = new NewBeanReptilian();
		newBeanReptilian.CrawlingNewTO();
	}
}
