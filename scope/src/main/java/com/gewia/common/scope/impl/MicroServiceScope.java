package com.gewia.common.scope.impl;

import com.gewia.common.scope.Scope;
import com.gewia.common.scope.ScopePart;
import com.gewia.common.scope.impl.util.Limitation;
import com.gewia.common.scope.impl.util.Mode;
import java.util.Collections;
import java.util.List;

/**
 * MicroServiceScope is specified by the following schema: 'microService.topic.mode.limitation.(extra)' .
 * Example: user.user.write.all - lets the given application/user write/create <i>all</i> users.
 */
public class MicroServiceScope extends BasicScope {

	public MicroServiceScope(ScopePart microService, ScopePart topic, Mode mode, Limitation limitation, ScopePart... extra) {
		super();

		this.addScopePart(microService);
		this.addScopePart(topic);
		this.addScopePart(mode.getScopePart());
		this.addScopePart(limitation.getScopePart());

		for (ScopePart scopePart : extra) this.addScopePart(scopePart);
	}

	@Override
	public Scope removeScopePart(int index) {
		if (index <= 3) throw new UnsupportedOperationException("MicroServiceScopes have to fit the schema.");
		return super.removeScopePart(index);
	}

	/**
	 * Gets the currently available scope parts.
	 *
	 * <p>
	 * 		The returned list is an unmodifiable list, because of the restrictions of {@link MicroServiceScope}.
	 * </p>
	 *
	 * @return all scope parts
	 *
	 * @since 1.0
	 */
	@Override
	public List<ScopePart> getScopeParts() {
		return Collections.unmodifiableList(super.getScopeParts());
	}


	/**
	 * Adds the given <i>scopePart</i> at the given <i>index</i>.
	 *
	 * @param scopePart the scope part to add
	 * @param index the index to add the scope part at
	 *
	 * @return this
	 *
	 * @throws IllegalArgumentException if the schema isn't fulfilled (e.g. invalid mode or limitation)
	 *
	 * @since 1.0
	 */
	@Override
	public Scope setScopePart(ScopePart scopePart, int index) {
		if (index == 2 && !Mode.isMode(scopePart)) throw new IllegalArgumentException("Third index has to be a valid mode.");
		if (index == 3 && !Limitation.isLimitation(scopePart)) throw new IllegalArgumentException("Fourth index has to be a valid limitation");

		return this.setScopePart(scopePart, index);
	}

	public ScopePart getMicroService() {
		return this.getScopePart(0);
	}

	public ScopePart getTopic() {
		return this.getScopePart(1);
	}

	public Mode getMode() {
		return Mode.find(this.getScopePart(2).getContent());
	}

	public Limitation getLimitation() {
		return Limitation.find(this.getScopePart(3).getContent());
	}

	public MicroServiceScope setMicroService(ScopePart microService) {
		this.setScopePart(microService, 0);
		return this;
	}

	public MicroServiceScope setTopic(ScopePart topic) {
		this.setScopePart(topic, 1);
		return this;
	}

	public MicroServiceScope setMode(Mode mode) {
		this.setScopePart(mode.getScopePart(), 2);
		return this;
	}

	public MicroServiceScope setLimitation(Limitation limitation) {
		this.setScopePart(limitation.getScopePart(), 3);
		return this;
	}

}
