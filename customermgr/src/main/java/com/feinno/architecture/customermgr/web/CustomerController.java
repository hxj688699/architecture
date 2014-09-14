package com.feinno.architecture.customermgr.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.feinno.architecture.customermgr.service.CustomerService;
import com.feinno.architecture.customermgr.vo.CustomerModel;
import com.feinno.architecture.customermgr.vo.CustomerQueryModel;
import com.feinno.architecture.pageutil.Page;
import com.feinno.architecture.util.format.DateFormatUtil;
import com.feinno.architecture.util.json.JsonHelper;

@Controller
@RequestMapping(value="/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="toAdd", method=RequestMethod.GET)
	public String toAdd(){
		System.out.println("Hello World!");
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager sm = factory.getInstance();
		SecurityUtils.setSecurityManager(sm);
		UsernamePasswordToken token = new UsernamePasswordToken("hxj", "hxj123!");		
		token.setRememberMe(true);
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.login(token);
		String sessionId = (String) currentUser.getSession().getId();
		System.out.println(sessionId);
		System.out.println("currentUser.isAuthenticated: "+currentUser.isAuthenticated());
		System.out.println(currentUser.isPermitted("r12"));
		return "customer/add";
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String add(@ModelAttribute("cm") CustomerModel cm){
		System.out.println("currentUser.isAuthenticated: "+SecurityUtils.getSubject().isAuthenticated());
		cm.setRegisterTime(DateFormatUtil.long2string(System.currentTimeMillis()));
		customerService.create(cm);
		return "customer/success";
	}
	
	@RequestMapping(value="toUpdate/{customerUuid}", method=RequestMethod.GET)
	public String toUpdate(Model model, @PathVariable("customerUuid") int customerUuid){
		CustomerModel cm = customerService.getByUuid(customerUuid);
		model.addAttribute("cm", cm);
		return "customer/update";
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String update(@ModelAttribute("cm") CustomerModel cm){
		customerService.update(cm);
		return "customer/success";
	}
	
	@RequestMapping(value="toDelete/{customerUuid}", method=RequestMethod.GET)
	public String toDelete(Model model, @PathVariable("customerUuid") int customerUuid){
		CustomerModel cm = customerService.getByUuid(customerUuid);
		model.addAttribute("cm", cm);
		return "customer/delete";
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String delete(@RequestParam("uuid") int uuid){
		customerService.delete(uuid);
		return "customer/success";
	}
	
	@RequestMapping(value="toList", method=RequestMethod.GET)
	public String toList(@ModelAttribute("wm") CustomerWebModel wm, Model model){
		CustomerQueryModel cqm = null;
		if (wm.getQueryJsonString() == null || wm.getQueryJsonString().trim().length() == 0) {
			cqm = new CustomerQueryModel();
		}else{
			cqm = (CustomerQueryModel) JsonHelper.str2object(wm.getQueryJsonString(), CustomerQueryModel.class);
		}
		cqm.getPage().setNowPage(wm.getNowPage());
		Page<CustomerModel> dbpage = customerService.queryByPage(cqm);	
		model.addAttribute("page", dbpage);
		return "customer/list";
	}
	
	@RequestMapping(value="toQuery", method=RequestMethod.GET)
	public String toQuery(){
		return "customer/query";
	}
}
