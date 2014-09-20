package com.feinno.architecture.storemgr.dao;

import org.springframework.stereotype.Repository;

import com.feinno.architecture.common.dao.BaseDAO;
import com.feinno.architecture.storemgr.vo.StoreModel;
import com.feinno.architecture.storemgr.vo.StoreQueryModel;

@Repository
public interface StoreDAO extends BaseDAO<StoreModel,StoreQueryModel>{
	/**
	 * 根据商品ID更新库存
	 * @param sm
	 * @return
	 */
	public int updateByGoodsId(StoreModel sm);
}
