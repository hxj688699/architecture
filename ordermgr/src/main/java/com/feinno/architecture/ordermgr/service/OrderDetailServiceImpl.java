package com.feinno.architecture.ordermgr.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feinno.architecture.common.service.BaseService;

import com.feinno.architecture.ordermgr.dao.OrderDetailDAO;
import com.feinno.architecture.ordermgr.vo.OrderDetailModel;
import com.feinno.architecture.ordermgr.vo.OrderDetailQueryModel;

@Service
@Transactional
public class OrderDetailServiceImpl extends BaseService<OrderDetailModel,OrderDetailQueryModel> implements OrderDetailService{
	private OrderDetailDAO dao = null;
	@Autowired
	private void setDAO(OrderDetailDAO dao){
		this.dao = dao;
		super.setDAO(dao);
	}
	
}