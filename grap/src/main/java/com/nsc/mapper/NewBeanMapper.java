package com.nsc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nsc.pojo.NewBean;

@Mapper
public interface NewBeanMapper {
	
	void insertNewBean(NewBean bean );
	
	List<NewBean> findNewBean(String title);
}
