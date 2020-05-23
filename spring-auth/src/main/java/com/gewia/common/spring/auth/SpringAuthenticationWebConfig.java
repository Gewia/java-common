package com.gewia.common.spring.auth;

import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gewia.common.auth.jwt.Jwt;
import com.gewia.common.auth.jwt.JwtScopes;
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
		DecodedJWT decodedJWT = (DecodedJWT) ((HttpServletRequest) webRequest.getNativeRequest()).getAttribute("accessToken");

		return new Jwt(UUID.fromString(decodedJWT.getClaim("userId").asString()),
				new JwtScopes(decodedJWT.getClaim("scopes").asList(String.class)));
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().equals(Jwt.class);
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
