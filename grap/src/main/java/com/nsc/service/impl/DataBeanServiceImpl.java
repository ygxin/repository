package com.nsc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsc.mapper.DataBeanMapper;
import com.nsc.pojo.DataBean;
import com.nsc.service.DataBeanService;

@Service
public class DataBeanServiceImpl implements DataBeanService {
	
	@Autowired
	private DataBeanMapper mapper;

	@Override
	public void insertDataBean(DataBean bean) {
		// TODO Auto-generated method stub
		this.mapper.insertDataBean(bean);
	}

	@Override
	public List<DataBean> findDataBean(String name) {
		// TODO Auto-generated method stub
		return mapper.findDataBean(name);
	}

}
