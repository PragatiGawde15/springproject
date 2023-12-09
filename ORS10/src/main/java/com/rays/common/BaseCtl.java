package com.rays.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class BaseCtl<F extends BaseForm, T extends BaseDTO, S extends BaseServiceInt<T>> {

	@Autowired
	public S baseService;

	public ORSResponse validate(BindingResult bindingResult) {

		ORSResponse res = new ORSResponse(false);

		if (bindingResult.hasErrors()) {

			res.setSuccess(true);

			Map errors = new HashMap();

			List<FieldError> list = bindingResult.getFieldErrors();

			list.forEach(e -> {
				errors.put(e.getField(), e.getDefaultMessage());
			});

			res.addInputError(errors);
		}
		return res;

	}

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid F form, BindingResult bindingResult, HttpServletRequest request) {

		ORSResponse res = validate(bindingResult);

		if (res.isSuccess()) {
			return res;
		}

		T dto = (T) form.getDto();

		if (dto.getId() != null && dto.getId() > 0) {
			baseService.update(dto);
		} else {
			baseService.add(dto);
		}

		res.addData(dto.getId());
		res.addMessage("User registerd successfully..!!!");
		return res;

	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable Long id, HttpServletRequest req) {

		ORSResponse res = new ORSResponse(true);

		T dto = baseService.findByPk(id);
		if (dto == null) {
			res.addResult("id", 0);
		} else {
			res.addData(dto);
		}
		return res;
	}

	@GetMapping("delete/{ids}")
	public ORSResponse deleteMany(@PathVariable String[] ids, HttpServletRequest req) {

		ORSResponse res = new ORSResponse(true);
		try {

			for (String id : ids) {
				baseService.delete(Long.parseLong(id));
			}
			res.setSuccess(true);
			res.addMessage("Records Deleted Successfully");
		} catch (Exception e) {
			res.setSuccess(false);
			res.addMessage(e.getMessage());
		}
		return res;
	}

	@RequestMapping(value = "/search/{pageNo}", method = { RequestMethod.GET, RequestMethod.POST })
	public ORSResponse search(@RequestBody F form, @PathVariable int pageNo) {

		ORSResponse res = new ORSResponse(true);

		pageNo = (pageNo < 0) ? 0 : pageNo;

		T dto = (T) form.getDto();

		res.addData(baseService.search(dto, pageNo, 5));

		return res;
	}

}
