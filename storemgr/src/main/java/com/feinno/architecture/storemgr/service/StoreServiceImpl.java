package com.feinno.architecture.storemgr.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feinno.architecture.common.service.BaseService;

import com.feinno.architecture.storemgr.dao.StoreDAO;
import com.feinno.architecture.storemgr.vo.StoreModel;
import com.feinno.architecture.storemgr.vo.StoreQueryModel;

@Service
@Transactional
public class StoreServiceImpl extends BaseService<StoreModel,StoreQueryModel> implements StoreService{
	private StoreDAO dao = null;
	@Autowired
	private void setDAO(StoreDAO dao){
		this.dao = dao;
		super.setDAO(dao);
	}
	
}