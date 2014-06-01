package com.feinno.architecture.goodsmgr.dao;

import org.springframework.stereotype.Repository;

import com.feinno.architecture.common.dao.BaseDAO;
import com.feinno.architecture.goodsmgr.vo.GoodsModel;
import com.feinno.architecture.goodsmgr.vo.GoodsQueryModel;

@Repository
public interface GoodsDAO extends BaseDAO<GoodsModel,GoodsQueryModel>{
	
}
