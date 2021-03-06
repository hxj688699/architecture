package com.feinno.architecture.storemgr.service;


import com.feinno.architecture.common.service.IBaseService;

import com.feinno.architecture.storemgr.vo.StoreModel;
import com.feinno.architecture.storemgr.vo.StoreQueryModel;

public interface StoreService extends IBaseService<StoreModel,StoreQueryModel>{
	/**
	 * 根据商品ID更新库存
	 * @param sm
	 * @return
	 */
	public int updateByGoodsId(StoreModel sm);
}

