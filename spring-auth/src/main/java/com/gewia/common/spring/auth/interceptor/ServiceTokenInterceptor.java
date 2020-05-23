package com.gewia.common.spring.auth.interceptor;

import com.gewia.common.spring.auth.IgnoreServiceToken;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@AllArgsConstructor
public class ServiceTokenInterceptor extends HandlerInterceptorAdapter {

	private final String serviceToken;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		response.setStatus(HttpStatus.FORBIDDEN.value());

		HandlerMethod method = (HandlerMethod) handler;
		if (method.hasMethodAnnotation(IgnoreServiceToken.class) ||
				method.getMethod().getDeclaringClass().getAnnotation(IgnoreServiceToken.class) != null) {
			response.setStatus(HttpStatus.OK.value());
			return true;
		}

		String serviceToken = request.getHeader("X-ServiceToken");

		if (serviceToken == null) return false;
		if (!this.serviceToken.equals(serviceToken)) return false;

		response.setStatus(HttpStatus.OK.value());
		return true;
	}

}
