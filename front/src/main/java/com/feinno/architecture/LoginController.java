package com.feinno.architecture;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.feinno.architecture.customermgr.service.CustomerService;
import com.feinno.architecture.customermgr.vo.CustomerModel;

@Controller
@RequestMapping("/")
public class LoginController {
	@Autowired
	private CustomerService cs;

	@RequestMapping(value="/toLogin", method=RequestMethod.GET)
	public String toLogin(){
		return "login";
	}

	@RequestMapping("/login")
	public String login(String custometId, String pwd, HttpServletResponse resp){
		if (custometId == null || custometId.trim().length() == 0) {
			return "login";
		}
		CustomerModel cm = cs.getByCustomerId(custometId);
		if (cm == null || cm.getUuid() <= 0) {
			return "login";
		}
		Cookie c = new Cookie("MyLogin", cm.getUuid() + "," + System.currentTimeMillis());
		resp.addCookie(c);
		
		return "redirect:toIndex";
	}
}
