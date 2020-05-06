package com.gewia.common.scope.impl;

import com.gewia.common.scope.Scope;
import com.gewia.common.scope.ScopePart;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import java.util.List;

/**
 * Represents a generic scope without further restrictions.
 *
 * @since 1.0
 */
@RequiredArgsConstructor(access = AccessLevel.MODULE)
public class BasicScope extends Scope {

	/**
	 * Gets the currently available scope parts.
	 *
	 * The returned list is the real modifiable list used for internal
	 * purposes.
	 * Modifications to this list will directly affect this scope.
	 *
	 * @return all scope parts
	 */
	@Override
	public List<ScopePart> getScopeParts() {
		return this.scopeParts;
	}

	@Override
	public Scope addScopePart(ScopePart scopePart) {
		this.scopeParts.add(scopePart);
		return this;
	}

	@Override
	public Scope removeScopePart(int index) {
		this.scopeParts.remove(index);
		return this;
	}

	@Override
	public ScopePart getScopePart(int index) {
		return this.scopeParts.get(index);
	}

	@Override
	public int getScopePartsSize() {
		return this.scopeParts.size();
	}


}
