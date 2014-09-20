package com.feinno.architecture.customermgr.dao;

import org.springframework.stereotype.Repository;

import com.feinno.architecture.common.dao.BaseDAO;
import com.feinno.architecture.customermgr.vo.CustomerModel;
import com.feinno.architecture.customermgr.vo.CustomerQueryModel;
@Repository
public interface CustomerDAO extends BaseDAO<CustomerModel, CustomerQueryModel> {

	/**
	 * 根据custometId获取CustomerModel
	 * @param custometId
	 * @return
	 */
	public CustomerModel getByCustomerId(String custometId);
}
