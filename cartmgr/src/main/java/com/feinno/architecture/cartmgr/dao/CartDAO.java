package com.feinno.architecture.cartmgr.dao;

import org.springframework.stereotype.Repository;

import com.feinno.architecture.common.dao.BaseDAO;
import com.feinno.architecture.cartmgr.vo.CartModel;
import com.feinno.architecture.cartmgr.vo.CartQueryModel;

@Repository
public interface CartDAO extends BaseDAO<CartModel,CartQueryModel>{
	
}
