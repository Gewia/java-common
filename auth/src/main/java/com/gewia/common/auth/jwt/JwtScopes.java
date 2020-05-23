package com.gewia.common.auth.jwt;

import com.gewia.common.util.Executor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtScopes {

	@Getter private List<String> scopes = new ArrayList<>();
	private boolean containing = false;

	public JwtScopes(List<String> scopes) {
		this.scopes = Collections.unmodifiableList(scopes);
	}

	public JwtScopes includes(String scope, Executor executor) {
		if (this.scopes.contains(scope)) {
			containing = true;
			if (executor != null) executor.action();
		} else containing = false;

		return this;
	}

	public void orElse(Executor executor) {
		if (!containing && executor != null) executor.action();
		containing = false;
	}

	public boolean getResult() {
		return containing;
	}

	public boolean hasScope(String scope) {
		return this.scopes.contains(scope);
	}

}
