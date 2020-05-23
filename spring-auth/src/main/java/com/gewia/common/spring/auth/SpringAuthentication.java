package com.gewia.common.spring.auth;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@ComponentScan("com.gewia.common.spring.auth")
public abstract class SpringAuthentication implements InitializingBean {

	@Getter(AccessLevel.PACKAGE) private static List<HandlerInterceptorAdapter> interceptors = new ArrayList<>();

	@Override
	public void afterPropertiesSet() {
		this.addAuthenticationInterceptors(interceptors);
	}

	abstract public void addAuthenticationInterceptors(List<HandlerInterceptorAdapter> authenticationInterceptors);

}
