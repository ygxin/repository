package com.nsc.reptilian;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;
import com.gargoylesoftware.htmlunit.html.HtmlTableDataCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.nsc.pojo.DataBean;
import com.nsc.service.DataBeanService;

/**
 * 抓取世行亚行数据信息 世界银行url： 亚洲银行url：
 * 
 * @author xyg
 *
 */
@Component
public class DataBeanReTilian {

	@Autowired
	private DataBeanService service;

	final WebClient client = new WebClient(BrowserVersion.CHROME); // 创建一个webclient
	
	/**
	 * 亚洲银行抓取
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void analysisPage() throws Exception {
		String url = "https://www.adb.org/projects/search/sectors/ene/status/approved";
		// 登录
		client.getOptions().setJavaScriptEnabled(false);
		client.getOptions().setCssEnabled(false);
		client.setAjaxController(new NicelyResynchronizingAjaxController());// 设置支持AJAX
		client.getOptions().setThrowExceptionOnScriptError(false);
		client.getOptions().setTimeout(10000);// 设置“浏览器”的请求超时时间
		client.setJavaScriptTimeout(10000);// 设置JS执行的超时时间
		client.waitForBackgroundJavaScript(10000);// 设置JS后台等待执行时间
		HtmlPage page = (HtmlPage) client.getPage(url);
		List<HtmlElement> trList = (List<HtmlElement>) page
				.getByXPath("//div[contains(@class,'view-content')]/table/tbody/tr");
		for (int i = 0; i < trList.size(); i++) {

			HtmlElement tr = trList.get(i);
			try {
				DomNodeList<DomNode> tds = tr.getChildNodes();
				DomNode country = tds.get(1);
				DomNode project_name = tds.get(3);
				DomNode status = tds.get(5);
				DomNode approvaldate = tds.get(7);

				DomNodeList<DomNode> subtds = project_name.getChildNodes();
				HtmlAnchor nameAnchor = (HtmlAnchor) subtds.get(1);

				DomNodeList<DomNode> subsubtds = nameAnchor.getChildNodes();
				DomNode nameSpan = subsubtds.get(0);
				DomNode name = subsubtds.get(1);

				DomNodeList<DomNode> subsubsubtds = nameSpan.getChildNodes();
				DomNode meta = subsubsubtds.get(0);
				DomNode productsSpan = subsubsubtds.get(1);

				DomNodeList<DomNode> subsubsubsubtds = productsSpan.getChildNodes();
				DomNode products = subsubsubsubtds.get(0);

				DataBean bean = new DataBean();
				bean.setId(UUID.randomUUID().toString());
				bean.setBaseurl(url);
				bean.setUrl(page.getUrl().toString());
				bean.setCountry(country.asText());
				bean.setMeta(meta == null ? null : meta.asText());
				bean.setProduct(products == null ? null : products.asText());
				bean.setName(name.asText());
				bean.setHref(nameAnchor.getHrefAttribute());
				bean.setStatus(status.asText());
				bean.setApproval(approvaldate.asText());
				
				if (approvaldate.asText().length() != 0) {
					// 变换bean的日期格式
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Map<String, String> dateMap = new HashMap<String, String>() {
						{
							put("Jan", "01");
							put("Feb", "02");
							put("Mar", "03");
							put("Apr", "04");
							put("May", "05");
							put("Jun", "06");
							put("Jul", "07");
							put("Aug", "08");
							put("Sep", "09");
							put("Oct", "10");
							put("Nov", "11");
							put("Dec", "12");
						}
					};
					try {
						String[] curDate = approvaldate.asText().split(" ");
						Date parseDate = (Date) sdf
								.parse(curDate[2] + "-" + dateMap.get(curDate[1]) + "-" + curDate[0]);
						bean.setApprovaldate(parseDate);
						String url1 = bean.getUrl();
						if(url1.indexOf("www.adb.org")!=-1){
							bean.setProjecttype("亚洲银行");	
						}
						List<DataBean> dataBeanList = service.findDataBean(bean.getName());
						if(dataBeanList.size() == 0){
							service.insertDataBean(bean);
							System.out.println("新增亚洲银行为："+bean.getName());
						}else{
							System.out.println("已存在亚洲银行为："+bean.getName());
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					bean.setApprovaldate(null);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 世界银行
	 * @throws Exception
	 */
	public void analyPage() throws Exception {
		String url = "http://projects.shihang.org/?lang=zh";
		client.setAjaxController(new NicelyResynchronizingAjaxController());// 设置支持AJAX
		client.getOptions().setThrowExceptionOnScriptError(false);
		client.getOptions().setTimeout(50000);// 设置“浏览器”的请求超时时间
		client.setJavaScriptTimeout(50000);// 设置JS执行的超时时间
		client.waitForBackgroundJavaScript(50000);// 设置JS后台等待执行时间
		HtmlPage page = (HtmlPage) client.getPage(url);
		List<HtmlTableRow> trList = (List<HtmlTableRow>) page.getByXPath("//td[@id='homePageRecentProjects']/table/tbody/tr");
		
		for (int i = 0; i < trList.size(); i++) {
			
			HtmlElement tr = trList.get(i);			
			try {
				HtmlTableDataCell td = (HtmlTableDataCell) tr.getChildElements().iterator().next();
				HtmlParagraph p = (HtmlParagraph) td.getChildElements().iterator().next();
				Iterator<DomElement> iterator =  p.getChildElements().iterator();
				List<DomElement> elements = new ArrayList<DomElement>();
				while(iterator.hasNext()){
					elements.add( iterator.next());					
				}
				
				DataBean bean = new DataBean();
				bean.setId(UUID.randomUUID().toString());
				bean.setBaseurl(url);
				bean.setUrl(page.getUrl().toString());
				bean.setCountry(elements.get(2).asText());
				
				HtmlAnchor anchor = (HtmlAnchor) elements.get(0);
				bean.setName(anchor.asText());
				bean.setHref("http://projects.shihang.org"+anchor.getHrefAttribute());
				bean.setStatus("Approved");
				String[] tmpStr = p.asText().split("-");
				String date = tmpStr[tmpStr.length-1].trim();
				bean.setApproval(date);
				
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日"); 
				Date parseDate = (Date) sdf.parse(date);
				bean.setApprovaldate(parseDate);
				List<DataBean> list = service.findDataBean(bean.getName());
				if(bean.getUrl().indexOf("projects.shihang.org")!=-1){
					bean.setProjecttype("世界银行");
				}
				if(list.size()>0){
					System.out.println("已存在世界银行项目为:"+bean.getName());
				}else{
					service.insertDataBean(bean);
					System.out.println("新添加世界银行项目为："+bean.getName());
				}
				System.out.println(bean);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
	
	
	public static void main(String[] args) throws Exception {
		DataBeanReTilian beanReTilian = new DataBeanReTilian();
		beanReTilian.analyPage();
	}
}
