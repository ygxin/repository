package com.nsc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nsc.pojo.DataBean;

@Mapper
public interface DataBeanMapper {
	
	void insertDataBean(DataBean bean);
	
	List<DataBean> findDataBean(String name);
}
