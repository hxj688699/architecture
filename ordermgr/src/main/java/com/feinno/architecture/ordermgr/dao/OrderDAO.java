package com.feinno.architecture.ordermgr.dao;

import org.springframework.stereotype.Repository;

import com.feinno.architecture.common.dao.BaseDAO;
import com.feinno.architecture.ordermgr.vo.OrderModel;
import com.feinno.architecture.ordermgr.vo.OrderQueryModel;

@Repository
public interface OrderDAO extends BaseDAO<OrderModel,OrderQueryModel>{
	
}
