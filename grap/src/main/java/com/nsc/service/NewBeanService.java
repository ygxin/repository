package com.nsc.service;

import java.util.List;

import com.nsc.pojo.NewBean;

public interface NewBeanService {
	
	void insertNewBean(NewBean newBean);
	
	List<NewBean> findNewBean(String title );
}
