package com.feinno.architecture.goodsmgr.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feinno.architecture.common.service.BaseService;

import com.feinno.architecture.goodsmgr.dao.GoodsDAO;
import com.feinno.architecture.goodsmgr.vo.GoodsModel;
import com.feinno.architecture.goodsmgr.vo.GoodsQueryModel;

@Service
@Transactional
public class GoodsServiceImpl extends BaseService<GoodsModel,GoodsQueryModel> implements GoodsService{
	private GoodsDAO dao = null;
	@Autowired
	private void setDAO(GoodsDAO dao){
		this.dao = dao;
		super.setDAO(dao);
	}
	
}