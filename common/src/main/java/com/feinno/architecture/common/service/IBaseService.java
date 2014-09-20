package com.feinno.architecture.common.service;

import com.feinno.architecture.common.vo.BaseModel;
import com.feinno.architecture.pageutil.Page;

public interface IBaseService<M, QM extends BaseModel> {
	public int create(M m);
	public void update(M m);
	public void delete(int uuid);
	
	public M getByUuid(int uuid);
	public Page<M> queryByPage(QM qm);
}
