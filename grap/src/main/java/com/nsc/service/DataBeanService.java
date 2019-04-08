package com.nsc.service;

import java.util.List;

import com.nsc.pojo.DataBean;

public interface DataBeanService {
	
	void insertDataBean(DataBean bean);
	
	List<DataBean> findDataBean(String name);
}
