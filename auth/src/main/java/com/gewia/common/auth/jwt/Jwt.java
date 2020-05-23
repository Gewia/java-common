package com.gewia.common.auth.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Jwt {

	private final UUID userId;
	private final JwtScopes userScopes;

}
