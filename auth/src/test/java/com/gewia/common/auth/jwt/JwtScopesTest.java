package com.gewia.common.auth.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Assert;
import org.junit.Test;

public class JwtScopesTest {

	@Test
	public void testScopesUnmodifiable() {
		JwtScopes jwtScopes = this.getExampleJwtScopes();

		Assert.assertThrows(UnsupportedOperationException.class, () -> jwtScopes.getScopes().add("another.example.scope"));
	}

	@Test
	public void testResultOperation() {
		JwtScopes jwtScopes = this.getExampleJwtScopes();

		Assert.assertTrue(jwtScopes.includes("microservice.topic.mode.limitation.extra", null).getResult());
		Assert.assertFalse(jwtScopes.includes("another.exmaple.scope", null).getResult());
	}

	@Test
	public void testOrElseOperation() {
		JwtScopes jwtScopes = this.getExampleJwtScopes();

		AtomicInteger i = new AtomicInteger();
		jwtScopes.includes("another.example.scope", i::getAndIncrement).orElse(i::getAndDecrement);
		Assert.assertEquals(-1, i.get());
	}

	@Test
	public void testIncludeOperation() {
		JwtScopes jwtScopes = this.getExampleJwtScopes();

		AtomicInteger i = new AtomicInteger();
		jwtScopes.includes("microservice.topic.mode.limitation.extra", i::getAndIncrement).orElse(i::getAndDecrement);
		Assert.assertEquals(1, i.get());
	}

	@Test
	public void testInitializing() {
		List<String> exampleScopes = new ArrayList<>();
		exampleScopes.add("microservice.topic.mode.limitation.extra");
		JwtScopes jwtScopes = new JwtScopes(exampleScopes);

		Assert.assertEquals(exampleScopes, jwtScopes.getScopes());
	}

	private JwtScopes getExampleJwtScopes() {
		List<String> exampleScopes = new ArrayList<>();
		exampleScopes.add("microservice.topic.mode.limitation.extra");

		return  new JwtScopes(exampleScopes);
	}

}
