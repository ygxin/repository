package com.nsc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nsc.pojo.BusinessNews;

@Mapper
public interface  BusinessNewsMapper {
	List<BusinessNews> findBusinessNewsInfo(String title);
	
	void insertBusinessNewsInfo(BusinessNews businessNews);
}
