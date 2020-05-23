package com.gewia.common.spring.auth;

import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
@EnableWebMvc
public class SpringAuthenticationWebConfig implements WebMvcConfigurer, HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		return ((HttpServletRequest) webRequest.getNativeRequest()).getAttribute("accessToken");
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().equals(DecodedJWT.class);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(this);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		for (HandlerInterceptorAdapter interceptors : SpringAuthentication.getInterceptors())
			registry.addInterceptor(interceptors).addPathPatterns("/**/*");
	}

}
