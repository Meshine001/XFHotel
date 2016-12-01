package com.xfhotel.hotel.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xfhotel.hotel.common.Constants;

/**
 * ¹ÜÀíÔ±µÇÂ¼À¹½ØÆ÷
 * 
 * @author Ming
 *
 */
public class AdminLoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		if (request.getSession().getAttribute(Constants.ADMIN_SESSION_ATTR) != null) {
			return true;
		}
		response.sendRedirect("/admin/login?next=".concat(request.getRequestURI()));
		return false;
	}

}
