package com.feinno.architecture.ordermgr.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feinno.architecture.common.service.BaseService;

import com.feinno.architecture.ordermgr.dao.OrderDAO;
import com.feinno.architecture.ordermgr.vo.OrderModel;
import com.feinno.architecture.ordermgr.vo.OrderQueryModel;

@Service
@Transactional
public class OrderServiceImpl extends BaseService<OrderModel,OrderQueryModel> implements OrderService{
	private OrderDAO dao = null;
	@Autowired
	private void setDAO(OrderDAO dao){
		this.dao = dao;
		super.setDAO(dao);
	}
	
}