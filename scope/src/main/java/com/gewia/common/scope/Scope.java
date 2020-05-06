package com.gewia.common.scope;

import java.util.ArrayList;
import java.util.List;

/**
 * A scope is a mechanism to limit permissions of a person or application.
 *
 * <p>
 * 		This implementation is the reference implementation of scopes for the 'Gewia' project.
 *
 * 		Examples:
 * 			user.email.read.self - let's the user read his own email address
 * 			user.email.write.self - let's the user change his own email address
 * 			user.email.write.all - let's the user change every email address of every user
 * 			user.email.write.steve - let's the user change the email address of "steve"; this is a custom behaviour
 * 									and not part of the official specification
 * </p>
 * @since 1.0
 */
public abstract class Scope {

	protected final List<ScopePart> scopeParts = new ArrayList<>();

	/**
	 * Gets the currently available scope parts.
	 *
	 * <p>
	 * 		Whether the returned list is readonly or even a copy will be specified by
	 * 		the implementation.
	 * </p>
	 *
	 * @return all scope parts
	 *
	 * @since 1.0
	 */
	public abstract List<ScopePart> getScopeParts();

	/**
	 * Adds the given <i>scopeParts</i> at the end of the scope.
	 *
	 * @param scopeParts the scope parts to add
	 *
	 * @return this
	 *
	 * @since 1.0
	 */
	public Scope addScopeParts(String... scopeParts) {
		for (String scopePart : scopeParts) this.addScopePart(new ScopePart(scopePart));
		return this;
	}

	/**
	 * Adds the given <i>scopePart</i> at the end of the scope.
	 *
	 * @param scopePart the scope part to add
	 *
	 * @return this
	 *
	 * @since 1.0
	 */
	public Scope addScopePart(String scopePart) {
		return this.addScopePart(new ScopePart(scopePart));
	}

	/**
	 * Adds the given <i>scopePart</i> at the end of the scope.
	 *
	 * @param scopePart the scope part to add
	 *
	 * @return this
	 *
	 * @since 1.0
	 */
	public abstract Scope addScopePart(ScopePart scopePart);

	/**
	 * Adds the given <i>scopePart</i> at the given <i>index</i>.
	 *
	 * @param scopePart the scope part to add
	 * @param index the index to add the scope part at
	 *
	 * @return this
	 *
	 * @since 1.0
	 */
	public abstract Scope setScopePart(ScopePart scopePart, int index);

	/**
	 * Removes the scope part at the given <i>index</i>.
	 *
	 * @param index the index used to remove the scope part
	 *
	 * @return this
	 * @throws IndexOutOfBoundsException when the index is greater than the amount scope parts
	 *
	 * @since 1.0
	 */
	public abstract Scope removeScopePart(int index);

	/**
	 * Gets the scope part at the given <i>index</i>.
	 *
	 * @param index the index used to get the scope part
	 *
	 * @return this
	 * @throws IndexOutOfBoundsException when the index is greater than the amount scope parts
	 *
	 * @since 1.0
	 */
	public abstract ScopePart getScopePart(int index);

	/**
	 * Gets the amount of scope parts of this scope.
	 *
	 * @return the amount of scope parts
	 *
	 * @since 1.0
	 */
	public abstract int getScopePartsSize();

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		this.getScopeParts().forEach(scopePart -> {
			if (sb.length() != 0) sb.append(".");

			sb.append(scopePart);
		});

		return sb.toString();
	}

}
