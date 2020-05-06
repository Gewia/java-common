package com.gewia.common.scope.impl;

import com.gewia.common.scope.Scope;
import com.gewia.common.scope.ScopePart;
import com.gewia.common.scope.impl.util.Limitation;
import com.gewia.common.scope.impl.util.Mode;

public class ScopeFactory {

	/**
	 * Creates a {@link BasicScope} with no further restrictions.
	 *
	 * @return a new {@link BasicScope}
	 *
	 * @since 1.0
	 */
	public static Scope createBasicScope() {
		return new BasicScope();
	}

	/**
	 * Creates a {@link MicroServiceScope} with it's specified restrictions.
	 *
	 * @param microService the micro service the scope belongs to
	 * @param topic the topic of the scope
	 * @param mode the mode of the scope
	 * @param limitation the limitation of the scope
	 * @param extra extra information
	 *
	 * @return a new {@link MicroServiceScope}
	 *
	 * @see MicroServiceScope
	 * @since 1.0
	 */
	public static MicroServiceScope createMicroServiceScope(String microService, String topic, Mode mode, Limitation limitation, ScopePart... extra) {
		return createMicroServiceScope(new ScopePart(microService), new ScopePart(topic), mode, limitation, extra);
	}

	/**
	 * Creates a {@link MicroServiceScope} with it's specified restrictions.
	 *
	 * @param microService the micro service the scope belongs to
	 * @param topic the topic of the scope
	 * @param mode the mode of the scope
	 * @param limitation the limitation of the scope
	 * @param extra extra information
	 *
	 * @return a new {@link MicroServiceScope}
	 *
	 * @see MicroServiceScope
	 * @since 1.0
	 */
	public static MicroServiceScope createMicroServiceScope(ScopePart microService, ScopePart topic, Mode mode, Limitation limitation, ScopePart... extra) {
		return new MicroServiceScope(microService, topic, mode, limitation, extra);
	}

}
