package com.feinno.architecture.goodsmgr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.feinno.architecture.goodsmgr.service.GoodsService;
import com.feinno.architecture.goodsmgr.vo.GoodsModel;
import com.feinno.architecture.goodsmgr.vo.GoodsQueryModel;
import com.feinno.architecture.pageutil.Page;
import com.feinno.architecture.util.format.DateFormatUtil;
import com.feinno.architecture.util.json.JsonHelper;

@Controller
@RequestMapping(value="/goods")
public class GoodsController {
	@Autowired
	private GoodsService service = null;
	
	@RequestMapping(value="toAdd",method=RequestMethod.GET)
	public String toAdd(){
		
		return "goods/add";
	}
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(@ModelAttribute("m") GoodsModel m){
		service.create(m);
		return "goods/success";
	}
	@RequestMapping(value="toUpdate/{uuid}",method=RequestMethod.GET)
	public String toUpdate(Model model,@PathVariable("uuid") int uuid){
		GoodsModel m = service.getByUuid(uuid);
		model.addAttribute("m", m);
		return "goods/update";
	}
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String post(@ModelAttribute("m") GoodsModel m){
		service.update(m);
		return "goods/success";
	}
	@RequestMapping(value="toDelete/{uuid}",method=RequestMethod.GET)
	public String toDelete(Model model,@PathVariable("uuid") int uuid){
		GoodsModel m = service.getByUuid(uuid);
		model.addAttribute("m", m);
		return "goods/delete";
	}
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public String post(@RequestParam("uuid") int uuid){
		service.delete(uuid);
		return "goods/success";
	}
	@RequestMapping(value="toList",method=RequestMethod.GET)
	public String toList(@ModelAttribute("wm")GoodsWebModel wm,Model model){
		GoodsQueryModel qm = null;
		if(wm.getQueryJsonString()==null || wm.getQueryJsonString().trim().length()==0){
			qm =  new GoodsQueryModel();
		}else{
			String s = wm.getQueryJsonString();
			s = s.replace("-", "%");
			qm = (GoodsQueryModel)JsonHelper.str2object(s, GoodsQueryModel.class);
		}
		
		qm.getPage().setNowPage(wm.getNowPage());
		if(wm.getPageSize() > 0){
			qm.getPage().setPageSize(wm.getPageSize());
		}
		
		Page<GoodsModel> dbPage = service.queryByPage(qm);
		
		//
		model.addAttribute("wm", wm);
		model.addAttribute("page", dbPage);
				
		return "goods/list";
	}
	@RequestMapping(value="toQuery",method=RequestMethod.GET)
	public String toQuery(){
		return "goods/query";
	}	
}
