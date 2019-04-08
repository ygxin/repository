package com.nsc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsc.mapper.NewBeanMapper;
import com.nsc.pojo.NewBean;
import com.nsc.service.NewBeanService;
@Service
public class NewBeanServiceImpl implements NewBeanService {
	
	@Autowired
	private NewBeanMapper mapper;
	
	
	@Override
	public List<NewBean> findNewBean(String title) {
		return this.mapper.findNewBean(title);
	}

	@Override
	public void insertNewBean(NewBean bean) {
		mapper.insertNewBean(bean);
	}


}
