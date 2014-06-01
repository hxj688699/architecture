package com.feinno.architecture.customermgr;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.feinno.architecture.customermgr.service.CustomerService;
import com.feinno.architecture.customermgr.vo.CustomerModel;
import com.feinno.architecture.customermgr.vo.CustomerQueryModel;
import com.feinno.architecture.pageutil.Page;

@Service
public class Client {

	@Autowired
	private CustomerService service;
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Client client = (Client)ctx.getBean("client");
//		CustomerModel cm = new CustomerModel();
//		cm.setCustomerId("hxj4");
//		cm.setPwd("hxj4");
//		cm.setRegisterTime(new Date().toString());
//		cm.setTrueName("小将3");
//		cm.setShowName("小将3");
//		
//		client.service.create(cm);
		CustomerQueryModel cqm = new CustomerQueryModel();
		cqm.getPage().setNowPage(1);
		cqm.getPage().setPageSize(2);
		Page<CustomerModel> page = client.service.queryByPage(cqm);
		System.out.println("page: " + page.getResult());		

		Page<CustomerModel> page1 = client.service.queryByPage(cqm);
		System.out.println("page1: " + page1.getResult());
	}

}
