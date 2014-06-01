package com.feinno.architecture.storemgr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.feinno.architecture.pageutil.Page;
import com.feinno.architecture.storemgr.service.StoreService;
import com.feinno.architecture.storemgr.vo.StoreModel;
import com.feinno.architecture.storemgr.vo.StoreQueryModel;
import com.feinno.architecture.util.json.JsonHelper;

@Controller
@RequestMapping(value="/store")
public class StoreController {
	@Autowired
	private StoreService service = null;
	
	@RequestMapping(value="toAdd",method=RequestMethod.GET)
	public String toAdd(){
		
		return "store/add";
	}
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(@ModelAttribute("m") StoreModel m){
		service.create(m);
		return "store/success";
	}
	@RequestMapping(value="toUpdate/{uuid}",method=RequestMethod.GET)
	public String toUpdate(Model model,@PathVariable("uuid") int uuid){
		StoreModel m = service.getByUuid(uuid);
		model.addAttribute("m", m);
		return "store/update";
	}
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String post(@ModelAttribute("m") StoreModel m){
		service.update(m);
		return "store/success";
	}
	@RequestMapping(value="toDelete/{uuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("uuid") int uuid){
		StoreModel m = service.getByUuid(uuid);
		model.addAttribute("m", m);
		return "store/delete";
	}
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String post(@RequestParam("uuid") int uuid){
		service.delete(uuid);
		return "store/success";
	}
	@RequestMapping(value="toList",method=RequestMethod.GET)
	public String toList(@ModelAttribute("wm")StoreWebModel wm,Model model){
		StoreQueryModel qm = null;
		if(wm.getQueryJsonString()==null || wm.getQueryJsonString().trim().length()==0){
			qm =  new StoreQueryModel();
		}else{
			String s = wm.getQueryJsonString();
			s = s.replace("-", "%");
			qm = (StoreQueryModel)JsonHelper.str2object(s, StoreQueryModel.class);
		}
		
		qm.getPage().setNowPage(wm.getNowPage());
		if(wm.getPageSize() > 0){
			qm.getPage().setPageSize(wm.getPageSize());
		}
		
		Page<StoreModel> dbPage = service.queryByPage(qm);
		
		//
		model.addAttribute("wm", wm);
		model.addAttribute("page", dbPage);
				
		return "store/list";
	}
	@RequestMapping(value="toQuery",method=RequestMethod.GET)
	public String toQuery(){
		return "store/query";
	}	
}
