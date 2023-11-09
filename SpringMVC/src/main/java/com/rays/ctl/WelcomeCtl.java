package com.rays.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/Welcome")
public class WelcomeCtl {

	@GetMapping
	public String loginView() {
		return "login";
	}

	@RequestMapping(method = RequestMethod.GET, value = "message")
	public String display(Model model) {
		model.addAttribute("message", "Welcome to Spring MVC");
		return "welcome";
	}

	@GetMapping("/hi")
	public ModelAndView hi() {
		ModelAndView model = new ModelAndView();
		model.setViewName("welcome");
		model.addObject("message", "Welcome to Spring MVC");
		return model;
	}

	@PostMapping
	public String submit(@RequestParam("login") String loginId, @RequestParam("password") String pwd) {
		System.out.println(loginId);
		System.out.println(pwd);
		return "Welcome";
	}
}
