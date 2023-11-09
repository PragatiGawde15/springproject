package com.rays.ctl;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class FrontCtl extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("in front controller");

		HttpSession session = request.getSession();

		if (session.getAttribute("user") == null) {
			request.setAttribute("error", "your session has been expired");
			RequestDispatcher rd = request.getRequestDispatcher("/Auth");
			rd.forward(request, response);
			return false;
		}
		return true;
	}
}