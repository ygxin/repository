package com.nsc.service;

import java.util.List;

import com.nsc.pojo.BusinessNews;

public interface BusinessNewsServices {
	
	List<BusinessNews> findBusinessNewsInfo(String title);
	
	void insertBusinessNewsInfo(BusinessNews businessNews);
}
