package com.nsc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsc.mapper.BusinessNewsMapper;
import com.nsc.pojo.BusinessNews;
import com.nsc.service.BusinessNewsServices;

@Service
public class BusinessNewsServicesImpl implements BusinessNewsServices {
	@Autowired
	private BusinessNewsMapper mapper;

	@Override
	public List<BusinessNews> findBusinessNewsInfo(String title) {
		return mapper.findBusinessNewsInfo(title);
	}

	@Override
	public void insertBusinessNewsInfo(BusinessNews businessNews) {
		 mapper.insertBusinessNewsInfo(businessNews);
		
	}

}
