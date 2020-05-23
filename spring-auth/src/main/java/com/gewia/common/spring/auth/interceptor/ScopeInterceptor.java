package com.gewia.common.spring.auth.interceptor;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gewia.common.auth.jwt.JwtUtil;
import com.gewia.common.spring.auth.AuthScope;
import com.gewia.common.spring.auth.Authentication;
import com.gewia.common.util.Pair;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@AllArgsConstructor
public class ScopeInterceptor extends HandlerInterceptorAdapter {

	private final JwtUtil jwtUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		HandlerMethod method = (HandlerMethod) handler;

		AuthScope[] authScopes;
		Authentication auth = method.getMethodAnnotation(Authentication.class);
		AuthScope methodAuthScope = method.getMethodAnnotation(AuthScope.class);
		if (auth != null) authScopes = auth.value();
		else {
			if (methodAuthScope == null) return true;
			authScopes = new AuthScope[]{methodAuthScope};
		}


		String jwt = request.getHeader("Authorization");
		if (jwt == null || jwt.isBlank()) return false;

		Pair<DecodedJWT, JwtUtil.VerificationResult> result = this.jwtUtil.verify(jwt);
		switch (result.getRight()) {
			case EXPIRED:
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				return false;
			case INVALID:
				response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
				return false;
			case FAILED:
				response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
				return false;
			case UNKNOWN:
				response.setStatus(HttpStatus.FORBIDDEN.value());
				return false;
			default:
				response.setStatus(HttpStatus.OK.value());
		}

		Claim claim = result.getLeft().getClaim("scopes");
		List<String> userScopes = claim.asList(String.class);
		for (AuthScope authScope : authScopes) {
			String scope = authScope.scope();
			if (scope.isBlank()) scope = authScope.value();
			if (!scope.isBlank()) {
				boolean isPresent = false;
				for (String userScope : userScopes)
					if (userScope.equalsIgnoreCase(scope)) {
						isPresent = true;
						break;
					}
				if (!isPresent) return false;
			}
		}

		request.setAttribute("accessToken", result.getLeft());

		return true;
	}

}
