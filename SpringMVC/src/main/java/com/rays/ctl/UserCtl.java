package com.rays.ctl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rays.dto.UserDTO;
import com.rays.form.UserForm;
import com.rays.service.UserServiceImpl;
import com.rays.util.DataUtility;

@Controller
@RequestMapping(value = "/ctl/User")
public class UserCtl {

	@Autowired
	public UserServiceImpl service;

	@GetMapping
	public String display(@ModelAttribute("form") UserForm form, Model m) {
		return "UserRegistration";
	}

	@PostMapping
	public String submit(@ModelAttribute("form") @Valid UserForm form, BindingResult bindingResult, Model m) {
		
		System.out.println(bindingResult.hasErrors());

		if (bindingResult.hasErrors()) {
			System.out.println("in binding result");
			return "UserRegistration";
		}
		try {
			UserDTO dto = new UserDTO();
			dto.setId(form.getId());
			dto.setFirstName(form.getFirstName());
			dto.setLastName(form.getLastName());
			dto.setLogin(form.getLogin());
			dto.setPassword(form.getPassword());
			dto.setDob(DataUtility.stringToDate(form.getDob()));
			dto.setAddress(form.getAddress());
			service.save(dto);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "UserRegistration";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable long id) {
		service.delete(id);
		return "redirect:/ctl/User/list";
	}

	@RequestMapping(value = "list", method = { RequestMethod.GET, RequestMethod.POST })
	public String search(@ModelAttribute("form") UserForm form, Model model) {

		UserDTO dto = new UserDTO();
		dto.setFirstName(form.getFirstName());
		System.out.println("dob" + form.getDob());

		if (form.getDob() != null) {
			dto.setDob(DataUtility.stringToDate(form.getDob()));
			System.out.println("date " + dto.getDob());
		}

		List list = service.search(dto, 1, 5);
		model.addAttribute("list", list);
		return "UserList";
	}

	@GetMapping("get/{id}")
	public String get(@ModelAttribute("form") UserForm form, Model model, @PathVariable long id) {
		UserDTO dto = service.findByPk(id);
		form.setId(dto.getId());
		form.setFirstName(dto.getFirstName());
		form.setLastName(dto.getLastName());
		form.setLogin(dto.getLogin());
		form.setPassword(dto.getPassword());
		form.setDob(DataUtility.dateToString(dto.getDob()));
		form.setAddress(dto.getAddress());
		return "User";
	}

}
