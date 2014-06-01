package com.feinno.architecture.ordermgr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.feinno.architecture.ordermgr.service.OrderService;
import com.feinno.architecture.ordermgr.vo.OrderModel;
import com.feinno.architecture.ordermgr.vo.OrderQueryModel;
import com.feinno.architecture.pageutil.Page;
import com.feinno.architecture.util.json.JsonHelper;

@Controller
@RequestMapping(value="/order")
public class OrderController {
	@Autowired
	private OrderService service = null;
	
	@RequestMapping(value="toAdd",method=RequestMethod.GET)
	public String toAdd(){
		
		return "order/add";
	}
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(@ModelAttribute("m") OrderModel m){
		service.create(m);
		return "order/success";
	}
	@RequestMapping(value="toUpdate/{uuid}",method=RequestMethod.GET)
	public String toUpdate(Model model,@PathVariable("uuid") int uuid){
		OrderModel m = service.getByUuid(uuid);
		model.addAttribute("m", m);
		return "order/update";
	}
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String post(@ModelAttribute("m") OrderModel m){
		service.update(m);
		return "order/success";
	}
	@RequestMapping(value="toDelete/{uuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("uuid") int uuid){
		OrderModel m = service.getByUuid(uuid);
		model.addAttribute("m", m);
		return "order/delete";
	}
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String post(@RequestParam("uuid") int uuid){
		service.delete(uuid);
		return "order/success";
	}
	@RequestMapping(value="toList",method=RequestMethod.GET)
	public String toList(@ModelAttribute("wm")OrderWebModel wm,Model model){
		OrderQueryModel qm = null;
		if(wm.getQueryJsonString()==null || wm.getQueryJsonString().trim().length()==0){
			qm =  new OrderQueryModel();
		}else{
			String s = wm.getQueryJsonString();
			s = s.replace("-", "%");
			qm = (OrderQueryModel)JsonHelper.str2object(s, OrderQueryModel.class);
		}
		
		qm.getPage().setNowPage(wm.getNowPage());
		if(wm.getPageSize() > 0){
			qm.getPage().setPageSize(wm.getPageSize());
		}
		
		Page<OrderModel> dbPage = service.queryByPage(qm);
		
		//
		model.addAttribute("wm", wm);
		model.addAttribute("page", dbPage);
				
		return "order/list";
	}
	@RequestMapping(value="toQuery",method=RequestMethod.GET)
	public String toQuery(){
		return "order/query";
	}	
}
