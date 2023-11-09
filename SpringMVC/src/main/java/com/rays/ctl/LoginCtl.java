package com.rays.ctl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rays.dto.UserDTO;
import com.rays.form.LoginForm;
import com.rays.service.UserServiceImpl;

@Controller
@RequestMapping(value = "Auth")
public class LoginCtl {

	@Autowired
	public UserServiceImpl service;

	@GetMapping
	public String display(@ModelAttribute("form") LoginForm form, Model model) {
		return "Login";
	}

	@GetMapping("logout")
	public String logout(@ModelAttribute("form") LoginForm form, Model model, HttpSession session) {

		session.invalidate();
		return "redirect:/Auth";
	}

	@PostMapping
	public String submit(@ModelAttribute("form") LoginForm form, Model model, HttpSession session) {

		UserDTO dto = service.auth(form.getLogin(), form.getPassword());

		String page = "Login";

		if (dto != null) {
			session.setAttribute("user", dto);
			page = "Welcome";
		} else {
			model.addAttribute("msg", "Login & Password Invalid...!!!");
		}

		return page;
	}

}
