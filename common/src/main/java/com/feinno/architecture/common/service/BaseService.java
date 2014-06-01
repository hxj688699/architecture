package com.feinno.architecture.common.service;

import java.util.List;

import com.feinno.architecture.common.dao.BaseDAO;
import com.feinno.architecture.common.vo.BaseModel;
import com.feinno.architecture.pageutil.Page;

public class BaseService<M, QM extends BaseModel> implements IBaseService<M, QM> {

	private BaseDAO dao;
	
	public void setDAO(BaseDAO dao){
		this.dao = dao;
	}
	@Override
	public void create(M m) {
		dao.create(m);
	}

	@Override
	public void update(M m) {
		dao.update(m);
	}

	@Override
	public void delete(int uuid) {
		dao.delete(uuid);
	}

	@Override
	public M getByUuid(int uuid) {
		return (M)dao.getByUuid(uuid);
	}

	@Override
	public Page<M> queryByPage(QM qm) {
		List<M> list = dao.queryByPage(qm);
		qm.getPage().setResult(list);
		return qm.getPage();
	}

}
