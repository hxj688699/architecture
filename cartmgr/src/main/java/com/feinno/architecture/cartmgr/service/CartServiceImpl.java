package com.feinno.architecture.cartmgr.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feinno.architecture.common.service.BaseService;

import com.feinno.architecture.cartmgr.dao.CartDAO;
import com.feinno.architecture.cartmgr.vo.CartModel;
import com.feinno.architecture.cartmgr.vo.CartQueryModel;

@Service
@Transactional
public class CartServiceImpl extends BaseService<CartModel,CartQueryModel> implements CartService{
	private CartDAO dao = null;
	@Autowired
	private void setDAO(CartDAO dao){
		this.dao = dao;
		super.setDAO(dao);
	}
	
}