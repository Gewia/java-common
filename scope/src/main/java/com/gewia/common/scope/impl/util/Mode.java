package com.gewia.common.scope.impl.util;

import com.gewia.common.scope.ScopePart;
import lombok.Getter;

public enum Mode {

	READ,
	WRITE,
	DELETE;

	private static final Mode[] VALUES = Mode.values(); // #values() always creates a new copy of the array

	@Getter private final ScopePart scopePart = new ScopePart(this.name().toLowerCase());

	public static Mode find(String name) {
		for (Mode mode : VALUES)
			if (mode.name().equalsIgnoreCase(name)) return mode;

		return null;
	}

	public static boolean isMode(ScopePart scopePart) {
		return find(scopePart.getContent()) != null;
	}

}
