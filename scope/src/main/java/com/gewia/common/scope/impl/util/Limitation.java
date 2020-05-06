package com.gewia.common.scope.impl.util;

import com.gewia.common.scope.ScopePart;
import lombok.Getter;

public enum Limitation {

	NONE,
	OWN,
	SELF,
	ALL,
	BETA;

	private static final Limitation[] VALUES = Limitation.values(); // #values() always creates a new copy of the array

	@Getter private final ScopePart scopePart = new ScopePart(this.name().toLowerCase());

	public static Limitation find(String name) {
		for (Limitation limitation : VALUES)
			if (limitation.name().equalsIgnoreCase(name)) return limitation;

		return null;
	}

	public static boolean isLimitation(ScopePart scopePart) {
		return find(scopePart.getContent()) != null;
	}

}
