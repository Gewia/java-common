package com.gewia.common.scope.impl;

import com.gewia.common.scope.Scope;

public class ScopeFactory {

	/**
	 * Creates a {@link BasicScope} with not further restrictions.
	 *
	 * @return a {@link BasicScope}
	 *
	 * @since 1.0
	 */
	public static Scope createBasicScope() {
		return new BasicScope();
	}

}
