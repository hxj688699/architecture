package com.feinno.architecture.customermgr.service;

import com.feinno.architecture.common.service.IBaseService;
import com.feinno.architecture.customermgr.vo.CustomerModel;
import com.feinno.architecture.customermgr.vo.CustomerQueryModel;

public interface CustomerService extends IBaseService<CustomerModel, CustomerQueryModel> {

	/**
	 * 根据custometId获取CustomerModel
	 * @param custometId
	 * @return
	 */
	public CustomerModel getByCustomerId(String custometId);
}
