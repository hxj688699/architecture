package com.feinno.architecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.feinno.architecture.cartmgr.service.CartService;
import com.feinno.architecture.cartmgr.vo.CartModel;
import com.feinno.architecture.cartmgr.vo.CartQueryModel;
import com.feinno.architecture.goodsmgr.service.GoodsService;
import com.feinno.architecture.goodsmgr.vo.GoodsModel;
import com.feinno.architecture.goodsmgr.vo.GoodsQueryModel;
import com.feinno.architecture.ordermgr.service.OrderDetailService;
import com.feinno.architecture.ordermgr.service.OrderService;
import com.feinno.architecture.ordermgr.vo.OrderDetailModel;
import com.feinno.architecture.ordermgr.vo.OrderModel;
import com.feinno.architecture.pageutil.Page;
import com.feinno.architecture.storemgr.service.StoreService;
import com.feinno.architecture.storemgr.vo.StoreModel;
import com.feinno.architecture.util.format.DateFormatUtil;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private GoodsService cs;
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private StoreService storeService;

	@RequestMapping(value="/toIndex", method=RequestMethod.GET)
	public String toIndex(Model model){
		GoodsQueryModel qm = new GoodsQueryModel();
		qm.getPage().setPageSize(10);
		Page<GoodsModel> page = cs.queryByPage(qm);
		model.addAttribute("goods", page.getResult());
		return "index";
	}

	@RequestMapping(value="/toGoodsDesc/{uuid}", method=RequestMethod.GET)
	public String toIndex(Model model, @PathVariable("uuid") int uuid){
		GoodsModel m = cs.getByUuid(uuid);		
		model.addAttribute("good", m);
		return "goods/desc";
	}

	@RequestMapping(value="/addToCart/{uuid}", method=RequestMethod.GET)
	public String addToCar(Model m, @PathVariable("uuid") int uuid, @CookieValue("MyLogin") String myLogin){
		int customerId = Integer.parseInt(myLogin.split(",")[0]);
		CartModel cm = new CartModel();
		cm.setCustomerUuid(customerId);
		cm.setGoodsUuid(uuid);
		cm.setBuyNum(1);
		//新增记录
		cartService.create(cm);
		//////////////////////////////////
		CartQueryModel qm = new CartQueryModel();
		qm.getPage().setPageSize(10);
		qm.setCustomerUuid(customerId);
		Page<CartModel> page = cartService.queryByPage(qm);
		m.addAttribute("carts", page.getResult());
		
		return "cart/myCart";
	}

	@RequestMapping(value="/toCart", method=RequestMethod.GET)
	public String toCar(Model m, @CookieValue("MyLogin") String myLogin){
		int customerId = Integer.parseInt(myLogin.split(",")[0]);
		//////////////////////////////////
		CartQueryModel qm = new CartQueryModel();
		qm.getPage().setPageSize(10);
		qm.setCustomerUuid(customerId);
		Page<CartModel> page = cartService.queryByPage(qm);
		m.addAttribute("carts", page.getResult());
		
		return "cart/myCart";
	}

	@RequestMapping(value="/order", method=RequestMethod.GET)
	public String order(@CookieValue("MyLogin") String myLogin){
		int customerId = Integer.parseInt(myLogin.split(",")[0]);
		//////////////////////////////////
		CartQueryModel qm = new CartQueryModel();
		qm.getPage().setPageSize(50);
		qm.setCustomerUuid(customerId);
		Page<CartModel> page = cartService.queryByPage(qm);
		//计算价格
		float total = 0;
		for (CartModel cm : page.getResult()) {			
			total += 10 * cm.getBuyNum();
		}
		
		OrderModel om = new OrderModel();
		om.setCustomerUuid(customerId);
		om.setOrderTime(DateFormatUtil.long2string(System.currentTimeMillis()));
		om.setSaveMoney(0.0F);
		om.setTotalMoney(total);
		om.setState(1);
		//新增订单
		int orderId = orderService.create(om);
		////////////////////////////////////////////////		
		for (CartModel cm : page.getResult()) {
			OrderDetailModel odm = new OrderDetailModel();
			odm.setGoodsUuid(cm.getGoodsUuid());
			odm.setOrderUuid(orderId);
			odm.setOrderNum(cm.getBuyNum());
			odm.setPrice(10.0F);
			odm.setMoney(odm.getPrice() * odm.getOrderNum());
			odm.setSaveMoney(0.0F);
			//新增订单详情
			orderDetailService.create(odm);
			////////////////////////////////////////
			//更新库存
			StoreModel sm = new StoreModel();
			sm.setGoodsUuid(cm.getGoodsUuid());
			sm.setStoreNum(cm.getBuyNum());
			storeService.updateByGoodsId(sm);
			/////////////////////////////////////////
			//清空购物车
			cartService.delete(cm.getUuid());
		}
		
		return "success";
	}
}
