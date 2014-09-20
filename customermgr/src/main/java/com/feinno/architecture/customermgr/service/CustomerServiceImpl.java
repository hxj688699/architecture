package com.feinno.architecture.customermgr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feinno.architecture.common.service.BaseService;
import com.feinno.architecture.customermgr.dao.CustomerDAO;
import com.feinno.architecture.customermgr.vo.CustomerModel;
import com.feinno.architecture.customermgr.vo.CustomerQueryModel;

@Service
public class CustomerServiceImpl extends BaseService<CustomerModel, CustomerQueryModel> implements
		CustomerService {
	private CustomerDAO dao;
	@Autowired
	public void setDAO(CustomerDAO dao){
		super.setDAO(dao);
		this.dao = dao;
	}
	@Override
	public CustomerModel getByCustomerId(String custometId) {
		return dao.getByCustomerId(custometId);
	}
}
