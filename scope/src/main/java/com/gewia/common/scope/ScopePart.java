package com.gewia.common.scope;

import lombok.Data;

/**
 * Scopes are made out of {@link ScopePart}s which are concatenated using dots.
 *
 * @since 1.0
 */
@Data
public class ScopePart {

	private final String content;

	@Override
	public String toString() {
		return this.content;
	}

}
