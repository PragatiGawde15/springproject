package com.rays.ctl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.UserDTO;
import com.rays.form.LoginForm;
import com.rays.form.UserForm;
import com.rays.form.UserRegistrationForm;
import com.rays.service.UserServiceInt;

@RestController
@RequestMapping(value = "Auth")
public class LoginCtl extends BaseCtl<UserForm, UserDTO, UserServiceInt> {

	@PostMapping("login")
	public ORSResponse login(@RequestBody @Valid LoginForm form, BindingResult bindingResult,
			HttpServletRequest request) {

		ORSResponse res = validate(bindingResult);

		if (res.isSuccess()) {
			return res;
		}

		UserDTO dto = baseService.authenticate(form.getLoginId(), form.getPassword());

		if (dto != null) {
			res.addData(dto);
		} else {
			res.addMessage("user is not valid");
		}

		return res;

	}

	@PostMapping("signUp")
	public ORSResponse signUp(@RequestBody @Valid UserRegistrationForm form, BindingResult bindingResult,
			HttpServletRequest request) {

		ORSResponse res = validate(bindingResult);

		if (res.isSuccess()) {
			return res;
		}

		UserDTO dto = (UserDTO) form.getDto();

		long pk = baseService.add(dto);

		res.addMessage("User registerd successfully..!!!");
		return res;

	}

}
