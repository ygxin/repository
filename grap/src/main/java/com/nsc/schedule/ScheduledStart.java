package com.nsc.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.nsc.reptilian.DataBeanReTilian;
import com.nsc.reptilian.NewBeanReptilian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * spring 定时任务
 * 
 * @author xyg
 *
 */
@Component
public class ScheduledStart {

	@Autowired
	private NewBeanReptilian reptilian;
	
	@Autowired
	private DataBeanReTilian dataBeanReTilian;

	private static final Logger logger = LoggerFactory.getLogger(ScheduledStart.class);

	@Scheduled(cron = "0/600 * * * * ?")
	public void executeFileDownLoadTask() throws Exception {

		// 间隔2分钟,执行任务
		Thread current = Thread.currentThread();
		System.out.println("定时任务1:" + current.getId());
	    reptilian.CrawlingNew();
	    //dataBeanReTilian.analysisPage();
		dataBeanReTilian.analyPage();
		reptilian.CrawlingNewTO();
		logger.info("启动中。。。。。。。。。。。。。。");
	}

}
