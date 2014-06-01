package com.feinno.architecture.ordermgr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.feinno.architecture.ordermgr.service.OrderDetailService;
import com.feinno.architecture.ordermgr.vo.OrderDetailModel;
import com.feinno.architecture.ordermgr.vo.OrderDetailQueryModel;
import com.feinno.architecture.pageutil.Page;
import com.feinno.architecture.util.json.JsonHelper;

@Controller
@RequestMapping(value="/orderDetail")
public class OrderDetailController {
	@Autowired
	private OrderDetailService service = null;
	
	@RequestMapping(value="toAdd",method=RequestMethod.GET)
	public String toAdd(){
		
		return "orderDetail/add";
	}
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(@ModelAttribute("m") OrderDetailModel m){
		service.create(m);
		return "orderDetail/success";
	}
	@RequestMapping(value="toUpdate/{uuid}",method=RequestMethod.GET)
	public String toUpdate(Model model,@PathVariable("uuid") int uuid){
		OrderDetailModel m = service.getByUuid(uuid);
		model.addAttribute("m", m);
		return "orderDetail/update";
	}
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String post(@ModelAttribute("m") OrderDetailModel m){
		service.update(m);
		return "orderDetail/success";
	}
	@RequestMapping(value="toDelete/{uuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("uuid") int uuid){
		OrderDetailModel m = service.getByUuid(uuid);
		model.addAttribute("m", m);
		return "orderDetail/delete";
	}
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String post(@RequestParam("uuid") int uuid){
		service.delete(uuid);
		return "orderDetail/success";
	}
	@RequestMapping(value="toList",method=RequestMethod.GET)
	public String toList(@ModelAttribute("wm")OrderDetailWebModel wm,Model model){
		OrderDetailQueryModel qm = null;
		if(wm.getQueryJsonString()==null || wm.getQueryJsonString().trim().length()==0){
			qm =  new OrderDetailQueryModel();
		}else{
			String s = wm.getQueryJsonString();
			s = s.replace("-", "%");
			qm = (OrderDetailQueryModel)JsonHelper.str2object(s, OrderDetailQueryModel.class);
		}
		
		qm.getPage().setNowPage(wm.getNowPage());
		if(wm.getPageSize() > 0){
			qm.getPage().setPageSize(wm.getPageSize());
		}
		
		Page<OrderDetailModel> dbPage = service.queryByPage(qm);
		
		//
		model.addAttribute("wm", wm);
		model.addAttribute("page", dbPage);
				
		return "orderDetail/list";
	}
	@RequestMapping(value="toQuery",method=RequestMethod.GET)
	public String toQuery(){
		return "orderDetail/query";
	}	
}
